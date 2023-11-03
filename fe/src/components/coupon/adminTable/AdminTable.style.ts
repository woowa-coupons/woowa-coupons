import { css } from '@emotion/react';
import { colors } from '@styles/base/Color';
import { radius } from '@styles/base/Object';

const adminTableStyle = css`
  width: 100%;
  border-radius: ${radius.medium};
`;

const adminTableHeaderStyle = (widthRatio: number) => {
  
  return css`
    width: ${widthRatio}%;
    height: 50px;
    vertical-align: middle;
    background: ${colors.WHITE_500};
  `;
};

const adminTableDataStyle = css`
  height: 50px;
    vertical-align: middle;
    text-align: center
`;

export { adminTableStyle, adminTableHeaderStyle, adminTableDataStyle };
