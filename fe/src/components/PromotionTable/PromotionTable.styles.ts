import { Theme, css } from '@emotion/react';

export const table = ({ colors, shadow }: Theme) => css`
  ${shadow.default}
  width: fit-content;
  position: relative;
  color: ${colors.NAVY};
`;

export const header = ({ colors, fonts, radius, flex }: Theme) => css`
  display: flex;
  gap: 20px;
  padding: 0px 20px;
  width: 1080px;
  height: 50px;
  font: ${fonts.regular12};
  color: ${colors.GRAY_300};
  background-color: ${colors.WHITE_400};
  border-bottom: 1px solid ${colors.WHITE_500};
  border-top-left-radius: ${radius.medium};
  border-top-right-radius: ${radius.medium};

  > td {
    ${flex.row}
  }
`;

export const headerTitle = (size: string) => css`
  width: ${size};
`;

export const promotionItem = (
  { colors, flex, fonts }: Theme,
  isFallback: boolean
) => css`
  display: flex;
  padding: 0px 20px;
  width: 1080px;
  height: 70px;
  gap: 20px;
  background-color: ${colors.WHITE};
  font: ${fonts.regular12};

  ${isFallback
    ? ''
    : css`
        :hover {
          background-color: ${colors.WHITE_500};
        }
        > td {
          ${flex.row}
        }
      `}
`;

export const tableBody = ({ colors, radius }: Theme) => css`
  > tr {
    border-bottom: 1px solid ${colors.WHITE_500};
  }

  > tr:last-child {
    border: none;
    border-bottom-left-radius: ${radius.medium};
    border-bottom-right-radius: ${radius.medium};
  }
`;

export const bannerImg = () => css`
  width: 90px;
  height: 50px;
`;

export const detailLink = ({ colors }: Theme) => css`
  color: ${colors.NAVY};
  text-decoration: none;
`;

export const sectionStyles = {
  checkbox: () => css`
    width: 36px;
  `,
  no: () => css`
    width: 54px;
  `,
  banner: () => css`
    width: 106px;
  `,
  name: () => css`
    width: 218px;
  `,
  startDate: () => css`
    width: 80px;
  `,
  endDate: () => css`
    width: 80px;
  `,
  status: () => css`
    width: 80px;
  `,
  writer: () => css`
    width: 106px;
  `,
  buttons: () => css`
    display: flex;
    width: 120px;
  `,
};
