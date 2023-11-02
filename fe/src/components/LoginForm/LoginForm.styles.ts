import { Theme, css, keyframes } from '@emotion/react';

const slideInLeft = keyframes`
  from {
    transform: translateX(-10%);
    opacity: 0;

  }
  to {
    transform: translateX(0%);
    opacity: 1;
  }
`;

export const logo = ({ colors, fonts }: Theme) => css`
  font: ${fonts.display42};
  color: ${colors.GRAY_900};
`;

export const loginSection = () => css`
  margin-right: 40px;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 60px;
  width: 345px;
  height: 100%;
  animation: ${slideInLeft} 0.5s ease-in-out;
`;

export const loginForm = () => css`
  width: 100%;
`;

export const buttonSection = () => css`
  display: flex;
  justify-content: space-between;
  align-items: end;
  width: 100%;
`;
