import Logo from "../kirby/kirby";
import Translation from "../translation/translation";

import {
  HeaderSection,
  HeaderTitleBox,
  SeparateColumnLine,
} from "./Header.style";

export default function Header() {
  return (
    <HeaderSection>
      <HeaderTitleBox>
        <Logo />
        KirTion
      </HeaderTitleBox>
      <SeparateColumnLine />
      <Translation />
    </HeaderSection>
  );
}
