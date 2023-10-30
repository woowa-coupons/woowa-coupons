import { css } from "@emotion/react";
import { ButtonColor, ButtonSize } from "./CustomButtonType";
import { colors } from "@styles/base/Color";
import { radius } from "@styles/base/Object";

const buttonStyle = (size: ButtonSize, color: ButtonColor) => {
    const buttonDimensions = determineButtonDimensions(size);
    const buttonColos = determineButtonColors(color);

    return css`
        min-width: ${buttonDimensions.width};
        height: ${buttonDimensions.height};
        border-radius: ${radius.medium};
        background-color: ${buttonColos.backgroundColor};
        border: 1px solid ${buttonColos.borderColor};
        color: ${buttonColos.fontColor};
        flex-shrink: 0;
        cursor: pointer;
        padding: 0 13px;
    `;
}

const buttonIconStyle = css`
    margin-right: 8px;
`;

interface ButtonDimensions {
    width: string;
    height: string;
}

function determineButtonDimensions(size: ButtonSize): ButtonDimensions {
    let width, height;
    switch (size) {
        case 'small':
            width = '78px';
            height = '25px'; 
            break;
        case 'medium':
            width = '93px';
            height = '38px'; 
            break;
        default:
            width = 'auto';
            height = 'auto';
    }

    return {width, height};
}

interface ButtonColors {
    backgroundColor: string,
    borderColor: string,
    fontColor: string,
}

function determineButtonColors(color: ButtonColor): ButtonColors {
    let backgroundColor, borderColor, fontColor;
    switch(color) {
        case 'white':
            backgroundColor = colors.WHITE;
            borderColor = colors.GRAY;
            fontColor = colors.BLACK;
            break;
        case 'mint':
            backgroundColor = colors.PRIMARY;
            borderColor = colors.PRIMARY;
            fontColor = colors.WHITE;
            break;
        case 'blue':
            backgroundColor = colors.SKY;
            borderColor = colors.SKY;
            fontColor = colors.WHITE;
            break;
        case 'red':
            backgroundColor = colors.RED;
            borderColor = colors.RED;
            fontColor = colors.WHITE;
            break;
        default:
            backgroundColor = colors.WHITE_600;
            borderColor = colors.WHITE_600;
            fontColor = colors.WHITE;
    }
    return {backgroundColor, borderColor, fontColor}
}

export {buttonStyle, buttonIconStyle};