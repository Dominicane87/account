package com.gorin.account.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(precision = 19, scale = 6)
    private BigDecimal summ;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Operation() {
        summ=BigDecimal.ZERO;
    }
    public Operation(BigDecimal summ, Account account) {
        this.summ=summ;
        this.account=account;
    }

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", summ=" + summ +
                ", account=" + account +
                '}';
    }

}
