import { SerializedStyles } from '@emotion/react';

type ColorsType = {
  [key: string]: string;
};

type FontsType = {
  [key: string]: string;
};

type RadiusType = {
  [key: string]: string;
};

type FlexType = Record<'row' | 'column', SerializedStyles>;

type ShadowType = Record<'default' | 'modal', SerializedStyles>;

declare module '@emotion/react' {
  export interface Theme {
    colors: ColorsType;
    fonts: FontsType;
    flex: FlexType;
    shadow: ShadowType;
    radius: RadiusType;
  }
}
