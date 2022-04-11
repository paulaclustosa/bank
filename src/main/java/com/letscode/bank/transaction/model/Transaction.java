package com.letscode.bank.transaction.model;

import com.letscode.bank.account.model.Account;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacao")
@EntityListeners(AuditingEntityListener.class)
public class Transaction {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "tipo_transacao")
  @Enumerated(EnumType.STRING)
  private Type type;

  @Column(name = "agencia")
  private Integer branchNumber;

  @Column(name = "numero")
  private Integer number;

  @Column(name = "data_criacao")
  @CreatedDate
  private LocalDateTime createdDate;

  @Column(name = "data_atualizacao")
  @LastModifiedDate
  private LocalDateTime updatedDate;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "conta_id", referencedColumnName = "id")
  private Account account;
}
