import { css } from "@emotion/react";
import { colors } from "@styles/base/Color";

const topDivStyle = css`
    width: 300px;
    height: 100vh;
    min-height: 100%;
    flex-shrink: 0;
    background: ${colors.GRAY_900};
    display: flex;
    flex-direction: column;
    gap: 40px;
`;

const brandDivStyle = css`
    color: ${colors.WHITE};
    font-size: 42px;
    text-align: center;
    padding: 40px 0;
`;

const itemDivStyle = css`
    width: 300px;
    height: 56px;
    flex-shrink: 0;
    list-style: none;
    padding-left: 0;
    display: flex;
    position: relative;
`;

const selectedItemDivStyle = css`
    background: ${colors.GRAY_500};
    color: ${colors.WHITE};
`;

const unselectedItemDivStyle = css`
    background: ${colors.GRAY_900};
    color: ${colors.GRAY_200};
`;

const itemIconDivStyle = css`
    width: 104px;
    height: 100%;
    display : flex;
    justify-content : center;
    align-items : center;
`;

const itemTitleDivStyle = css`
    display : flex;
    justify-content : center;
    align-items : center;
`;

const itemHighlightDivStyle = css`
    background: ${colors.LAVENDER};
    width: 5px;
    height: 100%;
    position: absolute;
`;

export {
    topDivStyle,
    brandDivStyle,
    itemDivStyle,
    selectedItemDivStyle,
    unselectedItemDivStyle,
    itemIconDivStyle,
    itemTitleDivStyle,
    itemHighlightDivStyle
}