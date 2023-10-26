/** @jsxImportSource @emotion/react */
import { cardStyle, inputBoxGropupFooterStyle } from './CouponGroup.style';
import CouponRegistrationGroup from './registrationGroup/CouponRegistrationGroup';
import DateInputGroup from './inputGroup/DateInputGroup';
import TextInputGroup from './inputGroup/TextInputGroup';
import { useInput } from '@hooks/useInput';
import { useCoupon } from '@components/coupon/useCoupons';
import SelectInputGroup from './inputGroup/SelectInputGroup';
import CustomButton from '@components/common/button/CustomButton';
import { Icon } from '@components/common/Icon/Icon';

export default function CouponGroupCard() {
  const {
    value: groupName,
    setValue: setGroupName,
    onChange: onGroupNameChange,
  } = useInput();
  const {
    value: startedAt,
    setValue: setStaredAt,
    onChange: onStartedAtChange,
  } = useInput();
  const {
    value: finishedAt,
    setValue: setFinishedAt,
    onChange: onFinishedAtChange,
  } = useInput();
  const {
    value: isRandom,
    setValue: setIsRandom,
    onChange: onIsRandomChange,
  } = useInput();
  const { coupons, setCoupons, onCreate, onEdit, onDelete } = useCoupon();

  /** 쿠폰 생성 Form 초기화 하기 */
  const handleResetButtonClick = () => {
    setGroupName('');
    setStaredAt('');
    setFinishedAt('');
    setIsRandom('');
    setCoupons([]);
  };

  const handleSubmitButtonCLick = () => {
    console.log(
      '그룹이름:',
      groupName,
      '\n',
      startedAt,
      '\n',
      finishedAt,
      '\n',
      isRandom,
      '\n',
      coupons
    );
  };

  return (
    <div css={cardStyle}>
      <TextInputGroup
        title={'쿠폰 그룹 이름'}
        required={true}
        type={'text'}
        placeHolder={'쿠폰 그룹 이름을 입력해 주세요.'}
        value={groupName}
        onChange={onGroupNameChange}
        inputSize={'medium'}
      />

      <DateInputGroup
        title={'발급 시작 일자'}
        required={true}
        value={startedAt}
        onChange={onStartedAtChange}
        inputGroupSize={'medium'}
      />

      <DateInputGroup
        title={'발급 종료 일자'}
        required={true}
        value={finishedAt}
        onChange={onFinishedAtChange}
        min={startedAt}
        inputGroupSize={'medium'}
      />

      <SelectInputGroup
        title={'랜덤 발급 여부'}
        required={true}
        value={isRandom}
        placeholder={'쿠폰 타입을 선택해 주세요.'}
        options={[
          { text: '일반 발급', value: '정액' },
          { text: '랜덤 발급', value: '정률' },
        ]}
        inputGroupSize={'medium'}
        onChange={onIsRandomChange}
      />

      <CouponRegistrationGroup
        title={'쿠폰 등록'}
        required={true}
        coupons={coupons}
        onCreate={onCreate}
        onEdit={onEdit}
        onDelete={onDelete}
      />

      <div css={inputBoxGropupFooterStyle}>
        <CustomButton
          icon={<Icon name="refresh" />}
          title={'초기화'}
          size={'medium'}
          color={'white'}
          onButtonClick={handleResetButtonClick}
        />

        <CustomButton
          icon={<Icon name="plus" stroke="WHITE" fill="WHITE" />}
          title={'추가'}
          size={'medium'}
          color={'blue'}
          onButtonClick={handleSubmitButtonCLick}
        />
      </div>
    </div>
  );
}
