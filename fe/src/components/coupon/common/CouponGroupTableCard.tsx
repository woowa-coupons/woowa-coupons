import {useEffect, useState} from 'react';
import AdminTable from '../adminTable/AdminTable';
import {HeaderData} from '../adminTable/AdminTableType';
import {cardWithoutPaddingStyle} from './CouponGroup.style';
import {CouponGroup} from './CouponType';

export default function CouponGroupTableCard() {

  const [couponGroups, setCouponGroups] = useState<CouponGroup[]>([]);

  useEffect(() => {
    (async () => {
      const data = await fetchData();
      setCouponGroups(data.data);
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
    const url = new URL(`/admin/coupon-groups/?page=1&size=10`, baseUrl);
    const response = await fetch(url, options);

    if (!response.ok) {
      throw new Error('Failed to fetch');
    }

    return await response.json();
  };

  return (
    <div css={cardWithoutPaddingStyle}>
      <AdminTable headers={HeaderData} couponGroups={couponGroups} />
    </div>
  );
}
