package com.letscode.bank.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letscode.bank.account.model.Account;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "nome")
  private String name;

  @Column(name = "cpf")
  private String cpf;

  @Column(name = "senha")
  private String password;

  @Column(name = "data_criacao")
  @CreatedDate
  private LocalDateTime createdDate;

  @Column(name = "data_atualizacao")
  @LastModifiedDate
  private LocalDateTime updatedDate;

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Account> accounts;
}
