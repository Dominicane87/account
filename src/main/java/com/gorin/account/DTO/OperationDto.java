package com.gorin.account.DTO;

import java.math.BigDecimal;
import java.util.Objects;

public class OperationDto {
    private Long idAccount;
    private BigDecimal summ;

    public OperationDto() {
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OperationDto)) return false;
        OperationDto that = (OperationDto) o;
        return getIdAccount().equals(that.getIdAccount()) &&
                getSumm().equals(that.getSumm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdAccount(), getSumm());
    }
}
