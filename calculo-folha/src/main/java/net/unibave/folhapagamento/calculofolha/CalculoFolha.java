package net.unibave.folhapagamento.calculofolha;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import net.unibave.folhapagamento.base.EntityId;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "calculo_folha")
public class CalculoFolha implements EntityId {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDate dataFolha;

    @Override
    public Object getId() {
        return id;
    }

}
