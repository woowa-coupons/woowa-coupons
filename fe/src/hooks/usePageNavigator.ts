import { useNavigate } from 'react-router-dom';
import { PATH } from '@constants/path';

export const usePageNavigator = () => {
  const navigate = useNavigate();

  return {
    navigateToHome: () => navigate(PATH.HOME),
    navigateToLogin: () => navigate(PATH.LOGIN),
    navigateToPromotion: () => navigate(PATH.PROMOTION.BASE),
    navigateToPromotionAdd: () => navigate(PATH.PROMOTION.ADD),
    navigateToPromotionDetail: (promotionId: number) =>
      navigate(PATH.PROMOTION.DETAIL(promotionId)),
    navigateToCouponGroupAdd: () => navigate(PATH.COUPON.ADD),
    navigateToGoBack: () => navigate(PATH.GOBACK),
  };
};
