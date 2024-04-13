package com.server.server.models;

import java.time.LocalDateTime;
import java.util.stream.*;
import java.util.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
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
@Table(name = "kirtion_user")
public class UserModel implements UserDetails{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Column(unique = true, nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String plan;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime created;

    @Column(name = "is_valid", nullable = false)
    private Boolean isValid;

    @Column(name = "token", nullable = false)
    private String token;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserSettingModel> settings = new ArrayList<>();
    public void addUserSetting(UserSettingModel userSettingModel){
      settings.add(userSettingModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<WorkspaceModel> workspaces = new ArrayList<>();
    public void addWorkspace(WorkspaceModel workspaceModel){
      workspaces.add(workspaceModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<TeamMemberModel> members = new ArrayList<>();
    public void addMember(TeamMemberModel teamMemberModel){
      members.add(teamMemberModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MessageModel> messages = new ArrayList<>();
    public void addMessage(MessageModel messageModel){
      messages.add(messageModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PageUpdateModel> pageUpdates = new ArrayList<>();
    public void addPageUpdate(PageUpdateModel pageUpdateModel){
      pageUpdates.add(pageUpdateModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PaymentModel> payments = new ArrayList<>();
    public void addPayment(PaymentModel paymentModel){
      payments.add(paymentModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<RicipientModel> ricipients = new ArrayList<>();
    public void addRicipient(RicipientModel ricipientModel){
      ricipients.add(ricipientModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<VatModel> vats = new ArrayList<>();
    public void addVat(VatModel vatModel){
      vats.add(vatModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PersonalspaceModel> personalspaces = new ArrayList<>();
    public void addPersonalspace(PersonalspaceModel personalspaceModel){
      personalspaces.add(personalspaceModel);
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CurrentModel> currents = new ArrayList<>();
    public void addCurrent(CurrentModel currentModel){
      currents.add(currentModel);
    }

    @Override
    public String getUsername() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }
    @Override
    public boolean isAccountNonExpired() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
    }
    @Override
    public boolean isAccountNonLocked() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
    }
    @Override
    public boolean isCredentialsNonExpired() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
    }
    @Override
    public boolean isEnabled() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    }
}
