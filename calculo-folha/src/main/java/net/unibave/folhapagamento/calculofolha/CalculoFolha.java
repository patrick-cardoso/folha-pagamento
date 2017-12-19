package net.unibave.folhapagamento.calculofolha;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    private static BigDecimal getAliquotaINSS(final BigDecimal salarioBruto) {
        if (salarioBruto.compareTo(BigDecimal.valueOf(1399.12)) <= 0) {
            return BigDecimal.valueOf(.08);
        }
        if (salarioBruto.compareTo(BigDecimal.valueOf(2331.88)) <= 0) {
            return BigDecimal.valueOf(.09);
        }
        return BigDecimal.valueOf(.11);
    }

    private static Map<String, BigDecimal> getAliquotaDeducaoIRRF(final BigDecimal baseCalculo) {
        Map<String, BigDecimal> retorno = new HashMap<>();
        if (baseCalculo.compareTo(BigDecimal.valueOf(1903.98)) <= 0) {
            retorno.put("aliquota", BigDecimal.ZERO);
            retorno.put("deducao", BigDecimal.ZERO);
            return retorno;
        }
        if (baseCalculo.compareTo(BigDecimal.valueOf(2826.65)) <= 0) {
            retorno.put("aliquota", BigDecimal.valueOf(.075));
            retorno.put("deducao", BigDecimal.valueOf(142.8));
            return retorno;
        }
        if (baseCalculo.compareTo(BigDecimal.valueOf(3751.05)) <= 0) {
            retorno.put("aliquota", BigDecimal.valueOf(.15));
            retorno.put("deducao", BigDecimal.valueOf(354.8));
            return retorno;
        }
        if (baseCalculo.compareTo(BigDecimal.valueOf(4664.68)) <= 0) {
            retorno.put("aliquota", BigDecimal.valueOf(.225));
            retorno.put("deducao", BigDecimal.valueOf(636.13));
            return retorno;
        }
        retorno.put("aliquota", BigDecimal.valueOf(.275));
        retorno.put("deducao", BigDecimal.valueOf(869.36));
        return retorno;
    }

    public static Map<String, BigDecimal> calculaFolha(final BigDecimal salarioBruto) {
        final BigDecimal aliquotaINSS = getAliquotaINSS(salarioBruto);
        final BigDecimal valorINSS = salarioBruto.multiply(aliquotaINSS);
        final BigDecimal baseCalculoIR = salarioBruto.subtract(valorINSS);
        final Map<String, BigDecimal> aliquotaDeducaoIR = getAliquotaDeducaoIRRF(baseCalculoIR);
        final BigDecimal valorIRRF = baseCalculoIR.multiply(aliquotaDeducaoIR.
                get("aliquota")).subtract(aliquotaDeducaoIR.get("deducao"));
        final BigDecimal salarioLiquido = salarioBruto.subtract(valorINSS)
                .subtract(valorIRRF);
        Map<String, BigDecimal> retorno = new HashMap<>();
        retorno.put("salarioBruto", salarioBruto);
        retorno.put("aliquotaINSS", aliquotaINSS);
        retorno.put("valorINSS", valorIRRF);
        retorno.put("aliquotaIR", aliquotaDeducaoIR.get("aliquota"));
        retorno.put("deducaoIR", aliquotaDeducaoIR.get("deducao"));
        retorno.put("baseCaluloIR", baseCalculoIR);
        retorno.put("valorIRRF", valorIRRF);
        retorno.put("salarioLiquido", salarioLiquido);

        return retorno;
    }
}
