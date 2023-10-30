import { Theme, css } from '@emotion/react';

export const button = ({ colors, fonts, radius, flex }: Theme) => css`
  ${flex.row}

  width: 100px;
  height: 40px;
  font: ${fonts.bold14};
  color: ${colors.WHITE};
  background-color: ${colors.PRIMARY};
  border: none;
  border-radius: ${radius.medium};
  &:hover {
    filter: brightness(1.1);
  }
  &:disabled {
    cursor: not-allowed;
    filter: grayscale(1);
  }
`;

export const buttonStyles = {
  iconWhite: ({ colors }: Theme) => css`
    padding: 10px 16px;
    width: fit-content;
    background-color: ${colors.WHITE};
    color: ${colors.GRAY_800};
    gap: 8px;
    border: 1px solid ${colors.GRAY};
  `,
  iconBlue: ({ colors }: Theme) => css`
    padding: 10px 16px;
    min-width: 96px;
    width: fit-content;
    background-color: ${colors.SKY};
    color: ${colors.WHITE};
    gap: 8px;
  `,
  iconPrimary: () => css`
    padding: 10px 16px;
    width: fit-content;
    gap: 8px;
  `,
  textLink: ({ colors, fonts }: Theme) => css`
    width: 100px;
    color: ${colors.SKY};
    background-color: ${colors.WHITE};
    font: ${fonts.regular12};
  `,
  onlyIconLarge: () => css`
    width: 40px;
    height: 40px;
    background-color: transparent;
  `,
  onlyTextWhite: ({ colors, fonts, radius }: Theme) => css`
    color: ${colors.GRAY_600};
    background-color: ${colors.WHITE};
    border: 1px solid ${colors.GRAY};
    font: ${fonts.regular14};
    border-radius: ${radius.large};
  `,
  white: ({ colors }: Theme) => css`
    color: ${colors.GRAY_800};
    background-color: ${colors.WHITE};
    border: 1px solid ${colors.GRAY};
  `,
};
