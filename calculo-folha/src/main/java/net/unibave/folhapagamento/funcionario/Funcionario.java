package net.unibave.folhapagamento.funcionario;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import net.unibave.folhapagamento.base.EntityId;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "funcionario")
public class Funcionario implements EntityId {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotNull
    private String nome;

    @Column(name = "salrio_bruto", nullable = false)
    @NotNull
    private BigDecimal salarioBruto;

    @Override
    public Object getId() {
        return id;
    }

}
