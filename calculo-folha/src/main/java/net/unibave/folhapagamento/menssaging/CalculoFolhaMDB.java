package net.unibave.folhapagamento.menssaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import net.unibave.folhapagamento.calculofolha.CalculoFolhaDTO;
import net.unibave.folhapagamento.calculofolha.CalculoFolhaService;
import org.jboss.ejb3.annotation.ResourceAdapter;
import org.jboss.logging.Logger;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "/jms/topic/calculo-topic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "folha-consumer"),
        @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1")
})
@ResourceAdapter("remote-mq")
public class CalculoFolhaMDB implements MessageListener {

    @Inject
    private Logger logger;

    @Inject
    private CalculoFolhaService service;

    @Inject
    private ObjectMapper mapper;

    public void onMessage(Message message) {
        try {
            String json = ((TextMessage) message).getText();
            logger.info("Consumed message: " + json);
            CalculoFolhaDTO calculo = mapper.readValue(json, CalculoFolhaDTO.class);
            //Pagamento pagamento = service.of(pedido);
            //service.save(pagamento);
        } catch (Exception e) {
            logger.error("Error consuming message:", e);
        }
    }

//    private Pagamento getPagamento(PedidoDTO pedido) {
//        ModelMapper mapper = new ModelMapper();
//        mapper.addMappings(new PropertyMap<PedidoDTO, Pagamento>() {
//            @Override
//            protected void configure() {
//                map().setId(null);
//                map().setPedido(source.getId());
//                map().setCliente(source.getCliente().getId());
//                map().setTotal(source.getItens().stream()
//                        .map(ItemPedidoDTO::getQuantidade)
//                        .reduce(BigDecimal.ZERO, BigDecimal::add));
//            }
//        });
//        return mapper.map(pedido, Pagamento.class);
//    }
}
