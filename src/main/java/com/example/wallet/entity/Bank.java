package com.example.wallet.entity;

import com.example.wallet.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "bank")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bank extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Double amount;
    @ManyToOne
    private User user;
}
