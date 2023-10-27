import {
  backgroundStyle,
  imgSection,
  logoImg,
  startCard,
} from './StartPage.styles';
import { useTheme } from '@emotion/react';
import Logo from '@assets/images/login.gif';
import { useState } from 'react';
import { LoginForm } from '@components/LoginForm/LoginForm';
import { JoinForm } from '@components/JoinForm/JoinForm';

export default function StartPage() {
  const theme = useTheme();
  const [isLoginPage, setIsLoginPage] = useState<boolean>(true);

  const goJoinPage = () => {
    setIsLoginPage(false);
  };

  const goLoginPage = () => {
    setIsLoginPage(true);
  };

  return (
    <div css={backgroundStyle(theme)}>
      <div css={startCard(theme)}>
        {isLoginPage ? (
          <LoginForm goJoinForm={goJoinPage} />
        ) : (
          <JoinForm goLoginPage={goLoginPage} />
        )}
        <div css={imgSection(theme)}>
          <img css={logoImg(theme)} src={Logo} />
        </div>
      </div>
    </div>
  );
}
