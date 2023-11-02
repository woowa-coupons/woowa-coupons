import { css } from '@emotion/react';
import { fonts } from '@styles/base/Font';
import { ViewDataGroupSize } from './ViewDataGroupType';
import { colors } from '@styles/base/Color';
import { flex, radius } from '@styles/base/Object';

const viewDataGroupTopDivStyle = (size: ViewDataGroupSize) => {
  return css`
    display: flex;
    flex-direction: row;
    ${size === 'small' ? undefined : 'border-bottom: 1px solid ' + colors.GRAY};
    ${size === 'small' ? undefined : 'padding-bottom: 20px'};
  `;
};

const viewDataGroupTitleStyle = (size: ViewDataGroupSize) => {
  return css`
    width: 170px;
    display: flex;
    align-items: center;
    font: ${size === 'small' ? fonts.regular12 : fonts.regular14};
  `;
};

const contentStyle = (size: ViewDataGroupSize) => css`
  width: 300px;
  height: ${size === 'small' ? '20px' : '30px'};
  display: flex;
  align-items: center;
  font: ${size === 'small' ? fonts.regular12 : fonts.regular14};
  padding: 0 20px;
`;

const viewBoxGroupTopDivStyle = css`
  display: flex;
  flex-direction: row;
`;

const viewBoxGroupTitleStyle = css`
  width: 170px;
  height: 30px;
  display: flex;
  align-items: center;
`;

const viewBoxStyle = css`
  width: 700px;
  flex-shrink: 0;
  border-radius: 8px;
  border: 1px solid #d0d5dd;
  background: #fff;
  padding: 15px 10px;
`;

const viewBoxItemTopDivStyle = css`
  width: 680px;
  height: 128px;
  flex-shrink: 0;
  border-radius: ${radius.medium};
  border: 1px solid ${colors.GRAY};
  background: ${colors.WHITE};
  ${flex.row}
  margin-bottom: 10px;
`;

const viewBoxItemIndexDivStyle = css`
  width: 77px;
  height: 100%;
  ${flex.row}
  font: ${fonts.bold14};
`;

const viewBoxItemVerticalLineStyle = css`
  border-left: medium solid ${colors.GRAY};
  height: 80px;
`;

const viewBoxItemInputGroupDivStyle = css`
  width: 604px;
  ${flex.column}
  gap: 7px;
`;

export {
  viewDataGroupTopDivStyle,
  viewDataGroupTitleStyle,
  contentStyle,
  viewBoxGroupTopDivStyle,
  viewBoxGroupTitleStyle,
  viewBoxStyle,
  viewBoxItemTopDivStyle,
  viewBoxItemIndexDivStyle,
  viewBoxItemVerticalLineStyle,
  viewBoxItemInputGroupDivStyle,
};
