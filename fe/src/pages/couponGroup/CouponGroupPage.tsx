import CouponGroupTableCard from "@components/coupon/common/CouponGroupTableCard.tsx";
import {contents, tableCaption} from "@pages/PromotionPage/PromotionPage.styles.ts";
import {Button} from "@components/common/Button/Button.tsx";
import {buttonStyles} from "@components/common/Button/Button.styles.ts";
import {Icon} from "@components/common/Icon/Icon.tsx";
import {theme} from "@styles/theme.ts";
import {usePageNavigator} from "@hooks/usePageNavigator.ts";

export function CouponGroupPage() {
  const { navigateToCouponGroupAdd } = usePageNavigator();

  return (
      <div css={contents}>
        <div css={tableCaption}>
          <Button cssProp={buttonStyles.iconWhite(theme)}>
            <Icon name="filter" />
            <span>필터</span>
          </Button>
          <Button
              cssProp={buttonStyles.iconPrimary()}
              onClick={navigateToCouponGroupAdd}
          >
            <Icon name="plus" stroke="WHITE" />
            <span>쿠폰 등록</span>
          </Button>
        </div>
        <CouponGroupTableCard />
      </div>
  );
}