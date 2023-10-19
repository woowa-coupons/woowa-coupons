import { css } from '@emotion/react';

export const shadow = {
  default: '0px 10px 13px 0px rgba(0, 0, 0, 0.05);',
  modal: '10px 10px 13px 0px rgba(0, 0, 0, 0.05);',
};

export const radius = {
  small: '4px',
  medium: '8px',
  large: '25px',
};

export const flex = {
  row: css`
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
  `,
  column: css`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  `,
};
