import CouponGroupDetailCard from "@components/coupon/common/CouponGroupDetailCard.tsx";
import {contents} from "@pages/PromotionPage/PromotionPage.styles.ts";

type Props = {
  couponGroupId: number
}
export function CouponGroupDetailPage({couponGroupId}: Props) {
  return (
      <div css={contents}>
        <CouponGroupDetailCard couponGroupId={couponGroupId} />
      </div>
  );
}