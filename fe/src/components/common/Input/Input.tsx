import { useTheme } from '@emotion/react';
import { input } from './Input.style';

type InputProps = {
  value?: string;
  placeholder?: string;
  isRound?: boolean;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
};

export function Input({
  value,
  placeholder,
  isRound = false,
  onChange,
}: InputProps) {
  const theme = useTheme();

  return (
    <input
      type="text"
      css={input(theme, isRound)}
      value={value}
      placeholder={placeholder}
      onChange={onChange}
    />
  );
}
