import { API_ENDPOINT } from '@constants/endpoints';
import { publicApi } from '.';
import { useMutation } from '@tanstack/react-query';
import { usePageNavigator } from '@hooks/usePageNavigator';

type SignUpData = {
  email: string;
  nickname: string;
  password: string;
};

type SignInData = {
  email: string;
  password: string;
};

const postSignUpData = async (signUpData: SignUpData) => {
  const response = await publicApi.post(API_ENDPOINT.SIGN_UP, signUpData);

  return response.data;
};

export const usePostSignUpData = () => {
  const { mutate } = useMutation(postSignUpData, {
    onSuccess: () => {
      location.reload();
    },
  });
  return { mutate };
};

const postSignInData = async (signInData: SignInData) => {
  const response = await publicApi.post(API_ENDPOINT.LOGIN, signInData);
  console.log(response);
  return response.data;
};

export const usePostSignInData = () => {
  const { navigateToPromotion } = usePageNavigator();
  const { mutate } = useMutation(postSignInData, {
    onSuccess: () => {
      navigateToPromotion();
    },
  });
  return { mutate };
};
