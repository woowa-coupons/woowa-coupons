import { Theme, css } from '@emotion/react';

export const card = ({ colors, radius }: Theme) => css`
  display: flex;
  width: 680px;
  height: 100px;
  color: ${colors.GRAY_700};
  border: 1px solid ${colors.GRAY};
  border-radius: ${radius.medium};
`;

export const idSection = ({ colors, fonts, flex }: Theme) => css`
  ${flex.row}

  position: relative;
  width: 80px;
  height: 100%;

  font: ${fonts.bold14};

  ::after {
    position: absolute;
    top: 20px;
    right: 0;
    content: '';
    width: 1px;
    height: 60px;
    background-color: ${colors.GRAY};
  }
`;

export const contentsSection = () => css`
  padding: 0px 40px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 10px;
  width: 600px;
`;

export const contentItem = () => css`
  display: flex;
  gap: 20px;
`;

export const contentTitle = ({ fonts }: Theme) => css`
  width: 100px;
  font: ${fonts.regular12};
`;

export const contentText = ({ fonts }: Theme) => css`
  font: ${fonts.regular10};
`;
