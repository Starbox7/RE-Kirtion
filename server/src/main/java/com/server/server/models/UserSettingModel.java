package com.server.server.models;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "user_setting")
public class UserSettingModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_uuid")
    private UserModel user;

    @Column(nullable = false)
    private String theme;

    @Column(name = "init_page", nullable = false)
    private boolean initPage;

    @Column(name = "app_link", nullable = false)
    private boolean appLink;

    @Column(name = "is_location", nullable = false)
    private boolean isLocation;

    @Column(name = "time_line", nullable = false)
    private int timeLine;

    @Column(name = "is_history", nullable = false)
    private boolean isHistory;

    @Column(name = "is_profile", nullable = false)
    private boolean isProfile;

    @Column(nullable = false)
    private String language;

    @Column(name = "is_monday", nullable = false)
    private boolean isMonday;

    @Column(name = "function_cookie", nullable = false)
    private boolean functionCookie;

    @Column(name = "analysis_cookie", nullable = false)
    private boolean analysisCookie;

    @Column(name = "marketing_cookie", nullable = false)
    private boolean marketingCookie;

    @Column(name = "mobile_push", nullable = false)
    private boolean mobilePush;

    @Column(name = "slack_push", nullable = false)
    private boolean slackPush;

    @Column(name = "workspace_to_email", nullable = false)
    private boolean workspaceToEmail;

    @Column(name = "is_email_always", nullable = false)
    private boolean isEmailAlways;

    @Column(name = "email_description_plan", nullable = false)
    private boolean emailDescriptionPlan;

    @Column(name = "is_notion", nullable = false)
    private boolean isNotion;
}
