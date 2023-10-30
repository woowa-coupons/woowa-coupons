import { Theme, css } from '@emotion/react';

export const page = ({ colors }: Theme) => css`
  display: flex;
  width: 100vw;
  height: 100vh;
  background-color: ${colors.WHITE_200};
`;

export const main = () => css`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
`;
