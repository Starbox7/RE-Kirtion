
CREATE TABLE "user" (
  "uuid" varchar(255) UNIQUE PRIMARY KEY NOT NULL,
  "email" varchar(255) UNIQUE NOT NULL,
  "password" varchar(255) UNIQUE NOT NULL,
  "name" varchar(255) NOT NULL,
  "plan" varchar(255) NOT NULL,
  "created" TIMESTAMP NOT NULL
);

CREATE TABLE "user_setting" (
  "uuid" varchar(255) UNIQUE PRIMARY KEY NOT NULL,
  "user_uuid" varchar(255) NOT NULL,
  "theme" varchar(255) NOT NULL,
  "init_page" bool NOT NULL,
  "app_link" bool NOT NULL,
  "is_location" bool NOT NULL,
  "time_line" int NOT NULL,
  "is_history" bool NOT NULL,
  "is_profile" bool NOT NULL,
  "language" varchar(255) NOT NULL,
  "is_monday" bool NOT NULL,
  "function_cookie" bool NOT NULL,
  "analysis_cookie" bool NOT NULL,
  "marketing_cookie" bool NOT NULL,
  "mobile_push" bool NOT NULL,
  "slack_push" bool NOT NULL,
  "workspace_to_email" bool NOT NULL,
  "is_email_always" bool NOT NULL,
  "email_description_plan" bool NOT NULL,
  "is_notion" bool NOT NULL
);

CREATE TABLE "workspace" (
  "uuid" varchar(255) UNIQUE PRIMARY KEY NOT NULL,
  "user_uuid" varchar(255) NOT NULL,
  "name" varchar(255) NOT NULL,
  "logo" varchar(255) NOT NULL,
  "plan" varchar(255) NOT NULL,
  "domain" varchar(255) NOT NULL,
  "created" TIMESTAMP NOT NULL
);

CREATE TABLE "teamspace" (
  "uuid" varchar(255) UNIQUE PRIMARY KEY NOT NULL,
  "workspace_uuid" varchar(255) NOT NULL,
  "name" varchar(255) NOT NULL,
  "logo" varchar(255) NOT NULL,
  "created" TIMESTAMP NOT NULL
);

CREATE TABLE "team_member" (
  "uuid" varchar(255) UNIQUE PRIMARY KEY NOT NULL,
  "teamspace_uuid" varchar(255) NOT NULL,
  "user_uuid" varchar(255) NOT NULL,
  "created" TIMESTAMP NOT NULL
);

CREATE TABLE "message" (
  "uuid" varchar(255) UNIQUE PRIMARY KEY NOT NULL,
  "user_uuid" varchar(255) NOT NULL,
  "title" varchar(255) NOT NULL,
  "created" TIMESTAMP NOT NULL
);

CREATE TABLE "page" (
  "uuid" varchar(255) UNIQUE PRIMARY KEY NOT NULL,
  "workspace_uuid" varchar(255),
  "teamspace_uuid" varchar(255),
  "page_uuid" varchar(255),
  "created" TIMESTAMP NOT NULL,
  "title" varchar(255),
  "icon" varchar(255),
  "background" varchar(255),
  "text" text,
  "soft_delete" bool NOT NULL
);

CREATE TABLE "page_setting" (
  "uuid" varchar(255) UNIQUE PRIMARY KEY NOT NULL,
  "page_uuid" varchar(255) NOT NULL,
  "font_family" varchar(255) NOT NULL,
  "font_size" bool NOT NULL,
  "expansion" bool NOT NULL,
  "page_lock" bool NOT NULL,
  "comment" bool NOT NULL,
  "up_comment" bool NOT NULL,
  "back_link" varchar(255) NOT NULL,
  "is_wiki" bool NOT NULL,
  "alert" bool NOT NULL
);

CREATE TABLE "page_web_share" (
  "uuid" varchar(255) UNIQUE PRIMARY KEY NOT NULL,
  "page_uuid" varchar(255) NOT NULL,
  "url" varchar(255) NOT NULL,
  "register" date NOT NULL,
  "expire" TIMESTAMP NOT NULL,
  "edit" bool NOT NULL,
  "comment" bool NOT NULL,
  "template" bool NOT NULL,
  "search" bool NOT NULL,
  "created" TIMESTAMP NOT NULL
);

CREATE TABLE "page_update" (
  "uuid" varchar(255) UNIQUE PRIMARY KEY NOT NULL,
  "page_uuid" varchar(255) NOT NULL,
  "user_uuid" varchar(255) NOT NULL,
  "updated" TIMESTAMP NOT NULL,
  "content" varchar(255) NOT NULL
);

CREATE TABLE "page_snapshot" (
  "uuid" varchar(255) UNIQUE PRIMARY KEY NOT NULL,
  "created" TIMESTAMP NOT NULL,
  "page_uuid" varchar(255) NOT NULL,
  "icon" varchar(255),
  "title" varchar(255),
  "text" text
);

CREATE TABLE "payment" (
  "uuid" varchar(255) UNIQUE PRIMARY KEY NOT NULL,
  "user_uuid" varchar(255) NOT NULL,
  "card_num" int NOT NULL,
  "expire" int NOT NULL,
  "cvc" int NOT NULL,
  "country" varchar(255) NOT NULL
);

CREATE TABLE "recipient" (
  "uuid" varchar(255) UNIQUE PRIMARY KEY NOT NULL,
  "user_uuid" varchar(255) NOT NULL,
  "name" varchar(255) NOT NULL,
  "country" varchar(255) NOT NULL,
  "address" text NOT NULL
);

CREATE TABLE "vat" (
  "uuid" varchar(255) UNIQUE PRIMARY KEY NOT NULL,
  "user_uuid" varchar(255) NOT NULL,
  "number" int NOT NULL
);

ALTER TABLE "user_setting" ADD FOREIGN KEY ("user_uuid") REFERENCES "user" ("uuid");

ALTER TABLE "workspace" ADD FOREIGN KEY ("user_uuid") REFERENCES "user" ("uuid");

ALTER TABLE "teamspace" ADD FOREIGN KEY ("workspace_uuid") REFERENCES "workspace" ("uuid");

ALTER TABLE "team_member" ADD FOREIGN KEY ("teamspace_uuid") REFERENCES "teamspace" ("uuid");

ALTER TABLE "team_member" ADD FOREIGN KEY ("user_uuid") REFERENCES "user" ("uuid");

ALTER TABLE "message" ADD FOREIGN KEY ("user_uuid") REFERENCES "user" ("uuid");

ALTER TABLE "page" ADD FOREIGN KEY ("workspace_uuid") REFERENCES "workspace" ("uuid");

ALTER TABLE "page" ADD FOREIGN KEY ("teamspace_uuid") REFERENCES "teamspace" ("uuid");

ALTER TABLE "page" ADD FOREIGN KEY ("page_uuid") REFERENCES "page" ("uuid");

ALTER TABLE "page_setting" ADD FOREIGN KEY ("page_uuid") REFERENCES "page" ("uuid");

ALTER TABLE "page_web_share" ADD FOREIGN KEY ("page_uuid") REFERENCES "page" ("uuid");

ALTER TABLE "page_update" ADD FOREIGN KEY ("page_uuid") REFERENCES "page" ("uuid");

ALTER TABLE "page_update" ADD FOREIGN KEY ("user_uuid") REFERENCES "user" ("uuid");

ALTER TABLE "page_snapshot" ADD FOREIGN KEY ("page_uuid") REFERENCES "page" ("uuid");

ALTER TABLE "payment" ADD FOREIGN KEY ("user_uuid") REFERENCES "user" ("uuid");

ALTER TABLE "recipient" ADD FOREIGN KEY ("user_uuid") REFERENCES "user" ("uuid");

ALTER TABLE "vat" ADD FOREIGN KEY ("user_uuid") REFERENCES "user" ("uuid");

DO
$do$
DECLARE
   r RECORD;
BEGIN
   FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public') LOOP
      EXECUTE 'DROP TABLE IF EXISTS public.' || quote_ident(r.tablename) || ' CASCADE';
   END LOOP;
END
$do$;
