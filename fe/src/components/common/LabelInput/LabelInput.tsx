import { useTheme } from '@emotion/react';

import { errorLog, input, inputTitle, labelInput } from './LabelInput.styles';
import { error } from '@constants/error';

type LabelInputProps = {
  id: string;
  label: string;
  type?: 'text' | 'email' | 'password';
  placeholder?: string;
  required?: boolean;
  value: string;
  hasError?: boolean;
  errorMsg?: keyof typeof error;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
};

export function LabelInput({
  id,
  label,
  type = 'text',
  placeholder,
  required = true,
  value,
  hasError = false,
  errorMsg = 'default',
  onChange,
}: LabelInputProps) {
  const theme = useTheme();
  return (
    <div css={labelInput()}>
      <label css={inputTitle(theme)} htmlFor={id}>
        {label}
      </label>
      <input
        id={id}
        css={input(theme, hasError)}
        type={type}
        placeholder={placeholder}
        required={required}
        value={value}
        onChange={onChange}
      />
      {hasError && <p css={errorLog(theme)}>{error[errorMsg]}</p>}
    </div>
  );
}
