package net.unibave.folhapagamento.calculofolha;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import net.unibave.folhapagamento.funcionario.Funcionario;
import net.unibave.folhapagamento.funcionario.FuncionarioService;
import net.unibave.folhapagamento.holerite.Holerite;
import net.unibave.folhapagamento.holerite.HoleriteRepository;
import net.unibave.folhapagamento.holerite.HoleriteService;

@Stateless
public class CalculoService {

    @Inject
    private HoleriteService holeriteService;
    @Inject
    private HoleriteRepository repository;
    @Inject
    private FuncionarioService funcionarioService;

    public void calculaFolha(final CalculoFolhaDTO calculo) {

        if (repository.getHoleritePorData(calculo.getDataFolha()) != null) {
            return;
        }

        for (FuncionarioDTO funcionarioLista : getFuncionarios()) {

            Funcionario funcionario = funcionarioService.save(Funcionario.builder().nome(funcionarioLista.getNome()).salarioBruto(funcionarioLista.getSalarioBruto()).build());

            //Calculo - chama método
            Holerite holerite = Holerite.builder()
                    .funcionario(funcionario)
                    .dataFolha(calculo.getDataFolha())
                    .valorIr(BigDecimal.ZERO)
                    .valorIss(BigDecimal.ZERO)
                    .valorSalarioLiquido(BigDecimal.ZERO)
                    .build();

            holeriteService.save(holerite);
        }
    }

    public List<FuncionarioDTO> getFuncionarios() {
        Client client = ClientBuilder.newClient();
        List<FuncionarioDTO> page = client.target("http://localhost:8080/funcionarios")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<FuncionarioDTO>>() {
                });
        return page;
    }
}
