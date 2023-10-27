import { Theme, css } from '@emotion/react';

export const page = ({ colors, fonts }: Theme) => css`
  display: flex;
  align-items: center;
  justify-content: space-between;
  min-width: 1000px;
  min-height: 800px;
  width: 100vw;
  height: 100vh;
  font: ${fonts.display24};
  background-color: ${colors.WHITE_200};
`;

export const message = ({ flex }: Theme) => css`
  ${flex.column}
  gap: 30px;
`;

export const errorImg = () => css`
  width: 400px;
  height: 400px;
`;
