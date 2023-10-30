/** @jsxImportSource @emotion/react */
import { InputHTMLAttributes } from 'react';
import {
  inputGroupTitleStyle,
  inputGroupTopDivStyle,
  textInputStyle,
} from './InputGroup.style';
import { requiredIndicationStyle } from '../common/CouponGroup.style';
import { InputGroupSize } from './InputGroupType';

type Props = InputHTMLAttributes<HTMLInputElement> & {
  title: string;
  required: boolean;
  value: string;
  placeHolder: string;
  inputSize: InputGroupSize;
};

export default function TextInputGroup({
  title,
  required,
  value,
  placeHolder,
  inputSize,
  ...props
}: Props) {
  return (
    <div css={inputGroupTopDivStyle}>
      <div css={inputGroupTitleStyle(inputSize)}>
        {title}
        {required ? <div css={requiredIndicationStyle}>*</div> : null}
      </div>
      <div>
        <input
          type={'text'}
          placeholder={placeHolder}
          css={textInputStyle(inputSize)}
          maxLength={50}
          value={value}
          {...props}
        />
      </div>
    </div>
  );
}
