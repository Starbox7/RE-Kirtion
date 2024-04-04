import { useRef, useState } from "react";

export default function useRegister() {
  const emailRef = useRef("");
  const passwordRef = useRef("");
  const confirmRef = useRef("");

  const [validEmail, setValidEmail] = useState(true);
  const [validPassword, setValidPassword] = useState(true);
  const [validConfirm, setValidConfirm] = useState(true);

  function onChangeEmail(event) {
    emailRef.current = event.target.value;
    setValidEmail(isValidEmail(event.target.value));
    console.log(validEmail);
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
    const hasUpperCase = /[A-Z]/.test(password);
    const hasLowerCase = /[a-z]/.test(password);
    const hasNumber = /[0-9]/.test(password);
    const hasSpecialChar = /[\W_]/.test(password); // \W는 숫자와 문자가 아닌 문자, _ 포함
    const isLongEnough = password.length >= 8;

    return (
      hasUpperCase &&
      hasLowerCase &&
      hasNumber &&
      hasSpecialChar &&
      isLongEnough
    );
  }

  function onChangeConfirm(event) {
    confirmRef.current = event.target.value;
    setValidConfirm(isValidConfirm(event.target.value));
    console.log(validConfirm);
  }

  function isValidConfirm(confirm) {
    if (confirm == passwordRef.current) return true;
    else return false;
  }

  function register() {
    if (
      validEmail &&
      validPassword &&
      validConfirm &&
      emailRef.current != "" &&
      passwordRef.current != "" &&
      confirmRef.current != ""
    ) {
    } else {
      setValidEmail(false);
      setValidPassword(false);
      setValidConfirm(false);
    }
  }

  return {
    onChangeEmail,
    validEmail,
    onChangePassword,
    validPassword,
    onChangeConfirm,
    validConfirm,
    register,
  };
}
