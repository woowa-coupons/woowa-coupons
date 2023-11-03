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
  return response.data;
};

export const usePostSignInData = () => {
  const { navigateToPromotion } = usePageNavigator();
  const { mutate } = useMutation(postSignInData, {
    onSuccess: () => {
      console.log('success');
      localStorage.setItem(
        'accessToken',
        'eyJhbGciOiJIUzUxMiJ9.eyJhZG1pbklkIjo0LCJleHAiOjE2OTg5ODE0NTR9.3ZkKTbVrOn-aU9ZQU_RKjtoAlqzOtnNG2cZKjh3X70mgWTr-vmeFS-ZsnB1-VBOgGyTDKcVjSkNynTxzsGFoHg'
      );
      navigateToPromotion();
    },
  });
  return { mutate };
};
