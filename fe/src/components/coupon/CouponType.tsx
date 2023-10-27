export type Item = {
  id: number;
  title: string;
  selected: boolean;
};

export type Coupon = {
  id: number;
  title: string;
  type: string;
  discount: number;
  quantity: number;
};

export const IsRandom = [
    { text: '일반 발급', value: '정액' },
    { text: '랜덤 발급', value: '정률' },
]

export const CouponGroupType = [
    { text: '일일 발급', value: 'EVERYDAY' },
    { text: '기간 발급', value: 'PERIOD' },
]

export const CouponType = [
    { text: '정액 쿠폰', value: 'fixed' },
    { text: '정률 쿠폰', value: 'rate' },
]
