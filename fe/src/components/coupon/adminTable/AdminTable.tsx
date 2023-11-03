import { Icon } from '@components/common/Icon/Icon';
import { CouponGroup } from '../common/CouponType';
import { adminTableDataStyle, adminTableHeaderStyle, adminTableStyle } from './AdminTable.style';
import { TableHeader } from './AdminTableType';
import {PATH} from "@constants/path.ts";
import {detailLink} from "@components/PromotionTable/PromotionTable.styles.ts";
import {theme} from "@styles/theme.ts";

type Props = {
  headers: TableHeader[];
  couponGroups: CouponGroup[];
};

export default function AdminTable({ headers, couponGroups }: Props) {
  return (
    <div>
      <table css={adminTableStyle}>
        <thead>
          <tr>
            <th css={adminTableHeaderStyle(5)}>
              <input type="checkbox"></input>
            </th>
            {headers.map((header, index) => (
              <th css={adminTableHeaderStyle(header.widthRatio)} key={index}>
                {header.title}
              </th>
            ))}
            <th css={adminTableHeaderStyle(10)}></th>
          </tr>
        </thead>
        <tbody>
          {couponGroups.map((group, index) => (
            <tr key={index}>
              <td css={adminTableDataStyle}>
                <input type="checkbox"></input>
              </td>
              <td css={adminTableDataStyle}>{group.id}</td>
              <td css={adminTableDataStyle}>
                <a href={PATH.COUPON.DETAIL(group.id)} css={detailLink(theme)}>{group.title}</a>
              </td>
              <td css={adminTableDataStyle}>{group.couponTitles}</td>
              <td css={adminTableDataStyle}>{group.totalRemainQuantity}</td>
              <td css={adminTableDataStyle}>{group.totalInitialQuantity}</td>
              <td css={adminTableDataStyle}>{group.createdBy}</td>
              <td css={adminTableDataStyle}>
                <div>
                  <Icon name={'edit'} fill={'WHITE'} ></Icon>
                  <Icon name={'trash'} fill={'WHITE'} ></Icon>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
