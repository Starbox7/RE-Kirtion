import { Link } from "@mui/material";

import {
  AuthSection,
  Content,
  Description,
  Help,
  HelpText,
  Image,
  Input,
  SeparateRowLine,
  Signin,
  Social,
  Title,
} from "./login.style";

import { Apple, Google, Kakao } from "../../assets/noticon";
import Header from "../common/header/header";

export default function Login() {
  return (
    <AuthSection>
      <Header />
      <Content>
        <Title>Authorization</Title>
        <Input label="E-Mail" size="small" />
        <Input label="Password" size="small" type="password" />
        <Description>
          Use an organization email to easily collaborate with teammates
        </Description>
        <Signin variant="contained">Continue</Signin>
        <SeparateRowLine />
        <Social variant="outlined">
          <Image src={Google} alt="" />
          Continue with Google
        </Social>
        <Social variant="outlined">
          <Image src={Kakao} alt="" />
          Continue with Kakao
        </Social>
        <Social variant="outlined">
          <Image src={Apple} alt="" />
          Continue with Apple ($99)
        </Social>
        <Help>
          <HelpText>Don't have an account?</HelpText>
          <Link href="/signup">Create a KirTion Account</Link>
        </Help>
        <Help>
          <HelpText>Forgot your E-mail address?</HelpText>
          <Link>Find it.</Link>
        </Help>
        <Help>
          <HelpText>Forgot your password?</HelpText>
          <Link>Reset it.</Link>
        </Help>
      </Content>
    </AuthSection>
  );
}
