import Calendar from '@assets/icons/calendar.svg?react';
import Chart from '@assets/icons/chart.svg?react';
import ChevronDown from '@assets/icons/chevron-down.svg?react';
import ChevronLeft from '@assets/icons/chevron-left.svg?react';
import ChevronRight from '@assets/icons/chevron-right.svg?react';
import ChevronUp from '@assets/icons/chevron-up.svg?react';
import Dots from '@assets/icons/dots.svg?react';
import Edit from '@assets/icons/edit.svg?react';
import Grid from '@assets/icons/grid.svg?react';
import Home from '@assets/icons/home.svg?react';
import Plus from '@assets/icons/plus.svg?react';
import Refresh from '@assets/icons/refresh.svg?react';
import Search from '@assets/icons/search.svg?react';
import Trash from '@assets/icons/trash.svg?react';
import X from '@assets/icons/x.svg?react';
import { theme } from '@styles/theme';
import React from 'react';

const icons = {
  calendar: Calendar,
  chart: Chart,
  chevronDown: ChevronDown,
  chevronLeft: ChevronLeft,
  chevronRight: ChevronRight,
  chevronUp: ChevronUp,
  dots: Dots,
  edit: Edit,
  grid: Grid,
  home: Home,
  plus: Plus,
  refresh: Refresh,
  search: Search,
  trash: Trash,
  x: X,
};

type ThemeColors = keyof typeof theme.colors;

type IconProps = {
  name: keyof typeof icons;
  size?: 'S' | 'M' | 'L' | 'XL';
  fill?: ThemeColors;
  stroke?: ThemeColors;
};

export const Icon: React.FC<IconProps> = function ({
  name,
  size = 'S',
  fill = 'BLACK',
  stroke = 'BLACK',
  ...props
}) {
  const IconComponent = icons[name];
  if (!IconComponent) return null;

  const iconSize =
    size === 'S'
      ? '16px'
      : size === 'M'
      ? '20px'
      : size === 'L'
      ? '24px'
      : '40px';

  return (
    <IconComponent
      width={iconSize}
      height={iconSize}
      fill={theme.colors[fill]}
      stroke={theme.colors[stroke]}
      {...props}
    />
  );
};
