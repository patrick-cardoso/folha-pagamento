package net.unibave.folhapagamento.base.jaxrs;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        Map<String, String> map = new HashMap<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            String atribute = violation.getPropertyPath().toString();
            String entity = violation.getRootBean().getClass().getSimpleName();
            String key = String.format("%s.%s", entity, atribute);
            map.put(key, violation.getMessage());
        }
        return Response.status(BAD_REQUEST)
                .entity(map)
                .build();
    }

}
