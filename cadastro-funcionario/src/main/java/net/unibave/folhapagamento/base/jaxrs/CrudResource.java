package net.unibave.folhapagamento.base.jaxrs;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import net.unibave.folhapagamento.base.AbstractRepository;
import net.unibave.folhapagamento.base.AbstractService;
import net.unibave.folhapagamento.base.EntityId;
import org.jboss.logging.Logger;

@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public abstract class CrudResource<T extends EntityId<PK>, PK> {

    @Inject
    private AbstractRepository<T, PK> repository;

    @Inject
    private AbstractService<T, PK> service;

    @Inject
    private Logger logger;

    @PostConstruct
    public void init() {
        logger.info("Objeto pronto");
    }

    @PreDestroy
    public void close() {
        logger.info("Destruindo objeto...");
    }

    @GET
    public Response findAll() {
        logger.info("findAll");
        List<T> entities = repository.findAll();
        return Response.ok(entities).build();
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") PK id) {
        T entity = repository.find(id);
        return Response.ok(entity).build();
    }

    @POST
    public Response create(T entity, @Context UriInfo info) {
        T entity_ = service.save(entity);
        UriBuilder builder = info.getAbsolutePathBuilder();
        builder.path(entity_.getId().toString());
        return Response.created(builder.build())
                .entity(entity_)
                .build();
    }

    @PUT
    public Response update(T entity) {
        service.save(entity);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") PK id) {
        service.delete(id);
        return Response.noContent().build();
    }

}
