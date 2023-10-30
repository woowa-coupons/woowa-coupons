import { PromotionTable } from '@components/PromotionTable/PromotionTable';
import {
  contents,
  empty,
  emptyLog,
  tableCaption,
} from './PromotionPage.styles';
import { Button } from '@components/common/Button/Button';
import { buttonStyles } from '@components/common/Button/Button.styles';
import { useTheme } from '@emotion/react';
import { Icon } from '@components/common/Icon/Icon';
import { usePageNavigator } from '@hooks/usePageNavigator';
import emptyImg from '@assets/images/empty.png';

const data = [
  {
    id: 6,
    bannerUrl:
      'https://lwi.nexon.com/m_kartrush/event/2022/0907_baemin_21CF67D8C8A953B2/meta.jpg',
    title: '프로모션 제목',
    startedAt: '2023-10-06T14:30:00Z',
    finishedAt: '2023-10-16T14:30:00Z',
    progressStatus: '진행중',
  },
  {
    id: 5,
    bannerUrl:
      'https://lwi.nexon.com/m_kartrush/event/2022/0907_baemin_21CF67D8C8A953B2/meta.jpg',
    title: '프로모션 제목',
    startedAt: '2023-10-06T14:30:00Z',
    finishedAt: '2023-10-16T14:30:00Z',
    progressStatus: '종료',
  },
  {
    id: 4,
    bannerUrl:
      'https://lwi.nexon.com/m_kartrush/event/2022/0907_baemin_21CF67D8C8A953B2/meta.jpg',
    title: '프로모션 제목',
    startedAt: '2023-10-06T14:30:00Z',
    finishedAt: '2023-10-16T14:30:00Z',
    progressStatus: '종료',
  },
  {
    id: 3,
    bannerUrl:
      'https://lwi.nexon.com/m_kartrush/event/2022/0907_baemin_21CF67D8C8A953B2/meta.jpg',
    title: '프로모션 제목',
    startedAt: '2023-10-06T14:30:00Z',
    finishedAt: '2023-10-16T14:30:00Z',
    progressStatus: '종료',
  },
  {
    id: 2,
    bannerUrl:
      'https://img.a-bly.com/events/thumbnails/event_thumbnail_1681896870034220.jpg',
    title: '프로모션 제목',
    startedAt: '2023-10-06T14:30:00Z',
    finishedAt: '2023-10-16T14:30:00Z',
    progressStatus: '종료',
  },
  {
    id: 1,
    bannerUrl:
      'https://lwi.nexon.com/m_kartrush/event/2022/0907_baemin_21CF67D8C8A953B2/meta.jpg',
    title: '프로모션 제목',
    startedAt: '2023-10-06T14:30:00Z',
    finishedAt: '2023-10-16T14:30:00Z',
    progressStatus: '종료',
  },
];

// const data: PromotionList[] = [];

export function PromotionPage() {
  const theme = useTheme();
  const { navigateToPromotionAdd } = usePageNavigator();

  return (
    <div css={contents}>
      <div css={tableCaption}>
        <Button cssProp={buttonStyles.iconWhite(theme)}>
          <Icon name="filter" />
          <span>필터</span>
        </Button>
        <Button
          cssProp={buttonStyles.iconPrimary()}
          onClick={navigateToPromotionAdd}
        >
          <Icon name="plus" stroke="WHITE" />
          <span>프로모션 등록</span>
        </Button>
      </div>
      <PromotionTable data={data} />
      {data.length === 0 && (
        <div css={emptyLog(theme)}>
          <img css={empty} src={emptyImg} alt="" />
          <span>등록된 프로모션이 없습니다</span>
        </div>
      )}
    </div>
  );
}
