import { API_ENDPOINT } from '@constants/endpoints';
import { privateApi } from '.';
import { QUERY_KEYS } from './QueryKey';
import { useQuery } from '@tanstack/react-query';

type CouponGroupType = {
  id: number;
  title: string;
};

type CouponGroupList = CouponGroupType[];

const getCouponList = async () => {
  const { data } = await privateApi.get(API_ENDPOINT.COUPON_GROUP_LIST);
  return data;
};

export const useCouponGroupList = () => {
  return useQuery<CouponGroupList>(QUERY_KEYS.COUPON_GROUP_LIST(), () =>
    getCouponList()
  );
};
