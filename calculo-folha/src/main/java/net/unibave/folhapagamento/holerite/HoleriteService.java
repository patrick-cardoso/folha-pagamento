package net.unibave.folhapagamento.holerite;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import net.unibave.folhapagamento.base.AbstractService;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class HoleriteService extends AbstractService<Holerite, Long> {

}
