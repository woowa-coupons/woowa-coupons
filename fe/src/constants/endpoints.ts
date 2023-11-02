export const API_ENDPOINT = {
  SIGN_UP: '/admin/auth/sign-up',
  LOGIN: '/admin/auth/sign-in',
  ADD_PROMOTION: '/admin/promotions',
  PROMOTION: '/admin/promotions',
  PROMOTION_DETAIL: (promotionId: number) => `/admin/promotions/${promotionId}`,
  COUPON_GROUP_LIST: '/admin/coupon-groups/summary',
};
