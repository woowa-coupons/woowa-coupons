import { useTheme } from '@emotion/react';
import {
  header,
  headerTitle,
  promotionItem,
  sectionStyles,
  table,
  tableBody,
} from './PromotionTable.styles';
import { promotionTable } from '@constants/promotionTable';

export function PromotionTableFallback() {
  const theme = useTheme();
  const emptyItem = new Array(10).fill(0);

  return (
    <table css={table(theme)}>
      <thead>
        <tr css={header(theme)}>
          <td css={sectionStyles.checkbox}></td>
          {promotionTable.map((item) => (
            <td key={item.name} css={headerTitle(item.size)}>
              <span>{item.name}</span>
            </td>
          ))}
        </tr>
      </thead>
      <tbody css={tableBody(theme)}>
        {emptyItem.map((_, index) => (
          <tr key={index} css={promotionItem(theme, true)}></tr>
        ))}
      </tbody>
    </table>
  );
}
