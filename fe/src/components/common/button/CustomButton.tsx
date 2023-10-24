/** @jsxImportSource @emotion/react */
import { buttonIconStyle, buttonStyle } from "./CustomButton.style";
import { ButtonColor, ButtonSize } from "./CustomButtonType";

type Props = {
    title: string,
    size: ButtonSize,
    color: ButtonColor,
    icon?: string,
    onButtonClick?: () => void,
}

export default function CustomButton(props: Props) {

    return (
        <button 
            css={buttonStyle(props.size, props.color)} 
            {...(props.onButtonClick && { onClick: props.onButtonClick })}>
            {
                props.icon != undefined
                ? <i css={buttonIconStyle}>{ props.icon }</i>
                : null 
            }
            {props.title}
        </button>
    );
}
