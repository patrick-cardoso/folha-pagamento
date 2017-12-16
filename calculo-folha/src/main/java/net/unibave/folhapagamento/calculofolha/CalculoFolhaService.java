package net.unibave.folhapagamento.calculofolha;

import javax.ejb.Stateless;
import javax.inject.Inject;
import net.unibave.folhapagamento.base.AbstractService;

@Stateless
public class CalculoFolhaService extends AbstractService<CalculoFolha, Long> {
    
    @Inject
    private CalculoFolhaSender calculoFolhaSender;
    
    @Override
    public CalculoFolha save(CalculoFolha calculo) {
        calculoFolhaSender.send(calculo);
        return super.save(calculo);
    }
    
}
