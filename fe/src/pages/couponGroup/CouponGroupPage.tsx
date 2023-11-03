import CouponGroupTableCard from "@components/coupon/common/CouponGroupTableCard.tsx";
import {contents} from "@pages/PromotionPage/PromotionPage.styles.ts";

export function CouponGroupPage() {
  return (
      <div css={contents}>
        <CouponGroupTableCard />
      </div>
  );
}