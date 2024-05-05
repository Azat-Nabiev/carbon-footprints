const LOCAL_ID = "UID";
const LOCAL_ACCESS = "accessToken";
const LOCAL_REFRESH = "refreshToken";

export const saveAccessToken = (val) => localStorage.setItem(LOCAL_ACCESS, val);
export const saveUserId = (val) => localStorage.setItem(LOCAL_ID, val);
export const saveRefreshToken = (val) =>
  localStorage.setItem(LOCAL_REFRESH, val);

export const cleanAuthData = () => {
  localStorage.clear();
};

export const getSavedId = () => {
  const id = localStorage.getItem(LOCAL_ID);
  if (id) {
    return id;
  }
  throw new Error("Saved id not found");
};

export const getAccessToken = () => {
  const token = localStorage.getItem(LOCAL_ACCESS);
  if (token) {
    return token;
  }
  throw new Error("No access token strored");
};

export const getRefreshToken = () => {
  const token = localStorage.getItem(LOCAL_REFRESH);
  if (token) {
    return token;
  }
  throw new Error("No refresh token stored");
};

export const getAurhorizationHeaderValue = () => {
  const accessToken = getAccessToken();
  return `Bearer ${accessToken}`;
};
