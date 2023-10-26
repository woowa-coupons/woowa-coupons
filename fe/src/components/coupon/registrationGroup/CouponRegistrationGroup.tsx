/** @jsxImportSource @emotion/react */
import {
  inputBoxGropupParagraphStyle,
  inputBoxGroupTitleStyle,
  inputBoxGroupTopDivStyle,
} from './CouponRegistrationGroup.style';
import RegistrationBox from './RegistrationViewBox';
import { requiredIndicationStyle } from '../CouponGroup.style';
import { Coupon } from '../CouponType';
import RegistrationInputBox from './RegistrationInputBox';

type Props = {
  title: string;
  required: boolean;
  coupons: Coupon[];
  onCreate: (coupon: Coupon) => void;
  onEdit: (couponId: number, newCoupon: Coupon) => void;
  onDelete: (couponId: number) => void;
};

export default function CouponRegistrationGroup(props: Props) {
  return (
    <div css={inputBoxGroupTopDivStyle}>
      <div css={inputBoxGroupTitleStyle}>
        {props.title}
        {props.required ? <div css={requiredIndicationStyle}>*</div> : null}
      </div>
      <div>
        <RegistrationInputBox
          coupons={props.coupons}
          onCreate={props.onCreate}
        />

        <p css={inputBoxGropupParagraphStyle}>
          {props.coupons.length}개의 쿠폰이 등록되었습니다.
        </p>

        <RegistrationBox
          coupons={props.coupons}
          onCreate={props.onCreate}
          onEdit={props.onEdit}
          onDelete={props.onDelete}
        />
      </div>
    </div>
  );
}
