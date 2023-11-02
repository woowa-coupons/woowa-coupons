import { useTheme } from '@emotion/react';
import {
  buttonSection,
  loginForm,
  loginSection,
  logo,
} from './LoginForm.styles';
import { LabelInput } from '@components/common/LabelInput/LabelInput';
import { Button } from '@components/common/Button/Button';
import { useInput } from '@hooks/useInput';
import { buttonStyles } from '@components/common/Button/Button.styles';
import { isValidEmail } from '@utils/isValidEmail';
import { usePageNavigator } from '@hooks/usePageNavigator';

type LoginFormProps = {
  goJoinForm: () => void;
};

export function LoginForm({ goJoinForm }: LoginFormProps) {
  const theme = useTheme();
  const { navigateToPromotion } = usePageNavigator();
  const { value: id, onChange: onChangeId } = useInput();
  const { value: password, onChange: onChangePassword } = useInput();

  return (
    <div css={loginSection()}>
      <h1 css={logo(theme)}>우아한 프로모션</h1>
      <div css={loginForm()}>
        <LabelInput
          id="email"
          label="아이디(이메일)"
          type="email"
          placeholder="예시) woowa-coupon@baemin.com"
          value={id}
          hasError={!isValidEmail(id)}
          errorMsg={'id'}
          onChange={onChangeId}
        />
        <LabelInput
          id="password"
          label="비밀번호"
          type="password"
          placeholder="비밀번호를 입력하세요"
          value={password}
          errorMsg="password"
          onChange={onChangePassword}
        />
      </div>
      <div css={buttonSection()}>
        <Button css={buttonStyles.textLink(theme)} onClick={goJoinForm}>
          관리자 등록하기
        </Button>
        <Button onClick={navigateToPromotion}>로그인</Button>
      </div>
    </div>
  );
}
