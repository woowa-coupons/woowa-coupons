import { css } from "@emotion/react";
import { colors } from "@styles/base/Color";
import { fonts } from "@styles/base/Font";
import { radius } from "@styles/base/Object";

const searchBarDivStyle = css`
    position: relative;
    width: 300px;
    height: 40px;
`;

const searchBarIconStyle = css`
    width: 20px;
    height: 20px;
    flex-shrink: 0;
    display: flex;
    justify-content: space-between;
    position: absolute;
    top: 10px;
    left: 20px;
`;

const searchBarInputStyle = css`
    width: 100%;
    height: 100%;
    flex-shrink: 0;
    border-radius: ${radius.large};
    border: 1px solid ${colors.GRAY};
    background: ${colors.WHITE};
    padding: 0 50px;
    font: ${fonts.regular16};
`;

export {searchBarDivStyle, searchBarIconStyle, searchBarInputStyle};