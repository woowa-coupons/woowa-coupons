import { Theme, css } from '@emotion/react';

export const table = ({ colors, radius, shadow }: Theme) => css`
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

export const page = () => css`
  display: flex;
  flex-direction: column;
  min-width: 1140px;
  padding: 30px;
  display: flex;
  gap: 20px;

  overflow-y: scroll;
`;

export const tableItem = () => css`
  padding: 20px 140px 20px 30px;
  display: flex;
  gap: 20px;
  align-items: center;
  width: 100%;
  min-height: 70px;
`;

export const titleSection = ({ fonts }: Theme) => css`
  display: flex;
  gap: 4px;
  width: 170px;
  font: ${fonts.bold14};
`;

export const required = ({ colors }: Theme) => css`
  color: ${colors.RED};
`;

export const inputSection = () => css`
  width: 100%;
`;

export const addImgSection = ({ colors, radius, flex }: Theme) => css`
  ${flex.row}
  gap: 10px;
  width: 170px;
  height: 100px;
  border: 1px solid ${colors.GRAY};
  border-radius: ${radius.medium};
`;

export const dateInput = ({ colors, fonts, radius }: Theme) => css`
  padding: 0px 20px;
  height: 30px;
  border: 1px solid ${colors.GRAY};
  font: ${fonts.regular12};
  border-radius: ${radius.small};

  :focus {
    border: none;
    outline: 1px solid ${colors.PRIMARY};
  }
`;

export const selectorStyles = ({ colors, fonts, radius }: Theme) => css`
  padding: 3px 10px;
  font: ${fonts.regular12};
  border: 1px solid ${colors.GRAY};
  border-radius: ${radius.small};

  :focus {
    border: none;
    outline: 1px solid ${colors.PRIMARY};
  }
`;

export const settingInputSection = () => css`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 20px;
`;

export const promotionSetting = () => css`
  height: 30px;
  display: flex;
  align-items: center;
  gap: 20px;
`;

export const settingTitle = () => css`
  width: 100px;
`;

export const settingButtonSection = () => css`
  display: flex;
  justify-content: end;
  gap: 20px;
  width: 100%;
`;

export const settingSection = () => css`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 40px;
`;

export const cardSection = ({ colors, radius }: Theme) => css`
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 20px;
  justify-content: center;
  width: 100%;
  min-height: 250px;
  border: 1px solid ${colors.GRAY};
  border-radius: ${radius.medium};
`;

export const buttonSection = () => css`
  padding: 70px 0px 30px 0px;
  width: 100%;
  display: flex;
  justify-content: center;
  gap: 50px;
`;
