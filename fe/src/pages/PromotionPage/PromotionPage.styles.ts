import { Theme, css } from '@emotion/react';

export const background = () => css`
  display: flex;
  min-width: 1140px;
  width: 100vw;
`;

export const contents = () => css`
  padding: 30px;
  width: max-content;
  height: max-content;
  display: flex;
  flex-direction: column;
  gap: 20px;
`;

export const tableCaption = () => css`
  display: flex;
  justify-content: space-between;
`;

export const emptyLog = ({ flex, fonts }: Theme) => css`
  padding: 20px;
  ${flex.column}
  width: 100%;
  font: ${fonts.display24};
`;

export const empty = () => css`
  width: 300px;
  height: 300px;
`;
