import { faShieldHalved } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link } from "@mui/material";

//hooks
import useRegister from "./useRegister";

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
} from "./register.style";

import { Apple, Google, Kakao } from "../../assets/noticon";
import Header from "../common/header/header";

export default function Register() {
  const {
    onChangeEmail,
    validEmail,
    onChangePassword,
    validPassword,
    onChangeConfirm,
    validConfirm,
    register,
  } = useRegister();

  return (
    <AuthBox>
      <Header />
      <ContentBox>
        <TitleBox>Register</TitleBox>
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
        <AuthTextField
          label="Confirm Password"
          size="small"
          type="password"
          onChange={onChangeConfirm}
        />
        {!validConfirm && (
          <ValidBox>
            <FontAwesomeIcon
              icon={faShieldHalved}
              style={{ marginRight: "3px" }}
            />
            Password does not match
          </ValidBox>
        )}
        <DescriptionBox>
          Use an organization email to easily collaborate with teammates
        </DescriptionBox>
        <ContinueButton
          variant="contained"
          onClick={() => {
            register();
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
          <HelpTextBox>have an account?</HelpTextBox>
          <Link href="/login">go to Login</Link>
        </HelpBox>
      </ContentBox>
    </AuthBox>
  );
}
