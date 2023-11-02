import { API_ENDPOINT } from '@constants/endpoints';
import { privateApi } from '.';
import { useQuery } from '@tanstack/react-query';
import { QUERY_KEYS } from './QueryKey';

type CouponGroupSummaryListType = SummaryList[];

type SummaryList = {
  id: number;
  title: string;
};

const getCouponGroupSummaryList = async () => {
  const { data } = await privateApi.get(API_ENDPOINT.COUPON_GROUP_LIST);
  return data;
};

export const useCouponGroupSummaryList = () => {
  return useQuery<CouponGroupSummaryListType>(
    QUERY_KEYS.COUPON_GROUP_LIST(),
    () => getCouponGroupSummaryList()
  );
};
