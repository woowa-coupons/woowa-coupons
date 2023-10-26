/** @jsxImportSource @emotion/react */

import { Coupon } from "../CouponType";
import CouponRegistrationItem from "./CouponRegistrationItem";
import { registrationBoxStyle } from "./CouponRegistrationGroup.style";

type Props = {
    coupons: Coupon[],
    onCreate: (coupon: Coupon) => void,
    onEdit: (couponId: number, newCoupon: Coupon) => void,
    onDelete: (couponId: number) => void,
}

export default function RegistrationViewBox(props: Props) {



    return (
        <div css={registrationBoxStyle}>
            {props.coupons.slice().reverse().map((coupon, index) => (
                <CouponRegistrationItem
                    key={index}
                    index={props.coupons.length - index}
                    coupon={coupon}
                    onEdit={props.onEdit}
                    onDelete={props.onDelete}
                />
            ))}
        </div>
    );
}
