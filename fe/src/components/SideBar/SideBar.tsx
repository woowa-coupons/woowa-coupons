import { useTheme } from '@emotion/react';
import { bi, navigatorButton, sideBar } from './SideBar.styles';
import { Icon, icons } from '@components/common/Icon/Icon';
import { NavLink, useLocation } from 'react-router-dom';

type PageList = {
  name: string;
  title: string;
  path: string;
  icon: keyof typeof icons;
};

const PAGE_LISTS: PageList[] = [
  {
    name: 'promotion',
    title: '프로모션 관리',
    path: '/promotion',
    icon: 'plus',
  },
  {
    name: 'couponGroup',
    title: '쿠폰 그룹 관리',
    path: '/couponGroup',
    icon: 'grid',
  },
  { name: 'analytics', title: '통계', path: '/analytics', icon: 'chart' },
];

export function SideBar() {
  const theme = useTheme();
  const location = useLocation();

  return (
    <nav css={sideBar(theme)}>
      <a href="/promotion" css={bi(theme)}>
        <h1>우아한 프로모션</h1>
      </a>
      <ul>
        {PAGE_LISTS.map((item) => (
          <li key={item.name}>
            <NavLink
              className={({ isActive }) => (isActive ? 'a' : '')}
              to={item.path}
              css={navigatorButton(theme)}
            >
              <Icon
                name={item.icon}
                size="L"
                stroke={item.path === location.pathname ? 'WHITE' : 'GRAY_200'}
                fill="none"
              />
              <span>{item.title}</span>
            </NavLink>
          </li>
        ))}
      </ul>
    </nav>
  );
}
