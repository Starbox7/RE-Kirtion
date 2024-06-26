package com.server.server.models;

import java.time.LocalDateTime;
import java.util.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "workspace")
public class WorkspaceModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_uuid")
    private UserModel user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String logo;

    @Column(nullable = false)
    private String plan;

    @Column(nullable = false)
    private String domain;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime created;

    @ToString.Exclude
    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL)
    @Builder.Default
    private List<TeamspaceModel> teamspaces = new ArrayList<>();
    public void addTeamspace(TeamspaceModel teamspaceModel){
      teamspaces.add(teamspaceModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PersonalspaceModel> personalspaces = new ArrayList<>();
    public void addPersonalspace(PersonalspaceModel personalspaceModel){
      personalspaces.add(personalspaceModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CurrentModel> currents = new ArrayList<>();
    public void addCurrent(CurrentModel currentModel){
      currents.add(currentModel);
    }
}
