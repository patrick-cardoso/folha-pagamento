package net.unibave.folhapagamento.holerite;

import net.unibave.folhapagamento.funcionario.Funcionario;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.unibave.folhapagamento.base.EntityId;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "holerite")
public class Holerite implements EntityId {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionario;

    @Column(name = "data", nullable = false)
    private LocalDate dataFolha;

    @Column(name = "valor_iss", nullable = true)
    private BigDecimal valorIss;

    @Column(name = "aliquota_iss", nullable = true)
    private BigDecimal aliquotaINSS;

    @Column(name = "valor_ir", nullable = true)
    private BigDecimal valorIr;

    @Column(name = "aliquota_ir", nullable = true)
    private BigDecimal aliquotaIR;

    @Column(name = "base_calulo_ir", nullable = true)
    private BigDecimal baseCaluloIR;

    @Column(name = "deducao_ir", nullable = true)
    private BigDecimal deducaoIR;

    @Column(name = "valor_salario_liq", nullable = true)
    private BigDecimal valorSalarioLiquido;

    @Column(name = "valor_salario_bruto", nullable = true)
    private BigDecimal valorSalarioBruto;

    @Override
    public Object getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Holerite other = (Holerite) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Holerite{" + "id=" + id + ", dataFolha=" + dataFolha + ", valorIss=" + valorIss + ", valorIr=" + valorIr + ", valorSalarioLiquido=" + valorSalarioLiquido + '}';
    }

}
