package com.example.wallet.entity;

import com.example.wallet.base.BaseEntity;
import com.example.wallet.entity.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "wallet")
@Setter
@Getter
@NoArgsConstructor
@Builder
public class Wallet extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long walletNumber;
    @NotBlank
    @Size(min = 2 , max = 50)
    @Column(unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Size(max = 100)
    private String description;
    @Range(min = 0)
    private Double currentBalance;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "wallet")
    private List<Transaction> transactions;

    public Wallet(Long id, Long walletNumber, String name, Status status, String description, Double currentBalance, User user, List<Transaction> transactions) {
        this.id = id;
        this.walletNumber = walletNumber;
        this.name = name;
        this.status = Status.active;
        this.description = description;
        this.currentBalance = currentBalance;
        this.user = user;
        this.transactions = transactions;
    }
}
