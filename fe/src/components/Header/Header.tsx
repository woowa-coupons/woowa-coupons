import { useTheme } from '@emotion/react';
import { header } from './Header.styles';
import { Icon } from '@components/common/Icon/Icon';
import { Button } from '@components/common/Button/Button';
import { buttonStyles } from '@components/common/Button/Button.styles';
import { useLocation } from 'react-router-dom';
import { usePageNavigator } from '@hooks/usePageNavigator';

// to do: 상수 분리
type HeaderTitle = {
  title: string;
  hasBackButton: boolean;
};

type HeaderTitleMap = {
  [path: string]: HeaderTitle;
};

const headerTitle: HeaderTitleMap = {
  '/': {
    title: '프로모션 관리',
    hasBackButton: false,
  },
  '/promotion': {
    title: '프로모션 관리',
    hasBackButton: false,
  },
  '/couponGroup': {
    title: '쿠폰 그룹 관리',
    hasBackButton: false,
  },
  '/couponGroup/detail/1': {
    title: '쿠폰 그룹 상세',
    hasBackButton: false,
  },
  '/couponGroup/add': {
    title: '쿠폰 그룹 추가',
    hasBackButton: false,
  },
  '/analytics': {
    title: '통계',
    hasBackButton: false,
  },
  '/promotion/1': {
    title: '프로모션 조회',
    hasBackButton: true,
  },
  '/promotion/add': {
    title: '프로모션 등록',
    hasBackButton: true,
  },
};

export function Header() {
  const theme = useTheme();
  const location = useLocation();
  const { navigateToGoBack } = usePageNavigator();

  return (
    <div css={header(theme)}>
      {headerTitle[location.pathname].hasBackButton && (
        <Button
          cssProp={buttonStyles.onlyIconLarge()}
          onClick={navigateToGoBack}
        >
          <Icon name="chevronLeft" size="XL" fill="WHITE" stroke="GRAY_700" />
        </Button>
      )}
      <h1>{headerTitle[location.pathname].title}</h1>
    </div>
  );
}
