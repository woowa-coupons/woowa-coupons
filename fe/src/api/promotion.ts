import { API_ENDPOINT } from '@constants/endpoints';
import { privateApi } from '.';
import { useMutation, useQuery } from '@tanstack/react-query';
import { QUERY_KEYS } from './QueryKey';
import { PromotionListItem } from '@components/PromotionTable/PromotionTable';

type CouponGroupSummaryListType = SummaryList[];

type SummaryList = {
  id: number;
  title: string;
};

type NewPromotion = {
  title: string;
  content: string;
  banner: string;
  startedAt: string;
  finishedAt: string;
  isDisplay: boolean;
  promotionPageUrl: string;
  progressStatus: string;
  promotionOptions: PromotionOption[];
};

type PromotionOption = {
  memberType: string;
  lastOrderAt: string | null;
  couponGroupId: number;
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

const getPromotionList = async () => {
  const { data } = await privateApi.get(API_ENDPOINT.PROMOTION);
  return data;
};

export const usePromotionList = () => {
  return useQuery<PromotionListItem[]>(QUERY_KEYS.PROMOTION_LIST(), () =>
    getPromotionList()
  );
};

const postNewPromotion = async (newPromotionData: NewPromotion) => {
  const response = await privateApi.post(
    API_ENDPOINT.ADD_PROMOTION,
    newPromotionData
  );

  return response.data;
};

export const usePostNewPromotion = () => {
  const { mutate } = useMutation(postNewPromotion, {
    onSuccess: () => {
      location.reload();
    },
  });
  return { mutate };
};
