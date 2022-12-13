package com.example.wallet.entity;

import com.example.wallet.base.BaseEntity;
import com.example.wallet.entity.enums.Type;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "transaction")
@Setter
@Getter
@ToString
@Component
public class Transaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    private Type type;
    @Size(max = 1000)
    @Transient
    private String description;
    @ManyToOne
    private Wallet wallet;
    @ManyToOne
    private Bank bank;

    public Transaction() {
    }

    public Transaction(Long id, Wallet wallet, Bank bank) {
        this.id = id;
        this.type = Type.success;
        this.wallet = wallet;
        this.bank = bank;
    }
}
