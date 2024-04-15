import { useRef, useState } from "react";
import cookieService from "../../storages/token.cookie";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { setAuthenticationState } from "../../features/auth.slice";

export default function useLogin() {
  const emailRef = useRef("");
  const passwordRef = useRef("");

  const [validEmail, setValidEmail] = useState(true);
  const [validPassword, setValidPassword] = useState(true);

  const navigate = useNavigate();

  const dispatch = useDispatch();

  function onChangeEmail(event) {
    emailRef.current = event.target.value;
    setValidEmail(isValidEmail(event.target.value));
  }

  function isValidEmail(email) {
    const regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    return regex.test(email);
  }

  function onChangePassword(event) {
    passwordRef.current = event.target.value;
    setValidPassword(isValidPassword(event.target.value));
  }

  function isValidPassword(password) {
    if (password != "") {
      return true;
    } else {
      return false;
    }
  }

  function isConfirm(mutation) {
    if (
      validEmail &&
      validPassword &&
      emailRef.current != "" &&
      passwordRef.current != ""
    ) {
      const email = emailRef.current;
      const password = passwordRef.current;

      return { email, password, confirm: true };
    } else {
      setValidEmail(false);
      setValidPassword(false);

      return { confirm: false };
    }
  }

  function setToken(response) {
    const accessToken = response.headers["access-token"];
    const refreshToken = response.headers["refresh-token"];
    cookieService.setTokens(accessToken, refreshToken);

    dispatch(setAuthenticationState(true));

    navigate("/home");
  }

  return {
    onChangeEmail,
    validEmail,
    onChangePassword,
    validPassword,
    isConfirm,
    setToken,
  };
}
