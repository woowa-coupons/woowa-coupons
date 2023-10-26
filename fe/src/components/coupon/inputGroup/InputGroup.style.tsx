import { css } from "@emotion/react";
import { colors } from "@styles/base/Color";
import { radius } from "@styles/base/Object";
import { InputGroupSize } from "./InputGroupType";
import { fonts } from "@styles/base/Font";

const inputGroupTopDivStyle = css`
    display: flex;
    flex-direction: row;
`;

const inputGroupTitleStyle = (size: InputGroupSize) => {
    return css`
        width: 170px;
        display: flex;
        align-items: center;
        font: ${size === "small"? fonts.regular12 : fonts.regular14};
    `
}

const textInputStyle = (size: InputGroupSize) => {
    return css`
        width: 300px;
        height: ${size === "small"? "20px" : "30px"};
        flex-shrink: 0;
        border-radius: ${radius.medium};
        border: 1px solid ${colors.GRAY};
        background: ${colors.WHITE};
        padding: 0 20px;
    `;
}

const dateInputStyle = (size: InputGroupSize) => {
    return css`
        width: 200px;
        height: ${size === "small" ? "20px" : "30px"};
        flex-shrink: 0;
        border-radius: ${radius.medium};
        border: 1px solid ${colors.GRAY};
        background: ${colors.WHITE};
        padding: 0 20px;
`;
}

const selectInputStyle = (size: InputGroupSize) => {
    return css`
        width: 300px;
        height: ${size === "small" ? "20px" : "30px"};
        flex-shrink: 0;
        border-radius: ${radius.medium};
        border: 1px solid ${colors.GRAY};
        background: ${colors.WHITE};
        padding: 0 20px;
`;
}

export {
    inputGroupTopDivStyle,
    inputGroupTitleStyle,
    textInputStyle,
    dateInputStyle,
    selectInputStyle,
}