import { css } from "@emotion/react";

const cardStyle = css`
    width: 1080px;
    flex-shrink: 0;
    border-radius: 8px;
    background: #FFF;
    box-shadow: 0px 10px 13px 0px rgba(0, 0, 0, 0.05);
    padding: 30px;
    display: flex;
    flex-direction: column;
    gap: 20px;
    margin: 30px 0 auto 0;
`;

const inputBoxGropupFooterStyle = css`
    display: flex;
    justify-content: flex-end;
    gap: 15px;
    padding: 0 30px;
`;

const requiredIndicationStyle = css`
    color: red;
    margin: 0 4px;
`;

export {
    cardStyle,
    inputBoxGropupFooterStyle,
    requiredIndicationStyle,
}
