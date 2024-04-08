import axios from "axios";

export function registerRepo(encryptedCredentials) {
  return axios.post(
    "/auth/register",
    {},
    {
      headers: {
        Authorization: `${encryptedCredentials}`,
      },
    }
  );
}
