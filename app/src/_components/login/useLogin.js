import { useRef, useState } from "react";
import cookieService from "../../storages/token.cookie";

export default function useLogin() {
  const emailRef = useRef("");
  const passwordRef = useRef("");

  const [validEmail, setValidEmail] = useState(true);
  const [validPassword, setValidPassword] = useState(true);

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
    // const hasUpperCase = /[A-Z]/.test(password);
    // const hasLowerCase = /[a-z]/.test(password);
    // const hasNumber = /[0-9]/.test(password);
    // const hasSpecialChar = /[\W_]/.test(password); // \W는 숫자와 문자가 아닌 문자, _ 포함
    // const isLongEnough = password.length >= 8;

    if (password != "") {
      return true;
    } else {
      return false;
    }
  }

  function isConfirm() {
    if (
      validEmail &&
      validPassword &&
      emailRef.current != "" &&
      passwordRef.current != ""
    ) {
      return {
        confirm: true,
        email: emailRef.current,
        password: passwordRef.current,
      };
    } else {
      setValidEmail(false);
      setValidPassword(false);

      return { confirm: false };
    }
  }

  function completeLogin(mutation, navigate) {
    const accessToken = mutation.data.headers["access-token"];
    const refreshToken = mutation.data.headers["refresh-token"];
    cookieService.setTokens(accessToken, refreshToken);

    return navigate("/home");
  }

  return {
    onChangeEmail,
    validEmail,
    onChangePassword,
    validPassword,
    isConfirm,
    completeLogin,
  };
}
