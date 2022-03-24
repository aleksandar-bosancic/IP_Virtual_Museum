package com.museum.bank.backend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.museum.bank.backend.model.Type;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "bank_account")
public class BankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "holder_name")
    private String holderName;
    @Basic
    @Column(name = "number")
    private String number;
    @Column(name = "type", columnDefinition = "ENUM('VISA', 'MASTERCARD', 'AMERICAN EXPRESS')")
    @Enumerated(EnumType.STRING)
    private Type type;
    @Basic
    @Column(name = "valid_thru")
    private Date validThru;
    @Basic
    @Column(name = "pin")
    private String pin;
    @Basic
    @Column(name = "balance")
    private Double balance;
    @Basic
    @Column(name = "blocked")
    private Boolean blocked;
    @OneToMany(mappedBy = "bankAccount")
    @JsonIgnore
    private List<TransactionEntity> transactions;

}
