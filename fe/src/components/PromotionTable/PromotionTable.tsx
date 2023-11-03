import { useTheme } from '@emotion/react';
import {
  bannerImg,
  detailLink,
  header,
  headerTitle,
  promotionItem,
  sectionStyles,
  table,
  tableBody,
} from './PromotionTable.styles';
import { promotionTable } from '@constants/promotionTable';
import { Button } from '@components/common/Button/Button';
import { Icon } from '@components/common/Icon/Icon';
import { buttonStyles } from '@components/common/Button/Button.styles';
import { formatDate } from '@utils/formatData';

export type PromotionListItem = {
  id: number;
  bannerUrl: string;
  title: string;
  startedAt: string;
  finishedAt: string;
  progressStatus: string;
};

export function PromotionTable({ data }: { data: PromotionListItem[] }) {
  const theme = useTheme();

  return (
    <table css={table(theme)}>
      <thead>
        <tr css={header(theme)}>
          <td css={sectionStyles.checkbox}>
            <input type="checkbox" />
          </td>
          {promotionTable.map((item) => (
            <td key={item.name} css={headerTitle(item.size)}>
              <span>{item.name}</span>
            </td>
          ))}
        </tr>
      </thead>
      <tbody css={tableBody(theme)}>
        {data &&
          data.map((item: PromotionListItem) => (
            <tr key={item.id.toString()} css={promotionItem(theme, false)}>
              <td css={sectionStyles.checkbox}>
                <input type="checkbox" />
              </td>
              <td css={sectionStyles.no}>
                <span>{item.id}</span>
              </td>
              <td css={sectionStyles.banner}>
                <img
                  css={bannerImg}
                  src={item.bannerUrl}
                  alt="프로모션 배너 이미지"
                />
              </td>
              <td css={sectionStyles.name}>
                <a href={`/promotion/${item.id}`} css={detailLink(theme)}>
                  <span>{item.title}</span>
                </a>
              </td>
              <td css={sectionStyles.startDate}>
                <span>{formatDate(item.startedAt)}</span>
              </td>
              <td css={sectionStyles.endDate}>
                <span>{formatDate(item.finishedAt)}</span>
              </td>
              <td css={sectionStyles.status}>
                <span>{item.progressStatus}</span>
              </td>
              <td css={sectionStyles.writer}>
                <span>litae</span>
              </td>
              <td css={sectionStyles.buttons}>
                <Button cssProp={buttonStyles.onlyIconLarge()}>
                  <Icon name="chart" fill="WHITE" stroke="GRAY_400" />
                </Button>
                <Button cssProp={buttonStyles.onlyIconLarge()}>
                  <Icon name="edit" fill="WHITE" stroke="GRAY_400" />
                </Button>
                <Button cssProp={buttonStyles.onlyIconLarge()}>
                  <Icon name="trash" fill="WHITE" stroke="GRAY_400" />
                </Button>
              </td>
            </tr>
          ))}
      </tbody>
    </table>
  );
}
