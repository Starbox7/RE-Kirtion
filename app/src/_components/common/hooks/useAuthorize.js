import cookieService from "../../../storages/token.cookie";

export default function useAuthorize() {
  function getAccessToken() {
    const accessToken = cookieService.getAccessToken();

    return accessToken;
  }

  return {
    getAccessToken,
  };
}
