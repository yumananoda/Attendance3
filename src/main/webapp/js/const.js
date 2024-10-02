export const POSITION_NAME = {
  1: "正社員",
  2: "アルバイト",
};

export const INFO = [];

export const REMOVE_USERS = [];

export const disallowed_characters_regex = /[^a-zA-Z\d@$!%*?&.]/;
export const alphabetic_password_regex = /^(?=.*[a-z])[A-Za-z\d@$!%*?&.]*$/;
export const number_password_regex = /^(?=.*\d)[A-Za-z\d@$!%*?&.]*$/;
export const special_password_regex = /^(?=.*[@$!%*?&.])[A-Za-z\d@$!%*?&.]*$/;

export const DAYS = {
  SUNDAY: 0,
  MONDAY: 1,
  TUESDAY: 2,
  WEDNESDAY: 3,
  THURSDAY: 4,
  FRIDAY: 5,
  SATURDAY: 6,
};

export const DAY_TEXTS = {
  [DAYS.SUNDAY]: "日",
  [DAYS.MONDAY]: "月",
  [DAYS.TUESDAY]: "火",
  [DAYS.WEDNESDAY]: "水",
  [DAYS.THURSDAY]: "木",
  [DAYS.FRIDAY]: "金",
  [DAYS.SATURDAY]: "土",
};

export const WEEKS = ["日", "月", "火", "水", "木", "金", "土"];

export const STORE_NAME = {
  1: "渋谷店",
  2: "心斎橋店",
};
export const API_CONFIG = {};

export const APPLICATION_STATUS = {
  1: "未承認",
  2: "承認済",
  3: "拒否済",
};

export const AGREEMENTS = {
  0: "36協定未締結",
  1: "36協定締結済",
};