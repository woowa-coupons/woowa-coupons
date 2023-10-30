/** @jsxImportSource @emotion/react */
import { cardStyle, inputBoxGropupFooterStyle } from './CouponGroup.style';
import CouponRegistrationGroup from './registrationGroup/CouponRegistrationGroup';
import DateInputGroup from './inputGroup/DateInputGroup';
import TextInputGroup from './inputGroup/TextInputGroup';
import { useInput } from '@hooks/useInput';
import { useCoupon } from '@components/coupon/useCoupons';
import SelectInputGroup from './inputGroup/SelectInputGroup';
import CustomButton from '@components/common/CustomButton/CustomButton';
import { Icon } from '@components/common/Icon/Icon';
import { CouponGroupType, IsRandom } from './CouponType';

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
  const {
    value: groupType,
    setValue: setGroupType,
    onChange: onGroupTypeChange,
  } = useInput();
  const { coupons, setCoupons, onCreate, onEdit, onDelete } = useCoupon();

  /** 쿠폰 생성 Form 초기화 하기 */
  const handleResetButtonClick = () => {
    setGroupName('');
    setStaredAt('');
    setFinishedAt('');
    setIsRandom('');
    setGroupType('');
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
      groupType,
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
        options={IsRandom}
        inputGroupSize={'medium'}
        onChange={onIsRandomChange}
      />

      <SelectInputGroup
        title={'쿠폰 그룹 타입'}
        required={true}
        value={groupType}
        placeholder={'쿠폰 그룹 타입을 선택해 주세요.'}
        options={CouponGroupType}
        inputGroupSize={'medium'}
        onChange={onGroupTypeChange}
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
