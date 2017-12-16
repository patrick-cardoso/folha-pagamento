package net.unibave.folhapagamento.funcionario;


import javax.ejb.Stateless;
import javax.ws.rs.Path;
import net.unibave.folhapagamento.base.jaxrs.CrudResource;

@Stateless
@Path("funcionarios")
public class FuncionarioResource extends CrudResource<Funcionario, Long> {
}
