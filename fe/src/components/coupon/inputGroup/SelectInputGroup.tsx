/** @jsxImportSource @emotion/react */
import { SelectHTMLAttributes } from 'react';
import {
  inputGroupTitleStyle,
  inputGroupTopDivStyle,
  selectInputStyle,
} from './InputGroup.style';
import { requiredIndicationStyle } from '../CouponGroup.style';
import { InputGroupSize } from './InputGroupType';

type Option = { text: string; value: string; };

type Props = SelectHTMLAttributes<HTMLSelectElement> & {
  title: string;
  required: boolean;
  options: Option[];
  inputGroupSize: InputGroupSize;
};

export default function SelectInputGroup({
  title,
  required,
  options,
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
        <select 
          css={selectInputStyle(inputGroupSize)}
          {...props} >
          {/* {props.value ? null : (
            <option value="" disabled hidden selected>{props.placeholder}</option>
          )} */}
          {options.map((option, index) => (
            <option key={index} value={option.value}>
              {option.text}
            </option>
          ))}
        </select>
      </div>
    </div>
  );
}
