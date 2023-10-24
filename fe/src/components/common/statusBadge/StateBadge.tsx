/** @jsxImportSource @emotion/react */
import { StateBadgeTypes, stateBadgeStyle } from "./StateBadge.styles";

type Props = {
    type: keyof typeof StateBadgeTypes,
}

export default function StateBadge(props: Props) {

    return (
        <div
            css={stateBadgeStyle(props.type)}>
            {StateBadgeTypes[props.type].title}
        </div>
    );
}
