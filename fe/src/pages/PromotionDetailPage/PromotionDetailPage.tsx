import { useTheme } from '@emotion/react';
import {
  bannerImg,
  content,
  contents,
  contentsItem,
  couponSection,
  page,
  textArea,
  title,
} from './PromotionDetailPage.styles';
import { PromotionCard } from '@components/PromotionCard/PromotionCard';

type PromotionOptionType = {
  memberType: string;
  lastOrderAt: string | null;
  isRandom: boolean;
  couponGroup: {
    id: number;
    title: string;
  };
};

type PromotionDetail = {
  title: string;
  content: string;
  bannerUrl: string;
  startedAt: string;
  finishedAt: string;
  isDisplay: boolean;
  progressStatus: string;
  promotionPageUrl: string;
  promotionOptions: PromotionOptionType[];
};

const data: PromotionDetail = {
  title: '프로모션 제목',
  content: '프로모션 내용',
  bannerUrl:
    'https://lwi.nexon.com/m_kartrush/event/2022/0907_baemin_21CF67D8C8A953B2/meta.jpg',
  startedAt: '2023-10-06T14:30:00Z',
  finishedAt: '2023-10-16T14:30:00Z',
  isDisplay: true,
  progressStatus: '진행중',
  promotionPageUrl: 'http://naver.com',
  promotionOptions: [
    {
      memberType: 'oldMember',
      lastOrderAt: '2023-10-01',
      isRandom: true,
      couponGroup: {
        id: 1,
        title: '쿠폰 그룹 제목',
      },
    },
    {
      memberType: 'oldMember',
      lastOrderAt: null,
      isRandom: true,
      couponGroup: {
        id: 2,
        title: '쿠폰 그룹 제목',
      },
    },
  ],
};

type PromotionDetailPageProps = {
  promotionId: number;
};

export function PromotionDetailPage({ promotionId }: PromotionDetailPageProps) {
  const theme = useTheme();
  return (
    <div css={page}>
      <div css={contents(theme)}>
        <div css={contentsItem}>
          <div css={title(theme)}>
            <span>프로모션 ID</span>
          </div>
          <div>
            <span css={content(theme)}>{promotionId}</span>
          </div>
        </div>
        <div css={contentsItem}>
          <div css={title(theme)}>
            <span>프로모션 제목</span>
          </div>
          <div>
            <span css={content(theme)}>{data.title}</span>
          </div>
        </div>
        <div css={contentsItem}>
          <div css={title(theme)}>
            <span>배너 이미지</span>
          </div>
          <div>
            <img
              css={bannerImg}
              src={data.bannerUrl}
              alt="프로모션 배너 이미지"
            />
          </div>
        </div>
        <div css={contentsItem}>
          <div css={title(theme)}>
            <span>시작 일시</span>
          </div>
          <div>
            <span css={content(theme)}>{data.startedAt}</span>
          </div>
        </div>
        <div css={contentsItem}>
          <div css={title(theme)}>
            <span>종료 일시</span>
          </div>
          <div>
            <span css={content(theme)}>{data.startedAt}</span>
          </div>
        </div>
        <div css={contentsItem}>
          <div css={title(theme)}>
            <span>상세 내용</span>
          </div>
          <div>
            <textarea css={textArea()} readOnly>
              {data.content}
            </textarea>
          </div>
        </div>
        <div css={contentsItem}>
          <div css={title(theme)}>
            <span>지면 노출 여부</span>
          </div>
          <div>
            <span css={content(theme)}>
              {data.isDisplay ? '노출' : '미노출'}
            </span>
          </div>
        </div>
        <div css={contentsItem}>
          <div css={title(theme)}>
            <span>지면 URL</span>
          </div>
          <div>
            <span css={content(theme)}>{data.promotionPageUrl}</span>
          </div>
        </div>
        <div css={contentsItem}>
          <div css={title(theme)}>
            <span>프로모션 조건</span>
          </div>
          <div>
            <div css={couponSection}>
              {data.promotionOptions.reverse().map((item, index) => (
                <PromotionCard
                  id={index}
                  memberType={item.memberType}
                  lastOrderAt={
                    item.lastOrderAt === null ? '없음' : item.lastOrderAt
                  }
                  couponGroupTitle={item.couponGroup.title}
                />
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
