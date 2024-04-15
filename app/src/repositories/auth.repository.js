import axios from "axios";

export async function registerRepo(data) {
  return await axios.post("/auth/register", {
    email: data.email,
    password: data.password,
  });
}

export function loginRepo(data) {
  return axios.post("/auth/login", {
    email: data.email,
    password: data.password,
  });
}
