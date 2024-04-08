Table user {
  uuid varchar(255) [pk, unique, not null]
  email varchar(255) [unique, not null]
  name varchar(255) [not null]
  password varchar(255) [not null]
  plan varchar(255) [not null]
  created TIMESTAMP [not null]
}

Table user_setting {
  uuid varchar(255) [pk, unique, not null]
  user_uuid varchar(255) [not null, ref: > user.uuid]
  theme varchar(255) [not null]
  init_page bool [not null]
  app_link bool [not null]
  is_location bool [not null]
  time_line int [not null]
  is_history bool [not null]
  is_profile bool [not null]
  language varchar(255) [not null]
  is_monday bool [not null]
  function_cookie bool [not null]
  analysis_cookie bool [not null]
  marketing_cookie bool [not null]
  mobile_push bool [not null]
  slack_push bool [not null]
  workspace_to_email bool [not null]
  is_email_always bool [not null]
  email_description_plan bool [not null]
  is_notion bool [not null]
}

Table workspace {
  uuid varchar(255) [pk, unique, not null]
  user_uuid varchar(255) [not null, ref: > user.uuid]
  name varchar(255) [not null]
  logo varchar(255) [not null]
  plan varchar(255) [not null]
  domain varchar(255) [not null]
  created TIMESTAMP [not null]
}

Table teamspace {
  uuid varchar(255) [pk, unique, not null]
  workspace_uuid varchar(255) [not null, ref: > workspace.uuid]
  name varchar(255) [not null]
  logo varchar(255) [not null]
  created TIMESTAMP [not null]
}

Table team_member {
  uuid varchar(255) [pk, unique, not null]
  teamspace_uuid varchar(255) [not null, ref: > teamspace.uuid]
  user_uuid varchar(255) [not null, ref: > user.uuid]
  created TIMESTAMP [not null]
}

Table message {
  uuid varchar(255) [pk, unique, not null]
  user_uuid varchar(255) [not null, ref: > user.uuid]
  title varchar(255) [not null]
  created TIMESTAMP [not null]
}

Table page {
  uuid varchar(255) [pk, unique, not null]
  workspace_uuid varchar(255) [null, ref: > workspace.uuid]
  teamspace_uuid varchar(255) [null, ref: > teamspace.uuid]
  page_uuid varchar(255) [null, ref: > page.uuid]
  created TIMESTAMP [not null]
  title varchar(255) [null]
  icon varchar(255) [null]
  background varchar(255) [null]
  text text [null]
  soft_delete bool [not null]
}

Table page_setting {
  uuid varchar(255) [pk, unique, not null]
  page_uuid varchar(255) [not null, ref: > page.uuid]
  font_family varchar(255) [not null]
  font_size bool [not null]
  expansion bool [not null]
  page_lock bool [not null]
  comment bool [not null]
  up_comment bool [not null]
  back_link varchar(255) [not null]
  is_wiki bool [not null]
  alert bool [not null]
}

Table page_web_share {
  uuid varchar(255) [pk, unique, not null]
  page_uuid varchar(255) [not null, ref: > page.uuid]
  url varchar(255) [not null]
  register date [not null]
  expire TIMESTAMP [not null]
  edit bool [not null]
  comment bool [not null]
  template bool [not null]
  search bool [not null]
  created TIMESTAMP [not null]
}

Table page_update {
  uuid varchar(255) [pk, unique, not null]
  page_uuid varchar(255) [not null, ref: > page.uuid]
  user_uuid varchar(255) [not null, ref: > user.uuid]
  updated TIMESTAMP [not null]
  content varchar(255) [not null]
}

Table page_snapshot {
  uuid varchar(255) [pk, unique, not null]
  created TIMESTAMP [not null]
  page_uuid varchar(255) [not null, ref: > page.uuid]
  icon varchar(255) [null]
  title varchar(255) [null]
  text text [null]
}

Table payment {
  uuid varchar(255) [pk, unique, not null]
  user_uuid varchar(255) [not null, ref: > user.uuid]
  card_num int [not null]
  expire int [not null]
  cvc int [not null]
  country varchar(255) [not null]
}

Table recipient {
  uuid varchar(255) [pk, unique, not null]
  user_uuid varchar(255) [not null, ref: > user.uuid]
  name varchar(255) [not null]
  country varchar(255) [not null]
  address text [not null]
}

Table vat {
  uuid varchar(255) [pk, unique, not null]
  user_uuid varchar(255) [not null, ref: > user.uuid]
  number int [not null]
}
