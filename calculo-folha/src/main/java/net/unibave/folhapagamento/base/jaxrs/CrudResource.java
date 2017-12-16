package net.unibave.folhapagamento.base.jaxrs;

import net.unibave.folhapagamento.base.AbstractRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import net.unibave.folhapagamento.base.AbstractService;
import net.unibave.folhapagamento.base.EntityId;

@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public abstract class CrudResource<T extends EntityId, PK> {

    @Inject
    private AbstractRepository<T, PK> repository;

    @Inject
    private AbstractService<T, PK> service;

    @GET
    public Response findAll() {
        return Response.ok(repository.findAll()).build();
    }
    
    @GET
    @Path(value = "{id}")
    public Response find(@PathParam(value = "id") PK id) {
        return Response.ok(repository.findOrThrow(id)).build();
    }
    
    @POST
    public Response create(T entidade, @Context UriInfo info) {
        T entidade_ = service.save(entidade);
        UriBuilder builder = info.getAbsolutePathBuilder();
        builder.path(entidade_.getId().toString());
        return Response.created(builder.build()).entity(entidade_).build();
    }
    
    @DELETE
    @Path(value = "{id}")
    public Response delete(@PathParam(value = "id") PK id) {
        service.deleteOrThrow(id);
        return Response.noContent().build();
    }

    @PUT
    @Path(value = "{id}")
    public Response update(@PathParam(value = "id") PK id, T entidade) {
        repository.findReferenceOrThrow(id);
        service.save(entidade);
        return Response.noContent().build();
    }
    
}
