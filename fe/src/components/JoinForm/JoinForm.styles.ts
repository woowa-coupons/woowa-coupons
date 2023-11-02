import { Theme, css, keyframes } from '@emotion/react';

const slideInRight = keyframes`
  from {
    transform: translateX(10%);
    opacity: 0;

  }
  to {
    transform: translateX(0%);
    opacity: 1;
  }
`;

export const joinPage = () => css`
  display: flex;
  flex-direction: column;
  gap: 15px;
  width: 345px;
  height: 100%;
  animation: ${slideInRight} 0.5s ease-in-out;
`;

export const headerSection = () => css`
  display: flex;
  align-items: center;
  gap: 20px;
`;

export const goBackButton = ({ colors }: Theme) => css`
  width: 40px;
  height: 40px;
  background-color: ${colors.WHITE};
`;

export const headerTitle = ({ colors, fonts }: Theme) => css`
  color: ${colors.GRAY_700};
  font: ${fonts.display24};
`;

export const joinForm = () => css``;

export const buttonSection = () => css`
  display: flex;
  justify-content: end;
  width: 100%;
`;
