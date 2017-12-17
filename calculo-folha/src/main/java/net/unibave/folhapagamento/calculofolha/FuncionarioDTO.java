package net.unibave.folhapagamento.calculofolha;

import java.math.BigDecimal;
import java.util.Objects;

public class FuncionarioDTO {

    private Long id;

    private String nome;

    private BigDecimal salarioBruto;

    private long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruno(BigDecimal salarioBruno) {
        this.salarioBruto = salarioBruno;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Long id, String nome, BigDecimal salarioBruno) {
        this.id = id;
        this.nome = nome;
        this.salarioBruto = salarioBruno;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final FuncionarioDTO other = (FuncionarioDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FuncionarioDTO{" + "id=" + id + ", nome=" + nome + ", salarioBruno=" + salarioBruto + '}';
    }

}
