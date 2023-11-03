export const PATH = {
  HOME: '/',
  LOGIN: '/login',
  PROMOTION: {
    BASE: '/promotion',
    DETAIL: (promotionId: number) => `/promotion/detail/:${promotionId}`,
    ADD: '/promotion/add',
    EDIT: '/promotion/edit/:id',
  },
  COUPON: {
    BASE: '/couponGroup',
    DETAIL: (couponGroupId: number) => `/couponGroup/detail/${couponGroupId}`,
    ADD: '/couponGroup/add',
    EDIT: '/couponGroup/edit/:id',
  },
  ANALYTICS: '/analytics',
  GOBACK: -1,
};
