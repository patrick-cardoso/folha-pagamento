package net.unibave.folhapagamento.funcionario;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import net.unibave.folhapagamento.base.EntityId;

@Entity
@Table(name = "funcionario")
@SequenceGenerator(name = "funcionario_seq", sequenceName = "funcionario_seq", allocationSize = 1)
public class Funcionario implements Serializable, EntityId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="funcionario_seq")
    @Column(name = "id")
    private Long id;

    @NotNull(message = "O nome do funcionário é obrigatório")
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "salario_bruto", nullable = false)
    private BigDecimal salarioBruto;

    @Version
    @Column(name = "version")
    private Long version;

    @Override
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

    public void setSalarioBruto(BigDecimal salario) {
        this.salarioBruto = salario;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Funcionario funcionario = (Funcionario) o;

        return id != null ? id.equals(funcionario.id) : funcionario.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", salarioBruto='" + salarioBruto + '\'' +
                '}';
    }
}
