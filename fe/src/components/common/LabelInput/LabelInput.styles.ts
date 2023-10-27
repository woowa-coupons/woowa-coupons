import { Theme, css } from '@emotion/react';

export const labelInput = () => css`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 90px;
`;

export const input = (
  { colors, fonts, radius }: Theme,
  hasError: boolean
) => css`
  padding: 0px 10px;
  width: 100%;
  height: 30px;
  font: ${fonts.regular12};
  color: ${colors.NAVY};
  border: 1px solid ${colors.GRAY};
  border-radius: ${radius.small};

  :focus {
    border: none;
    outline: ${hasError
      ? `1px solid ${colors.RED}`
      : `1px solid ${colors.PRIMARY}`};
  }
  ::placeholder {
    color: ${colors.GRAY_600};
  }
`;

export const inputTitle = ({ colors, fonts }: Theme) => css`
  margin-bottom: 10px;
  font: ${fonts.regular14};
  color: ${colors.GRAY_700};
`;

export const errorLog = ({ colors, fonts }: Theme) => css`
  margin-top: 4px;
  margin-left: 10px;
  font: ${fonts.regular10};
  color: ${colors.RED};
`;
