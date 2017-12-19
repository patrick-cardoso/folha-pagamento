package net.unibave.folhapagamento.calculofolha;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
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

    @POST
    @Override
    public Response create(CalculoFolha competencia, @Context UriInfo info) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }
}
