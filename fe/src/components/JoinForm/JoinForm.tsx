import { Button } from '@components/common/Button/Button';
import { Icon } from '@components/common/Icon/Icon';
import { useTheme } from '@emotion/react';
import {
  buttonSection,
  headerSection,
  headerTitle,
  joinForm,
  joinPage,
} from './JoinForm.styles';
import { LabelInput } from '@components/common/LabelInput/LabelInput';
import { useInput } from '@hooks/useInput';
import { buttonStyles } from '@components/common/Button/Button.styles';

type JoinFormProps = {
  goLoginPage: () => void;
};

export function JoinForm({ goLoginPage }: JoinFormProps) {
  const theme = useTheme();
  const { value: id, onChange: onChangeId } = useInput();
  const { value: password, onChange: onChangePassword } = useInput();
  const { value: checkPassword, onChange: onChangeCheckPassword } = useInput();
  const { value: nickname, onChange: onChangeNickname } = useInput();

  return (
    <div css={joinPage()}>
      <div css={headerSection()}>
        <Button css={buttonStyles.onlyIconLarge()} onClick={goLoginPage}>
          <Icon name="chevronLeft" size="XL" fill="WHITE" stroke="GRAY_700" />
        </Button>
        <h1 css={headerTitle(theme)}>관리자 등록하기</h1>
      </div>
      <div css={joinForm()}>
        <LabelInput
          id="email"
          label="아이디(이메일)"
          type="email"
          placeholder="예시) woowa-coupon@baemin.com"
          value={id}
          errorMsg="id"
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
        <LabelInput
          id="checkPassword"
          label="비밀번호 확인"
          type="password"
          placeholder="비밀번호를 한번 더 입력하세요"
          value={checkPassword}
          hasError={password !== checkPassword}
          errorMsg="password"
          onChange={onChangeCheckPassword}
        />
        <LabelInput
          id="nickname"
          label="닉네임"
          type="text"
          placeholder="닉네임을 입력해주세요"
          value={nickname}
          errorMsg="password"
          onChange={onChangeNickname}
        />
      </div>
      <div css={buttonSection()}>
        <Button onClick={goLoginPage}>등록하기</Button>
      </div>
    </div>
  );
}
