import {useEffect, useState} from 'react';
import ViewBoxGroup from '../viewDataGroup/ViewBoxGroup';
import ViewTextGroup from '../viewDataGroup/ViewTextGroup';
import {cardStyle} from './CouponGroup.style';
import {Coupon} from './CouponType';
import {formatDate} from "@utils/formatData.ts";

type Props = {
  couponGroupId: number,
}

export default function CouponGroupDetailCard({couponGroupId}: Props) {
  const [title, setTitle] = useState('');
  const [startedAt, setStartedAt] = useState('');
  const [finishedAt, setFinishedAt] = useState('');
  const [type, setType] = useState('');
  const [isRandom, setIsRandom] = useState('');
  const [coupons, setCoupons] = useState<Coupon[]>([]);

  useEffect(() => {
    (async () => {
      const data = await fetchData();
      setTitle(data.title);
      setStartedAt(data.startedAt);
      setFinishedAt(data.finishedAt);
      setType(data.type);
      setIsRandom(data.isRandom);
      setCoupons(data.coupons);
    })();
  }, []);

  const fetchData = async () => {
    const baseUrl = import.meta.env.VITE_BASE_URL;
    const options = {
      method : "GET",
      headers: {
        'Authorization': 'Bearer ' + import.meta.env.VITE_ACCESS_TOKEN_FOR_TEST,
        'Content-Type': 'application/json',
      },
    };
    const url = new URL(`/admin/coupon-groups/` + couponGroupId, baseUrl);
    const response = await fetch(url, options);

    if (!response.ok) {
      throw new Error('Failed to fetch');
    }

    return await response.json();
  };

  return (
    <div css={cardStyle}>
      <ViewTextGroup title={'쿠폰 그룹 No.'} content={'1'} size={'medium'} />

      <ViewTextGroup title={'쿠폰 그룹 이름'} content={title} size={'medium'} />

      <ViewTextGroup title={'발급 시작 일자'} content={formatDate(startedAt)} size={'medium'} />

      <ViewTextGroup title={'발급 종료 일자'} content={formatDate(finishedAt)} size={'medium'} />

      <ViewTextGroup title={'랜덤 발급 여부'} content={isRandom? '랜덤 발급' : 'X'} size={'medium'} />

      <ViewTextGroup title={'쿠폰 그룹 타입'} content={type? '일일 지급 쿠폰' : '일일 지급 쿠폰'} size={'medium'} />

      <ViewBoxGroup title={'등록된 쿠폰'} content={coupons} />
    </div>
  );
}
