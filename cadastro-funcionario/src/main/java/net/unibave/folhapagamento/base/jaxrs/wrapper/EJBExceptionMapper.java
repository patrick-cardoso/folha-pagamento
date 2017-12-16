package net.unibave.folhapagamento.base.jaxrs.wrapper;

import org.jboss.logging.Logger;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

    @Inject
    private Logger logger;

    @Context
    private Providers providers;

    @Override
    public Response toResponse(EJBException e) {
        logger.info("Entrou");
        if (e.getCausedByException() == null) {
            return Response.serverError().build();
        }
        Class<? extends Exception> cause = e.getCausedByException().getClass();
        ExceptionMapper mapper = providers.getExceptionMapper(cause);
        if (mapper != null) {
            return mapper.toResponse(e.getCausedByException());
        } else {
            return Response.serverError().build();
        }
    }

}
