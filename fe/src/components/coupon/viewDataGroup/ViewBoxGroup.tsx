import { Coupon } from '../common/CouponType';
import ViewBoxItem from './ViewBoxItem';
import {
  viewBoxGroupTitleStyle,
  viewBoxGroupTopDivStyle,
  viewBoxStyle,
} from './ViewDataGroup.style';

type Props = {
  title: string;
  content: Coupon[]
};

export default function ViewBoxGroup({ title, content }: Props) {

  return (
    <div css={viewBoxGroupTopDivStyle}>
      <div css={viewBoxGroupTitleStyle}>{title}</div>
      <div css={viewBoxStyle}>
        {content.map((coupon, index) => (
          <ViewBoxItem
          index={index + 1}
          couponTitle={coupon.title}
          couponType={coupon.type}
          discount={coupon.discount}
          quantity={coupon.initialQuantity}
        />
        ))}
      </div>
    </div>
  );
}
