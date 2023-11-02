import { API_ENDPOINT } from '@constants/endpoints';
import { publicApi } from '.';
import { useMutation } from '@tanstack/react-query';
import { usePageNavigator } from '@hooks/usePageNavigator';

type SignUpData = {
  email: string;
  nickname: string;
  password: string;
};

export const signUp = async (signUpData: SignUpData) => {
  const { data } = await publicApi.post(API_ENDPOINT.SIGN_UP, signUpData);

  return data;
};

export const useSignUp = () => {
  const { navigateToLogin } = usePageNavigator();
  const { mutate } = useMutation(signUp, {
    onSuccess: () => {
      navigateToLogin();
    },
    onError: () => {
      alert('에러가 발생했습니다. 다시 시도해주세요.');
    },
  });
  return { mutate };
};
