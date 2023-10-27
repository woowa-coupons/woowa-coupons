import { Theme, css } from '@emotion/react';

export const page = () => css`
  display: flex;
  flex-direction: column;
  min-width: 1140px;
  padding: 30px;
  display: flex;
  gap: 20px;

  overflow-y: scroll;
`;

export const contents = ({ colors, shadow, radius }: Theme) => css`
  ${shadow.default}

  width: 1080px;
  background-color: ${colors.WHITE};
  border-radius: ${radius.medium};

  > div {
    border-bottom: 1px solid ${colors.WHITE_500};
  }

  > div:last-child {
    border-bottom: none;
  }
`;

export const contentsItem = () => css`
  display: flex;
  gap: 20px;
  min-height: 60px;
`;

export const title = ({ colors, fonts }: Theme) => css`
  padding: 20px 30px;
  min-width: 200px;
  height: 100%;
  color: ${colors.GRAY_700};
  font: ${fonts.bold14};
`;

export const content = ({ colors, fonts }: Theme) => css`
  display: flex;
  width: 100%;
  height: 100%;
  align-items: center;
  color: ${colors.NAVY};
  font: ${fonts.regular14};
`;

export const bannerImg = () => css`
  height: 100px;
`;

export const couponSection = () => css`
  padding: 20px 0px;
  display: flex;
  flex-direction: column;
  gap: 10px;
`;

export const textArea = () => css`
  padding: 20px 0px;
  width: 100%;
  min-height: 85px;
  border: none;
  resize: none;
  outline: none;
`;
