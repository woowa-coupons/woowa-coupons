import { PromotionTable } from '@components/PromotionTable/PromotionTable';
import {
  contents,
  empty,
  emptyLog,
  tableCaption,
} from './PromotionPage.styles';
import { Button } from '@components/common/Button/Button';
import { buttonStyles } from '@components/common/Button/Button.styles';
import { useTheme } from '@emotion/react';
import { Icon } from '@components/common/Icon/Icon';
import { usePageNavigator } from '@hooks/usePageNavigator';
import emptyImg from '@assets/images/empty.png';
import { usePromotionList } from '@api/promotion';

export function PromotionPage() {
  const theme = useTheme();
  const { navigateToPromotionAdd } = usePageNavigator();

  const { data: promotionList } = usePromotionList();

  return (
    <div css={contents}>
      <div css={tableCaption}>
        <Button cssProp={buttonStyles.iconWhite(theme)}>
          <Icon name="filter" />
          <span>필터</span>
        </Button>
        <Button
          cssProp={buttonStyles.iconPrimary()}
          onClick={navigateToPromotionAdd}
        >
          <Icon name="plus" stroke="WHITE" />
          <span>프로모션 등록</span>
        </Button>
      </div>
      {promotionList && promotionList.length > 0 ? (
        <PromotionTable data={promotionList} />
      ) : (
        <div css={emptyLog(theme)}>
          <img css={empty} src={emptyImg} alt="" />
          <span>등록된 프로모션이 없습니다</span>
        </div>
      )}
    </div>
  );
}
