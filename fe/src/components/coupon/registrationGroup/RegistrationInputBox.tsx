import CustomButton from '@components/common/button/CustomButton';
import SelectInputGroup from '../inputGroup/SelectInputGroup';
import TextInputGroup from '../inputGroup/TextInputGroup';
import {
  registrationItemInputGroupDivStyle,
  registrationItemTopDivStyle,
} from './CouponRegistrationGroup.style';
import { Coupon } from '../CouponType';
import { useInput } from '@hooks/useInput';
import { inputBoxGropupFooterStyle } from '../CouponGroup.style';
import { useState } from 'react';
import { Icon } from '@components/common/Icon/Icon';

type Props = {
  coupons: Coupon[],
  onCreate: (coupon: Coupon) => void;
};

export default function RegistrationInputBox(props: Props) {
  const { value: title, setValue: setTitle, onChange: onTitleChange } = useInput();
  const { value: type, setValue: setType, onChange: onTypeChange } = useInput();
  const { value: discount, setValue: setDiscount, onChange: onDiscountChange } = useInput();
  const { value: initialQuantity, setValue: setQuantity, onChange: onQuantityChange } = useInput();

  const [couponsId, setCouponsId] = useState(0);

  /** 쿠폰 생성 Form 초기화 하기 */
  const handleResetButtonClick = () => {
    setTitle('');
    setType('');
    setDiscount('');
    setQuantity('');
  };

  /** 쿠폰 생성하기 */
  const handleCouponCreateButtonClick = () => {
    const coupon: Coupon = {
      id: couponsId,
      title: title,
      type: type,
      discount: parseFloat(discount.replace(/,/g, '')),
      quantity: parseFloat(initialQuantity.replace(/,/g, '')),
    }

    props.onCreate(coupon);
    setCouponsId(couponsId+1);
  };

  return (
    <div>
      <div css={registrationItemTopDivStyle}>
        <div css={registrationItemInputGroupDivStyle}>
          <TextInputGroup
            title={'쿠폰 이름'}
            required={false}
            value={title}
            placeHolder={'쿠폰 이름을 입력해 주세요.'}
            inputSize={'small'}
            onChange={onTitleChange}
          />

          <SelectInputGroup
            title={'쿠폰 타입'}
            required={false}
            value={type}
            placeholder={'쿠폰 타입을 선택해 주세요.'}
            options={[{text:"정액 쿠폰", value:"정액"}, {text:"정률 쿠폰", value:"정률"}]}
            inputGroupSize={'small'}
            onChange={onTypeChange}
          />

          <TextInputGroup
            title={'금액/퍼센트'}
            required={false}
            value={discount}
            placeHolder={'금액/퍼센트를 입력해 주세요.'}
            inputSize={'small'}
            onChange={onDiscountChange}
          />

          <TextInputGroup
            title={'수량'}
            required={false}
            value={initialQuantity}
            placeHolder={'수량을 입력해 주세요.'}
            inputSize={'small'}
            onChange={onQuantityChange}
          />
        </div>
      </div>
      <div css={inputBoxGropupFooterStyle}>
        <CustomButton
          icon={<Icon name={'refresh'} />}
          title={'초기화'}
          size={'medium'}
          color={'white'}
          onButtonClick={handleResetButtonClick}
        />
        <CustomButton
          title={'쿠폰 추가'}
          size={'medium'}
          color={'blue'}
          onButtonClick={handleCouponCreateButtonClick}
        />
      </div>
    </div>
  );
}
