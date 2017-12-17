package net.unibave.folhapagamento.calculofolha;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import net.unibave.folhapagamento.funcionario.Funcionario;

public class CalculoFolhaDTO {

    private Long id;

    private Funcionario funcionario;

    private LocalDate dataFolha;

    private BigDecimal valorIss;

    private BigDecimal valorIr;

    private BigDecimal valorSalarioLiquido;

    public CalculoFolhaDTO() {
    }

    public CalculoFolhaDTO(Long id, Funcionario funcionario, LocalDate dataFolha, BigDecimal valorIss, BigDecimal valorIr, BigDecimal valorSalarioLiquido) {
        this.id = id;
        this.funcionario = funcionario;
        this.dataFolha = dataFolha;
        this.valorIss = valorIss;
        this.valorIr = valorIr;
        this.valorSalarioLiquido = valorSalarioLiquido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate getDataFolha() {
        return dataFolha;
    }

    public void setDataFolha(LocalDate dataFolha) {
        this.dataFolha = dataFolha;
    }

    public BigDecimal getValorIss() {
        return valorIss;
    }

    public void setValorIss(BigDecimal valorIss) {
        this.valorIss = valorIss;
    }

    public BigDecimal getValorIr() {
        return valorIr;
    }

    public void setValorIr(BigDecimal valorIr) {
        this.valorIr = valorIr;
    }

    public BigDecimal getValorSalarioLiquido() {
        return valorSalarioLiquido;
    }

    public void setValorSalarioLiquido(BigDecimal valorSalarioLiquido) {
        this.valorSalarioLiquido = valorSalarioLiquido;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CalculoFolhaDTO other = (CalculoFolhaDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
