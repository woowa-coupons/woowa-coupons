import ErrorImg from '@assets/images/error.png';
import { errorImg, message, page } from './ErrorPage.styles';
import { useTheme } from '@emotion/react';
import { Button } from '@components/common/Button/Button';
import { buttonStyles } from '@components/common/Button/Button.styles';
import { usePageNavigator } from '@hooks/usePageNavigator';

export function ErrorPage() {
  const theme = useTheme();
  const { navigateToGoBack } = usePageNavigator();

  return (
    <div css={page(theme)}>
      <div css={errorImg}></div>
      <div css={message(theme)}>
        <span>화면을 불러오지 못했어요</span>
        <Button
          css={buttonStyles.onlyTextWhite(theme)}
          onClick={navigateToGoBack}
        >
          뒤로 돌아가기
        </Button>
      </div>
      <img css={errorImg} src={ErrorImg} alt="Not Found Page" />
    </div>
  );
}
