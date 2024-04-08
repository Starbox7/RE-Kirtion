import axios from "axios";

export function registerRepo(data) {
  return axios.post("/auth/register", {
    email: data.email,
    password: data.password,
  });
}
