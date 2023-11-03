import CouponGroupRegistrationCard from "@components/coupon/common/CouponGroupRegistrationCard.tsx";
import {contents} from "@pages/PromotionPage/PromotionPage.styles.ts";

export function CouponGroupAddPage() {
  return (
    <div css={contents}>
      <CouponGroupRegistrationCard />
    </div>
  );
}