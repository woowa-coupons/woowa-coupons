import { css } from "@emotion/react";
import { colors } from "@styles/base/Color";
import { fonts } from "@styles/base/Font";
import { radius } from "@styles/base/Object";

const StateBadgeTypes = {
    PROCEEDING: {title: "진행중", color: colors.GREEN},
    EXPECTED: {title: "예정", color: colors.BLUE},
    TERMINATED: {title: "종료", color: colors.GRAY_900},
}

const stateBadgeStyle = (type: keyof typeof StateBadgeTypes) => css`
    width: 54px;
    height: 22px;
    flex-hrink: 0;
    border-radius: ${radius.medium};
    background: ${StateBadgeTypes[type].color};

    text-align: center;
    color: ${colors.WHITE};
    font: ${fonts.regular16};
    line-height: 22px;
    letter-spacing: 0.12px;
`;

export {StateBadgeTypes, stateBadgeStyle};
