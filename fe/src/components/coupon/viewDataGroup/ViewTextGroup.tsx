import {
  viewDataGroupTitleStyle,
  viewDataGroupTopDivStyle,
  contentStyle,
} from './ViewDataGroup.style';
import { ViewDataGroupSize } from './ViewDataGroupType';

type Props = {
  title: string;
  content: string;
  size: ViewDataGroupSize;
};

export default function ViewTextGroup({ title, content, size }: Props) {
  return (
    <div css={viewDataGroupTopDivStyle(size)}>
      <div css={viewDataGroupTitleStyle(size)}>{title}</div>
      <div>
        <div css={contentStyle(size)}>{content}</div>
      </div>
    </div>
  );
}
