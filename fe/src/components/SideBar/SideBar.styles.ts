import { Theme, css } from '@emotion/react';

export const sideBar = ({ colors }: Theme) => css`
  display: flex;
  flex-direction: column;
  gap: 40px;
  padding: 40px 0px;
  min-width: 300px;
  height: 100%;
  background-color: ${colors.GRAY_900};
`;

export const bi = ({ colors, fonts }: Theme) => css`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: ${colors.WHITE};
  font: ${fonts.display42};
  text-decoration: none;
`;

export const navigatorButton = ({ colors, fonts }: Theme) => css`
  padding: 0px 40px;
  display: flex;
  align-items: center;
  gap: 25px;
  width: 100%;
  height: 56px;
  color: ${colors.GRAY_200};
  font: ${fonts.bold16};
  text-decoration: none;
  &.active {
    position: relative;
    padding: 0px 40px;
    display: flex;
    align-items: center;
    gap: 25px;
    width: 100%;
    height: 56px;
    color: ${colors.WHITE};
    background-color: ${colors.GRAY_500};
    font: ${fonts.bold16};
    text-decoration: none;

    ::before {
      position: absolute;
      top: 0;
      left: 0;
      width: 5px;
      height: 56px;
      content: '';
      background-color: ${colors.LAVENDER};
    }
  }
`;

export const selectedNavigatorButton = ({ colors, fonts }: Theme) => css`
  position: relative;
  padding: 0px 40px;
  display: flex;
  align-items: center;
  gap: 25px;
  width: 100%;
  height: 56px;
  color: ${colors.WHITE};
  background-color: ${colors.GRAY_500};
  font: ${fonts.bold16};
  text-decoration: none;

  ::before {
    position: absolute;
    top: 0;
    left: 0;
    width: 5px;
    height: 56px;
    content: '';
    background-color: ${colors.LAVENDER};
  }
`;
