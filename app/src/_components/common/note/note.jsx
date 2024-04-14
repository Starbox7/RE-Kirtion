import { ContentBox, PageBox, RoutingBarBox, TitleBox } from "./page.style";

export default function Note() {
  return (
    <PageBox>
      <RoutingBarBox></RoutingBarBox>
      <TitleBox></TitleBox>
      <ContentBox></ContentBox>
    </PageBox>
  );
}
