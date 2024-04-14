package com.server.server.dtos.domainDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserSettingDto {
    private String uuid;
    @JsonProperty("user_uuid")
    private String userUuid;
    private String theme;
    @JsonProperty("init_page")
    private boolean initPage;
    @JsonProperty("app_link")
    private boolean appLink;
    @JsonProperty("is_location")
    private boolean isLocation;
    @JsonProperty("time_line")
    private int timeLine;
    @JsonProperty("is_history")
    private boolean isHistory;
    @JsonProperty("is_profile")
    private boolean isProfile;
    private String language;
    @JsonProperty("is_monday")
    private boolean isMonday;
    @JsonProperty("function_cookie")
    private boolean functionCookie;
    @JsonProperty("analysis_cookie")
    private boolean analysisCookie;
    @JsonProperty("marketing_cookie")
    private boolean marketingCookie;
    @JsonProperty("mobile_push")
    private boolean mobilePush;
    @JsonProperty("slack_push")
    private boolean slackPush;
    @JsonProperty("workspace_to_email")
    private boolean workspaceToEmail;
    @JsonProperty("is_email_always")
    private boolean isEmailAlways;
    @JsonProperty("email_description_plan")
    private boolean emailDescriptionPlan;
    @JsonProperty("is_notion")
    private boolean isNotion;
}
