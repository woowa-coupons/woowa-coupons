/** @jsxImportSource @emotion/react */
import { Icon } from '@components/common/Icon/Icon';
import { Coupon, CouponType } from '../CouponType';
import SelectInputGroup from '../inputGroup/SelectInputGroup';
import TextInputGroup from '../inputGroup/TextInputGroup';
import {
  closeIconDiv,
  registrationItemIndexDivStyle,
  registrationItemInputGroupDivStyle,
  registrationItemTopDivStyle,
  registrationItemVerticalLineStyle,
} from './CouponRegistrationGroup.style';

type Props = {
  index: number;
  coupon: Coupon;
  // onEdit: (couponId: number, newCoupon: Coupon) => void; TODO: 수정기능 만들기
  onDelete: (couponId: number) => void;
};

export default function CouponRegistrationItem({
  index,
  coupon,
  onDelete,
}: Props) {
  const handleDeleteButtonClick = () => {
    onDelete(coupon.id);
    return;
  };

  return (
    <div css={registrationItemTopDivStyle}>
      <div css={registrationItemIndexDivStyle}>#{index}</div>
      <div css={registrationItemVerticalLineStyle}></div>
      <div css={registrationItemInputGroupDivStyle}>
        <TextInputGroup
          title={'쿠폰 이름'}
          required={false}
          placeHolder={'쿠폰 이름을 입력해 주세요.'}
          inputSize={'small'}
          value={coupon.title}
        />

        <SelectInputGroup
          title={'쿠폰 타입'}
          required={false}
          placeholder={'쿠폰 타입을 선택해 주세요.'}
          options={CouponType}
          inputGroupSize={'small'}
          value={coupon.type}
        />

        <TextInputGroup
          title={'금액/퍼센트'}
          required={false}
          placeHolder={'금액/퍼센트를 입력해 주세요.'}
          inputSize={'small'}
          value={coupon.discount.toString()}
        />

        <TextInputGroup
          title={'수량'}
          required={false}
          placeHolder={'수량을 입력해 주세요.'}
          inputSize={'small'}
          value={coupon.quantity.toString()}
        />
      </div>

      <div css={closeIconDiv} onClick={handleDeleteButtonClick}>
        <Icon name={'x'} size={'M'}></Icon>
      </div>
    </div>
  );
}
