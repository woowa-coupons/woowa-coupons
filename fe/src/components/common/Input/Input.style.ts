import { Theme, css } from '@emotion/react';

export const input = (
  { colors, fonts, radius }: Theme,
  isRound: boolean
) => css`
  padding: 0px 10px;
  width: 100%;
  height: ${isRound ? '40px' : '30px'};
  font: ${fonts.regular12};
  color: ${colors.NAVY};
  border: 1px solid ${colors.GRAY};
  border-radius: ${isRound ? radius.large : radius.small};

  :focus {
    border: none;
    outline: 1px solid ${colors.PRIMARY};
  }
  ::placeholder {
    color: ${colors.GRAY_600};
  }
`;
