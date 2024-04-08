package com.server.server.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.*;

@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "page_web_share")
public class PageWebShareModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "page_uuid", nullable = false)
    private PageModel page;

    @Column(nullable = false, length = 255)
    private String url;

    @Column(nullable = false)
    private LocalDate register;

    @Column(nullable = false)
    private LocalDateTime expire;

    @Column(nullable = false)
    private boolean edit;

    @Column(nullable = false)
    private boolean comment;

    @Column(nullable = false)
    private boolean template;

    @Column(nullable = false)
    private boolean search;

    @Column(nullable = false)
    private LocalDateTime created;
}
