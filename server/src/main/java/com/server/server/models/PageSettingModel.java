package com.server.server.models;

import java.time.LocalDateTime;
import java.util.*;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "page_setting")
public class PageSettingModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "page_uuid", nullable = false)
    private PageModel page;

    @Column(name = "font_family", nullable = false, length = 255)
    private String fontFamily;

    @Column(name = "font_size", nullable = false)
    private boolean fontSize;

    @Column(nullable = false)
    private boolean expansion;

    @Column(name = "page_lock", nullable = false)
    private boolean pageLock;

    @Column(nullable = false)
    private boolean comment;

    @Column(name = "up_comment", nullable = false)
    private boolean upComment;

    @Column(name = "back_link", nullable = false, length = 255)
    private String backLink;

    @Column(name = "is_wiki", nullable = false)
    private boolean isWiki;

    @Column(nullable = false)
    private boolean alert;
}
