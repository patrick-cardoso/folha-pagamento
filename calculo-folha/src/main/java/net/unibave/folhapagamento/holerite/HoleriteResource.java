package net.unibave.folhapagamento.holerite;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import net.unibave.folhapagamento.base.jaxrs.CrudResource;

@Stateless
@Path("holerites")
public class HoleriteResource extends CrudResource<Holerite, Long> {

}
