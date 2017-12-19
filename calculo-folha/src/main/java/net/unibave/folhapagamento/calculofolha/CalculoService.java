package net.unibave.folhapagamento.calculofolha;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CalculoService {

    @Inject
    private HoleriteService holeriteService;
    @Inject
    private HoleriteRepository repository;
    @Inject
    private FuncionarioService funcionarioService;

    public void calculaFolha(final CalculoFolhaDTO calculo) {

        if (!repository.findAll().stream().filter(p -> p.getDataFolha().getMonth().equals(calculo.getDataFolha().getMonth())).collect(Collectors.toList()).isEmpty()) {
            return;
        }

        int i = 0;
        int j = getFuncionarios().size();
        List<Holerite> holeritesPersistir = new ArrayList<>();

        for (FuncionarioDTO funcionarioLista : getFuncionarios()) {
            i++;
            j--;
            Funcionario funcionario = funcionarioService.save(Funcionario.builder().nome(funcionarioLista.getNome()).salarioBruto(funcionarioLista.getSalarioBruto()).build());

            Map<String, BigDecimal> calculaFolha = CalculoFolha.calculaFolha(funcionario.getSalarioBruto());

            Holerite holerite = Holerite.builder()
                    .funcionario(funcionario)
                    .dataFolha(calculo.getDataFolha())
                    .valorIr(calculaFolha.get("valorIRRF"))
                    .aliquotaIR(calculaFolha.get("aliquotaIR"))
                    .aliquotaINSS(calculaFolha.get("aliquotaINSS"))
                    .valorIss(calculaFolha.get("valorINSS"))
                    .valorSalarioLiquido(calculaFolha.get("salarioLiquido"))
                    .valorSalarioBruto(calculaFolha.get("salarioBruto"))
                    .valorSalarioLiquido(calculaFolha.get("salarioLiquido"))
                    .deducaoIR(calculaFolha.get("deducaoIR"))
                    .baseCaluloIR(calculaFolha.get("baseCaluloIR"))
                    .build();

            if (i < 5) {
                holeritesPersistir.add(holerite);
            }
            if (i == 5) {
                holeriteService.saveMultiplos(holeritesPersistir);
                holeritesPersistir = new ArrayList<>();
                i = 0;
            }
        }
        if (i > 0) {
            holeriteService.saveMultiplos(holeritesPersistir);
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
