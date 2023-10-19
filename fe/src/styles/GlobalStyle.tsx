import { Global, css } from '@emotion/react';
import emotionReset from 'emotion-reset';

const global = () => css`
  ${emotionReset}

  @font-face {
    font-family: 'BMHANNAPro';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_seven@1.0/BMHANNAPro.woff')
      format('woff');
    font-weight: normal;
    font-style: normal;
  }

  *,
  *::after,
  *::before {
    margin: 0;

    box-sizing: border-box;
  }
`;

export function GlobalStyle() {
  return <Global styles={global} />;
}
