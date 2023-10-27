import { Layout } from '@components/common/Layout/Layout';
import { PATH } from '@constants/path';
import { AnalyticsPage } from '@pages/AnalyticsPage/AnalyticsPage';
import { CouponGroupPage } from '@pages/CouponGroupPage/CouponGroupPage';
import { ErrorPage } from '@pages/ErrorPage/ErrorPage';
import { PromotionAddPage } from '@pages/PromotionAddPage/PromotionAddPage';
import { PromotionDetailPage } from '@pages/PromotionDetailPage/PromotionDetailPage';
import { PromotionPage } from '@pages/PromotionPage/PromotionPage';
import StartPage from '@pages/StartPage/\bStartPage';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

const queryClient = new QueryClient({
  /* options */
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
      retry: 0,
    },
  },
});

function App() {
  return (
    <>
      <QueryClientProvider client={queryClient}>
        <ReactQueryDevtools initialIsOpen={false} />
        <BrowserRouter>
          <Routes>
            <Route element={<Layout />}>
              <Route path={PATH.PROMOTION.BASE} element={<PromotionPage />} />
              <Route path={PATH.HOME} element={<PromotionPage />} />
              <Route path={PATH.COUPON.BASE} element={<CouponGroupPage />} />
              <Route path={PATH.ANALYTICS} element={<AnalyticsPage />} />
              <Route path={PATH.PROMOTION.ADD} element={<PromotionAddPage />} />
              <Route
                path={'/promotion/1'}
                element={<PromotionDetailPage promotionId={1} />}
              />
            </Route>
            <Route path={PATH.LOGIN} element={<StartPage />} />
            <Route path="*" element={<ErrorPage />} />
          </Routes>
        </BrowserRouter>
      </QueryClientProvider>
    </>
  );
}

export default App;
