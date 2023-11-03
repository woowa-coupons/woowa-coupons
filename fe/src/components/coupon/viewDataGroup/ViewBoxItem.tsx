import { viewBoxItemIndexDivStyle, viewBoxItemInputGroupDivStyle, viewBoxItemTopDivStyle, viewBoxItemVerticalLineStyle } from './ViewDataGroup.style';
import ViewTextGroup from './ViewTextGroup';

type Props = {
    index: number,
    couponTitle: string,
    couponType: string,
    discount: number,
    quantity: number,
}

export default function ViewBoxItem({index, couponTitle, couponType, discount, quantity}: Props) {
  return (
    <div css={viewBoxItemTopDivStyle}>
      <div css={viewBoxItemIndexDivStyle}>#{index}</div>
      <div css={viewBoxItemVerticalLineStyle}></div>
      <div css={viewBoxItemInputGroupDivStyle}>
        <ViewTextGroup title={"쿠폰 이름"} content={couponTitle} size={"small"} />
        <ViewTextGroup title={"쿠폰 타입"} content={couponType} size={"small"} />
        <ViewTextGroup title={"금액/퍼센트"} content={discount} size={"small"} />
        <ViewTextGroup title={"수량"} content={quantity} size={"small"} />
      </div>
    </div>
  );
}
