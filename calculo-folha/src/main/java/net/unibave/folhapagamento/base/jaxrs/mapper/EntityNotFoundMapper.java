package net.unibave.folhapagamento.base.jaxrs.mapper;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import net.unibave.folhapagamento.base.jaxrs.ErrorMessage;

@Provider
public class EntityNotFoundMapper implements ExceptionMapper<EntityNotFoundException> {

    public Response toResponse(EntityNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
    }

}
