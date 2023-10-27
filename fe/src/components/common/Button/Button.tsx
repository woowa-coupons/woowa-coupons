import { button } from './Button.styles';
import { SerializedStyles, useTheme } from '@emotion/react';

export type ButtonProps = {
  type?: 'button' | 'submit' | 'reset';
  cssProp?: SerializedStyles;
  onClick?: () => void;
  disabled?: boolean;
  children?: string | JSX.Element | JSX.Element[];
};

export function Button({
  type = 'button',
  cssProp,
  onClick,
  disabled = false,
  children,
  ...props
}: ButtonProps) {
  const theme = useTheme();
  return (
    <button
      {...props}
      type={type}
      css={[button(theme), cssProp]}
      onClick={onClick}
      disabled={disabled}
    >
      {children}
    </button>
  );
}
