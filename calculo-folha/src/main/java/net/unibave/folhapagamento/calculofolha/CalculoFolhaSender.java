package net.unibave.folhapagamento.calculofolha;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;
import org.jboss.logging.Logger;

@Stateless
public class CalculoFolhaSender {

    @Inject
    private Logger logger;

    @Inject
    @JMSConnectionFactory("java:/jms/remote-mq")
    private JMSContext context;

    @Resource(mappedName = "java:/jms/topic/calculo-topic")
    private Topic topic;

    @Inject
    private ObjectMapper mapper;

    public void send(CalculoFolha calculo) {
        try {
            String json = mapper.writeValueAsString(calculo);
            logger.info("Enviando " + json);
            context.createProducer().send(topic, json);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

}
