/** @jsxImportSource @emotion/react */
import { InputHTMLAttributes } from 'react';
import {
  dateInputStyle,
  inputGroupTitleStyle,
  inputGroupTopDivStyle,
} from './InputGroup.style';
import { requiredIndicationStyle } from '../common/CouponGroup.style';
import { InputGroupSize } from './InputGroupType';

type Props = InputHTMLAttributes<HTMLInputElement> & {
  title: string;
  required: boolean;
  value: string;
  inputGroupSize: InputGroupSize;
};

export default function DateInputGroup({
  title,
  required,
  value,
  inputGroupSize,
  ...props
}: Props) {
  return (
    <div css={inputGroupTopDivStyle}>
      <div css={inputGroupTitleStyle(inputGroupSize)}>
        {title}
        {required ? <div css={requiredIndicationStyle}>*</div> : null}
      </div>
      <div>
        <input
          type="datetime-local"
          css={dateInputStyle(inputGroupSize)}
          onChange={props.onChange}
          value={value}
          {...props}
        />
      </div>
    </div>
  );
}
