package com.letscode.bank.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letscode.bank.transaction.model.Transaction;
import com.letscode.bank.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "conta")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "agencia")
  private Integer branchNumber;

  @Column(name = "numero")
  private Integer number;

  @Column(name = "tipo_conta")
  @Enumerated(EnumType.STRING)
  private Type type;

  @Column(name = "saldo")
  private BigDecimal balance;

  @Column(name = "data_criacao")
  @CreatedDate
  private LocalDateTime createdDate;

  @Column(name = "data_atualizacao")
  @LastModifiedDate
  private LocalDateTime updatedDate;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private User user;

  @JsonIgnore
  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
  private List<Transaction> transactions;
}
