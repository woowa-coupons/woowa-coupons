import { css } from '@emotion/react';
import { colors } from '@styles/base/Color';

const topLevelDiv = css`
  display: flex;
  flex-direction: row;
  background-color: ${colors.WHITE_200}
`;

const mainSectionDiv = css`
  display: flex;
  flex-direction: column;
  height: 100vh;
  margin: 0 auto;
`;

export { topLevelDiv, mainSectionDiv };
