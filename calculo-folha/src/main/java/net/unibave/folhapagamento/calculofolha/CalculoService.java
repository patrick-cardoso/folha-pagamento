package net.unibave.folhapagamento.calculofolha;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import net.unibave.folhapagamento.Funcionario.Funcionario;
import net.unibave.folhapagamento.holerite.Holerite;
import net.unibave.folhapagamento.holerite.HoleriteService;

@Stateless
public class CalculoService {

    @Inject
    private HoleriteService holeriteService;

    public void calculaFolha(final CalculoFolhaDTO calculo) {

        //chamada remota de funcionarios 8080
        List<Funcionario> funcionarios = new ArrayList<>();

        for (Funcionario funcionario : funcionarios) {

            //Calculo
            Holerite holerite = Holerite.builder().funcionario(funcionario).dataFolha(calculo.getDataFolha()).valorIr(BigDecimal.ZERO).valorIss(BigDecimal.ZERO).valorSalarioLiquido(BigDecimal.ZERO).build();
            holeriteService.save(holerite);
        }
    }
}
