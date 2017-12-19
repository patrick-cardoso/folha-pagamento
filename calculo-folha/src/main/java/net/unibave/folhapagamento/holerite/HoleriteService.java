package net.unibave.folhapagamento.holerite;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import net.unibave.folhapagamento.base.AbstractService;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class HoleriteService extends AbstractService<Holerite, Long> {

    public void saveMultiplos(List<Holerite> holerites) {
        for (Holerite holerite : holerites) {
            this.save(holerite);
        }
    }
}
