import { Theme, css } from '@emotion/react';

export const header = ({ colors, fonts }: Theme) => css`
  padding: 0px 30px;
  display: flex;
  align-items: center;
  gap: 20px;
  width: 100%;
  min-height: 100px;
  font: ${fonts.display24};
  color: ${colors.GRAY_700};
  background-color: ${colors.WHITE};
  border-bottom: 1px solid ${colors.GRAY_100};
`;
