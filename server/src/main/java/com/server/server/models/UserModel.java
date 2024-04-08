package com.server.server.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    @Column(unique = true, nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String plan;

    @Column(nullable = false)
    private LocalDateTime created;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<UserSettingModel> settings = new ArrayList<>();
    public void addUserSetting(UserSettingModel userSettingModel){
      settings.add(userSettingModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<WorkspaceModel> workspaces = new ArrayList<>();
    public void addWorkspace(WorkspaceModel workspaceModel){
      workspaces.add(workspaceModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<TeamMemberModel> members = new ArrayList<>();
    public void addMember(TeamMemberModel teamMemberModel){
      members.add(teamMemberModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<MessageModel> messages = new ArrayList<>();
    public void addMessage(MessageModel messageModel){
      messages.add(messageModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<PageUpdateModel> pageUpdates = new ArrayList<>();
    public void addPageUpdate(PageUpdateModel pageUpdateModel){
      pageUpdates.add(pageUpdateModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<PaymentModel> payments = new ArrayList<>();
    public void addPayment(PaymentModel paymentModel){
      payments.add(paymentModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<RicipientModel> ricipients = new ArrayList<>();
    public void addRicipient(RicipientModel ricipientModel){
      ricipients.add(ricipientModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<VatModel> vats = new ArrayList<>();
    public void addVat(VatModel vatModel){
      vats.add(vatModel);
    }
}
