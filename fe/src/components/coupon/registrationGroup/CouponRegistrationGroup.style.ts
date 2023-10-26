import { css } from "@emotion/react";
import { colors } from "@styles/base/Color";
import { fonts } from "@styles/base/Font";
import { flex, radius } from "@styles/base/Object";

const inputBoxGroupTopDivStyle = css`
    display: flex;
    flex-direction: row;
`;

const inputBoxGroupTitleStyle = css`
    width: 170px;
    height: 30px;
    display: flex;
    align-items: center;
`

const inputBoxGropupParagraphStyle = css`
    color: #4E4E4E;
    padding-top: 16px;
    padding-bottom: 4px;
`;

const registrationBoxStyle = css`
    width: 700px;
    flex-shrink: 0;
    border-radius: 8px;
    border: 1px solid #D0D5DD;
    background: #FFF;
    padding: 15px 10px;
`;

const registrationItemTopDivStyle = css`
    width: 680px;
    height: 128px;
    flex-shrink: 0;
    border-radius: ${radius.medium};
    border: 1px solid ${colors.GRAY};
    background: ${colors.WHITE};
    ${flex.row}
    margin-bottom: 10px;
`;

const registrationItemIndexDivStyle = css`
    width: 77px;
    height: 100%;
    ${flex.row}
    font: ${fonts.bold14};
    
`;

const registrationItemVerticalLineStyle = css`
    border-left : medium solid ${colors.GRAY};
    height : 80px;
`;

const registrationItemInputGroupDivStyle = css`
    width: 604px;
    ${flex.column}
    gap: 7px;
`;

const closeIconDiv = css`
    align-self: flex-start;
    padding: 10px;
`;

export {
    inputBoxGroupTopDivStyle,
    inputBoxGroupTitleStyle,
    inputBoxGropupParagraphStyle,
    registrationBoxStyle,
    registrationItemTopDivStyle,
    registrationItemIndexDivStyle,
    registrationItemVerticalLineStyle,
    registrationItemInputGroupDivStyle,
    closeIconDiv,
}