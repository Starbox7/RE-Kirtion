import { Cookies } from "react-cookie";

const cookies = new Cookies();

const ACCESS_TOKEN_KEY = "accessToken";
const REFRESH_TOKEN_KEY = "refreshToken";
// const AUTH_KEY = "authNumber";

const defaultOptions = {
  path: "/",
  maxAge: 60 * 60 * 24 * 30, // 30 days
  secure: false,
  sameSite: "strict",
};

const cookieService = {
  setAccessToken: (value) => {
    cookies.set(ACCESS_TOKEN_KEY, value, defaultOptions);
  },
  getAccessToken: () => {
    return cookies.get(ACCESS_TOKEN_KEY);
  },
  removeAccessToken: () => {
    cookies.remove(ACCESS_TOKEN_KEY, defaultOptions);
  },
  setRefreshToken: (value) => {
    cookies.set(REFRESH_TOKEN_KEY, value, defaultOptions);
  },
  getRefreshToken: () => {
    return cookies.get(REFRESH_TOKEN_KEY);
  },
  removeRefreshToken: () => {
    cookies.remove(REFRESH_TOKEN_KEY, defaultOptions);
  },
  setTokens: (accessToken, refreshToken) => {
    cookieService.setAccessToken(accessToken);
    cookieService.setRefreshToken(refreshToken);
  },
  getTokens: () => {
    return {
      accessToken: cookieService.getAccessToken(),
      refreshToken: cookieService.getRefreshToken(),
    };
  },
  removeTokens: () => {
    cookieService.removeAccessToken();
    cookieService.removeRefreshToken();
  },
  // setAuth: (value) => {
  //   cookies.set(AUTH_KEY, value, defaultOptions);
  // },
  // getAuth: () => {
  //   return cookies.get(AUTH_KEY);
  // },
  // removeAuth: () => {
  //   cookies.remove(AUTH_KEY, defaultOptions);
  // },
};

export default cookieService;
