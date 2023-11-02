import { useTheme } from '@emotion/react';
import {
  card,
  contentItem,
  contentText,
  contentTitle,
  contentsSection,
  idSection,
} from './PromotionCard.styles';

type PromotionCardProps = {
  id: number;
  memberType: string;
  lastOrderAt: string;
  couponGroupTitle: string;
};

export function PromotionCard({
  id,
  memberType,
  lastOrderAt,
  couponGroupTitle,
}: PromotionCardProps) {
  const theme = useTheme();

  return (
    <div css={card(theme)}>
      <div css={idSection(theme)}>
        <span>{`#${id + 1}`}</span>
      </div>
      <div css={contentsSection()}>
        <div css={contentItem}>
          <div css={contentTitle(theme)}>
            <span>회원 타입</span>
          </div>
          <div css={contentText(theme)}>
            <span>{memberType}</span>
          </div>
        </div>
        <div css={contentItem}>
          <div css={contentTitle(theme)}>
            <span>마지막 주문일</span>
          </div>
          <div css={contentText(theme)}>
            <span>{lastOrderAt}</span>
          </div>
        </div>
        <div css={contentItem}>
          <div css={contentTitle(theme)}>
            <span>쿠폰 그룹</span>
          </div>
          <div css={contentText(theme)}>
            <span>{couponGroupTitle}</span>
          </div>
        </div>
      </div>
    </div>
  );
}
