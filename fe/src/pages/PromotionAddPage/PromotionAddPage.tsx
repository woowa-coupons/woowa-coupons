import { useTheme } from '@emotion/react';
import {
  buttonSection,
  cardSection,
  dateInput,
  inputSection,
  page,
  promotionSetting,
  required,
  selectorStyles,
  settingButtonSection,
  settingInputSection,
  settingSection,
  settingTitle,
  table,
  tableItem,
  titleSection,
} from './PromotionAddPage.styles';
import { Input } from '@components/common/Input/Input';
import { useInput } from '@hooks/useInput';
import { useState } from 'react';
import { Button } from '@components/common/Button/Button';
import { buttonStyles } from '@components/common/Button/Button.styles';
import { Icon } from '@components/common/Icon/Icon';
import { PromotionCard } from '@components/PromotionCard/PromotionCard';
import { usePageNavigator } from '@hooks/usePageNavigator';

type PromotionOptions = PromotionOptionType[];

type PromotionOptionType = {
  memberType: string;
  lastOrderAt: string;
  couponGroupTitle: string;
};

// to do: 컴포넌트로 분리

export function PromotionAddPage() {
  const theme = useTheme();
  const { navigateToPromotion } = usePageNavigator();
  const { value: title, onChange: onChangeTitle } = useInput();
  const { value: bannerUrl, onChange: onChangeBannerUrl } = useInput();
  const { value: startedAt, onChange: onChangeStartedAt } = useInput();
  const { value: finishedAt, onChange: onChangeFinishedAt } = useInput();
  const { value: promotionUrl, onChange: onChangePromotionUrl } = useInput();
  const [isDisplay, setIsDisplay] = useState<string>('노출');
  const [promotionStatus, setPromotionStatus] = useState<string>('예정');
  const [memberType, setMemberType] = useState<string>();
  const [couponGroup, setCouponGroup] = useState<PromotionOptions>([
    {
      memberType: 'new',
      lastOrderAt: '2023.10.23',
      couponGroupTitle: '10주년',
    },
    {
      memberType: 'new',
      lastOrderAt: '2023.10.23',
      couponGroupTitle: '10주년',
    },
    {
      memberType: 'new',
      lastOrderAt: '2023.10.23',
      couponGroupTitle: '10주년',
    },
    {
      memberType: 'new',
      lastOrderAt: '2023.10.23',
      couponGroupTitle: '10주년',
    },
    {
      memberType: 'new',
      lastOrderAt: '2023.10.23',
      couponGroupTitle: '10주년',
    },
  ]);

  const handleDisplayOption = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setIsDisplay(e.target.value);
  };

  const handlePromotionStatus = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setPromotionStatus(e.target.value);
  };

  const handlePromotionMemberType = (
    e: React.ChangeEvent<HTMLSelectElement>
  ) => {
    setMemberType(e.target.value);
  };

  return (
    <div css={page()}>
      <div css={table(theme)}>
        <div css={tableItem}>
          <div css={titleSection(theme)}>
            <span>프로모션 제목</span>
            <span css={required(theme)}>*</span>
          </div>
          <div css={inputSection}>
            <Input
              value={title}
              placeholder="프로모션 제목을 입력해주세요"
              onChange={onChangeTitle}
            />
          </div>
        </div>
        <div css={tableItem}>
          <div css={titleSection(theme)}>
            <span>배너 이미지</span>
            <span css={required(theme)}>*</span>
          </div>
          <div css={inputSection}>
            <Input
              value={bannerUrl}
              placeholder="배너 이미지 URL을 입력해주세요"
              onChange={onChangeBannerUrl}
            />
          </div>
        </div>
        <div css={tableItem}>
          <div css={titleSection(theme)}>
            <span>시작 일시</span>
            <span css={required(theme)}>*</span>
          </div>
          <div css={inputSection}>
            <input
              css={dateInput(theme)}
              value={startedAt}
              type="datetime-local"
              onChange={onChangeStartedAt}
            />
          </div>
        </div>
        <div css={tableItem}>
          <div css={titleSection(theme)}>
            <span>종료 일시</span>
            <span css={required(theme)}>*</span>
          </div>
          <div css={inputSection}>
            <input
              css={dateInput(theme)}
              value={finishedAt}
              type="datetime-local"
              onChange={onChangeFinishedAt}
            />
          </div>
        </div>
        <div css={tableItem}>
          <div css={titleSection(theme)}>
            <span>상세 내용</span>
          </div>
          <div css={inputSection}>
            <textarea />
          </div>
        </div>
        <div css={tableItem}>
          <div css={titleSection(theme)}>
            <span>지면 URL</span>
            <span css={required(theme)}>*</span>
          </div>
          <div css={inputSection}>
            <Input
              value={promotionUrl}
              placeholder="지면 URL을 입력해주세요.   예시) https://woowa-coupon.com/promotion/1"
              onChange={onChangePromotionUrl}
            />
          </div>
        </div>
        <div css={tableItem}>
          <div css={titleSection(theme)}>
            <span>노출 여부</span>
            <span css={required(theme)}>*</span>
          </div>
          <div css={inputSection}>
            <select
              css={selectorStyles(theme)}
              value={isDisplay}
              onChange={handleDisplayOption}
            >
              <option value="노출">노출</option>
              <option value="미노출">미노출</option>
            </select>
          </div>
        </div>
        <div css={tableItem}>
          <div css={titleSection(theme)}>
            <span>진행 상태</span>
            <span css={required(theme)}>*</span>
          </div>
          <div css={inputSection}>
            <select
              css={selectorStyles(theme)}
              value={promotionStatus}
              onChange={handlePromotionStatus}
            >
              <option value="예정">예정</option>
              <option value="진행중">진행중</option>
              <option value="종료">종료</option>
            </select>
          </div>
        </div>
        <div css={tableItem}>
          <div css={titleSection(theme)}>
            <span>프로모션 조건 설정</span>
            <span css={required(theme)}>*</span>
          </div>
          <div css={settingSection}>
            <div css={settingInputSection}>
              <div css={promotionSetting}>
                <div css={settingTitle}>
                  <span>회원 타입</span>
                </div>
                <div>
                  <select
                    css={selectorStyles(theme)}
                    value={memberType}
                    placeholder="회원 타입을 설정해주세요"
                    onChange={handlePromotionMemberType}
                  >
                    <option disabled hidden selected>
                      회원 타입을 설정해주세요
                    </option>
                    <option value="예정">신규 회원</option>
                    <option value="진행중">모든 회원</option>
                  </select>
                </div>
              </div>
              <div css={promotionSetting}>
                <div css={settingTitle}>
                  <span>마지막 주문일</span>
                </div>
                <div>
                  <input
                    css={dateInput(theme)}
                    value={finishedAt}
                    type="date"
                    onChange={onChangeFinishedAt}
                  />
                </div>
              </div>
              <div css={promotionSetting}>
                <div css={settingTitle}>
                  <span>쿠폰 그룹</span>
                </div>
                <div>
                  <select
                    css={selectorStyles(theme)}
                    value={memberType}
                    onChange={handlePromotionMemberType}
                  >
                    <option disabled hidden selected>
                      쿠폰 그룹을 설정해주세요
                    </option>
                  </select>
                </div>
              </div>
            </div>
            <div css={settingButtonSection}>
              <Button cssProp={buttonStyles.iconWhite(theme)}>
                <Icon name="refresh" fill="WHITE" />
                <span>초기화</span>
              </Button>
              <Button cssProp={buttonStyles.iconBlue(theme)}>
                <Icon name="plus" stroke="WHITE" />
                <span>추가</span>
              </Button>
            </div>
            <div css={cardSection(theme)}>
              {couponGroup?.map((item, index) => (
                <PromotionCard
                  key={index}
                  id={index}
                  memberType={item.memberType}
                  lastOrderAt={item.lastOrderAt}
                  couponGroupTitle={item.couponGroupTitle}
                />
              ))}
            </div>
          </div>
        </div>
        <div>
          <span css={required(theme)}>*표시는 필수 입력값입니다. </span>
          <div css={buttonSection}>
            <Button
              cssProp={buttonStyles.white(theme)}
              onClick={navigateToPromotion}
            >
              취소
            </Button>
            <Button>등록하기</Button>
          </div>
        </div>
      </div>
    </div>
  );
}
