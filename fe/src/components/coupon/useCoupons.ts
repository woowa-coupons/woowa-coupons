import { useRef, useState } from 'react';
import { Coupon } from './CouponType';

export const useCoupon = (initialValue: Coupon[] = []) => {
  const [coupons, setCoupons] = useState<Coupon[]>(initialValue);
  const couponId = useRef(0);

  const onCreate = (coupon: Coupon) => {
    couponId.current += 1;
    setCoupons([...coupons, coupon]);
  };

  const onEdit = (couponId: number, newCoupon: Coupon) => {
    setCoupons(
      coupons.map((c) =>
        c.id === couponId
          ? {
              ...c,
              title: newCoupon.title,
              type: newCoupon.type,
              discount: newCoupon.discount,
              quantity: newCoupon.quantity,
            }
          : c
      )
    );
  };

  const onDelete = (couponId: number) => {
    const deletedCoupons = coupons.filter((c) => c.id !== couponId);
    setCoupons(deletedCoupons);
  };

  return {
    coupons,
    setCoupons,
    onCreate,
    onEdit,
    onDelete,
  };
};
