/** @jsxImportSource @emotion/react */
import React from "react";
import { buttonIconStyle, buttonStyle } from "./CustomButton.style";
import { ButtonColor, ButtonSize } from "./CustomButtonType";

type Props = {
    title: string,
    size: ButtonSize,
    color: ButtonColor,
    icon?: React.ReactNode,
    onButtonClick?: () => void,
}

export default function CustomButton({title, size, color, icon, onButtonClick}: Props) {

    return (
        <button 
            css={buttonStyle(size, color)} 
            {...(onButtonClick && { onClick: onButtonClick })}>
            {
                icon != undefined
                ? <i css={buttonIconStyle}>{ icon }</i>
                : null 
            }
            {title}
        </button>
    );
}
