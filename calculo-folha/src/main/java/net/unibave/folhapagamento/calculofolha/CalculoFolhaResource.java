package net.unibave.folhapagamento.calculofolha;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import net.unibave.folhapagamento.base.jaxrs.CrudResource;

@Stateless
@Path("calculos-folhas")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class CalculoFolhaResource extends CrudResource<CalculoFolha, Long> {

    @Inject
    private CalculoFolhaService service;

    @PUT
    public Response update(CalculoFolha competencia) {
        service.save(competencia);
        return Response.noContent().build();
    }

}
