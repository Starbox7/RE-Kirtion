package com.server.server.models;

import java.time.LocalDateTime;
import java.util.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.*;

@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "page")
public class PageModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workspace_uuid", nullable = false)
    private WorkspaceModel workspace;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teamspace_uuid", nullable = false)
    private TeamspaceModel teamspace;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "page_uuid", nullable = false)
    private PageModel page;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime created;

    @Column(length = 255)
    private String title;

    @Column(length = 255)
    private String icon;

    @Column(length = 255)
    private String background;

    @Column(columnDefinition = "text")
    private String text;

    @Column(name = "soft_delete", nullable = false)
    private boolean softDelete;

    @ToString.Exclude
    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PageModel> pages = new ArrayList<>();
    public void addPage(PageModel pageModel){
      pages.add(pageModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PageSettingModel> pageSettings = new ArrayList<>();
    public void addPageSetting(PageSettingModel pageSettingModel){
      pageSettings.add(pageSettingModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PageWebShareModel> pageWebShares = new ArrayList<>();
    public void addPageWebShare(PageWebShareModel pageWebShareModel){
      pageWebShares.add(pageWebShareModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PageUpdateModel> pageUpdates = new ArrayList<>();
    public void addPageUpdate(PageUpdateModel pageUpdateModel){
      pageUpdates.add(pageUpdateModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PageSnapshotModel> pageSnapshots = new ArrayList<>();
    public void addPageSnapshot(PageSnapshotModel pageSnapshotModel){
      pageSnapshots.add(pageSnapshotModel);
    }
}
