package net.unibave.folhapagamento.base.jaxrs.wrapper;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.RollbackException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

@Provider
public class RollbackExceptionMapper implements ExceptionMapper<RollbackException> {

    @Inject
    private Logger logger;

    @Context
    private Providers providers;

    public Response toResponse(RollbackException exception) {
        logger.info("Entrou");
        if (exception.getCause() == null) {
            return Response.serverError().build();
        }
        Class cause = exception.getCause().getClass();
        ExceptionMapper mapper = providers.getExceptionMapper(cause);
        if (mapper == null) {
            return Response.serverError().build();
        } else {
            return mapper.toResponse(exception.getCause());
        }
    }
}
