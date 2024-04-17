import { faShieldHalved } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link, CircularProgress, Box } from "@mui/material";

import useLogin from "./useLogin";

import {
  AuthBox,
  AuthTextField,
  ContentBox,
  ContinueButton,
  HelpBox,
  HelpTextBox,
  Img,
  SeparateRowLineBox,
  SocialButton,
  TitleBox,
  ValidBox,
} from "./login.style";

import { Apple, Google, Kakao } from "../../assets/noticon";
import Header from "../common/header/header";
import { useMutation } from "@tanstack/react-query";
import { loginRepo } from "../../repositories/auth.repository";

export default function Login() {
  const mutation = useMutation({ mutationFn: loginRepo });

  const {
    onChangeEmail,
    validEmail,
    onChangePassword,
    isConfirm,
    validPassword,
    setToken,
  } = useLogin();

  return mutation.isPending ? (
    <Box
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        width: "100vw",
        height: "100vh",
      }}
    >
      <CircularProgress />
    </Box>
  ) : (
    <AuthBox>
      <Header />
      <ContentBox>
        <TitleBox>Authorization</TitleBox>
        <AuthTextField label="E-Mail" size="small" onChange={onChangeEmail} />
        {!validEmail && (
          <ValidBox>
            <FontAwesomeIcon
              icon={faShieldHalved}
              style={{ marginRight: "3px" }}
            />
            Invalid email format. Please try again.
          </ValidBox>
        )}
        <AuthTextField
          label="Password"
          size="small"
          type="password"
          onChange={onChangePassword}
        />
        {!validPassword && (
          <ValidBox>
            <FontAwesomeIcon
              icon={faShieldHalved}
              style={{ marginRight: "3px" }}
            />
            Please enter your password
          </ValidBox>
        )}
        <ContinueButton
          variant="contained"
          onClick={async () => {
            const { email, password, confirm } = isConfirm();
            if (confirm) {
              const respons = await mutation.mutateAsync({ email, password });
              setToken(respons);
            }
          }}
        >
          Continue
        </ContinueButton>
        <SeparateRowLineBox />
        <SocialButton variant="outlined">
          <Img src={Google} alt="" />
          Continue with Google
        </SocialButton>
        <SocialButton variant="outlined">
          <Img src={Kakao} alt="" />
          Continue with Kakao
        </SocialButton>
        <SocialButton variant="outlined">
          <Img src={Apple} alt="" />
          Continue with Apple ($99)
        </SocialButton>
        <HelpBox>
          <HelpTextBox>Don't have an account?</HelpTextBox>
          <Link href="/signup">Create a KirTion Account</Link>
        </HelpBox>
        <HelpBox>
          <HelpTextBox>Forgot your E-mail address?</HelpTextBox>
          <Link>Find it.</Link>
        </HelpBox>
        <HelpBox>
          <HelpTextBox>Forgot your password?</HelpTextBox>
          <Link>Reset it.</Link>
        </HelpBox>
      </ContentBox>
    </AuthBox>
  );
}
