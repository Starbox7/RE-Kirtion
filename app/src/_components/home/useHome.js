import cookieService from "../../storages/token.cookie";

export default function useHome() {
  function getAccessToken() {
    const accessToken = cookieService.getAccessToken();

    return accessToken;
  }

  return {
    getAccessToken,
  };
}
