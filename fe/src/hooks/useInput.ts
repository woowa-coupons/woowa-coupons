import { useState } from 'react';

export const useInput = (initialValue: string = '') => {
  const [value, setValue] = useState<string>(initialValue);

  const onChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>
  ) => {
    setValue(e.target.value);
  };

  return {
    value,
    setValue,
    onChange,
  };
};
