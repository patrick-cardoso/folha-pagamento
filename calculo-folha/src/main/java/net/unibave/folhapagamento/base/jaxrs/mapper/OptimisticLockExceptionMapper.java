package net.unibave.folhapagamento.base.jaxrs.mapper;

import javax.persistence.OptimisticLockException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import net.unibave.folhapagamento.base.jaxrs.ErrorMessage;

@Provider
public class OptimisticLockExceptionMapper implements ExceptionMapper<OptimisticLockException> {

    public Response toResponse(OptimisticLockException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return Response.status(Response.Status.CONFLICT).entity(errorMessage).build();
    }

}
