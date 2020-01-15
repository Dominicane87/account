 package com.gorin.account.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    private Long id;

    @Column(precision = 19, scale = 6)
    private BigDecimal summ;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Operation> operations;

    public Account() {
        this.summ=BigDecimal.ZERO;
    }

    public Account(BigDecimal summ, Long id){
        this.summ=summ;
        this.id=id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", summ=" + summ.floatValue()+
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(getId(), account.getId()) &&
                Objects.equals(getSumm(), account.getSumm()) &&
                Objects.equals(getOperations(), account.getOperations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSumm(), getOperations());
    }
}
