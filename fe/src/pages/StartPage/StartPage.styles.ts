import { css, Theme } from '@emotion/react';

export const backgroundStyle = ({ colors, flex }: Theme) => css`
  ${flex.row}
  min-width: 1140px;
  width: 100vw;
  height: 100vh;
  background-color: ${colors.WHITE_200};
`;

export const startCard = ({ colors, radius, shadow }: Theme) => css`
  display: flex;
  justify-content: space-between;
  padding: 50px 40px;
  min-width: 1380px;
  height: 570px;
  background-color: ${colors.WHITE};
  border-radius: ${radius.medium};
  box-shadow: ${shadow.default};
`;

export const imgSection = ({ colors, flex }: Theme) => css`
  ${flex.row}
  display: flex;
  position: relative;
  ::before {
    position: absolute;
    top: 55px;
    left: -60px;
    width: 1px;
    height: 360px;
    content: '';
    background-color: ${colors.GRAY_100};
  }
`;

export const logoImg = ({ radius }: Theme) => css`
  border-radius: ${radius.medium};
`;
