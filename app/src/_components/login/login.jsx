import { faShieldHalved } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link } from "@mui/material";

import useLogin from "./useLogin";

import {
  AuthBox,
  AuthTextField,
  ContentBox,
  ContinueButton,
  DescriptionBox,
  HelpBox,
  HelpTextBox,
  Img,
  SeparateRowLineBox,
  SocialButton,
  TitleBox,
  ValidBox,
  ContinueLink,
} from "./login.style";

import { Apple, Google, Kakao } from "../../assets/noticon";
import Header from "../common/header/header";

export default function Login() {
  const {
    onChangeEmail,
    validEmail,
    onChangePassword,
    validPassword,
    onChangeConfirm,
    validConfirm,
    isConfirm,
  } = useLogin();

  return (
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
            Your password must be at least 8 characters long and include at
            least one uppercase letter, one lowercase letter, one number, and
            one special character.
          </ValidBox>
        )}
        <ContinueButton
          variant="contained"
          onClick={() => {
            const { confirm, email, password } = isConfirm();
            if (confirm) {
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
