/** @jsxImportSource @emotion/react */

import { Coupon } from '../common/CouponType';
import { registrationBoxStyle } from './CouponRegistrationGroup.style';
import CouponRegistrationItem from './CouponRegistrationItem';

type Props = {
  coupons: Coupon[];
  onCreate: (coupon: Coupon) => void;
  onEdit: (couponId: number, newCoupon: Coupon) => void;
  onDelete: (couponId: number) => void;
};

export default function RegistrationViewBox(props: Props) {
  return (
    <div css={registrationBoxStyle}>
      {props.coupons
        .slice()
        .reverse()
        .map((coupon, index) => (
          <CouponRegistrationItem
            key={index}
            index={props.coupons.length - index}
            coupon={coupon}
            // onEdit={props.onEdit} TODO: 입력된 쿠폰 수정기능 추가하기
            onDelete={props.onDelete}
          />
        ))}
    </div>
  );
}
