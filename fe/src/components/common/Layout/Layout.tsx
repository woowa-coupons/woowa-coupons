import { Header } from '@components/Header/Header';
import { SideBar } from '@components/SideBar/SideBar';
import { Navigate, Outlet } from 'react-router-dom';
import { main, page } from './Layout.styles';
import { useTheme } from '@emotion/react';

export function Layout() {
  const theme = useTheme();
  const accessToken = localStorage.getItem('accessToken');

  if (accessToken) {
    return (
      <div css={page(theme)}>
        <SideBar />
        <div css={main}>
          <Header />
          <Outlet />
        </div>
      </div>
    );
  }

  return <Navigate to={'/login'} />;
}
