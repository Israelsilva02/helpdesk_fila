package com.helpdesk.demo_backend_helpdesk.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChamadoSucessoProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void gerarResposta(String message) {
        amqpTemplate.convertAndSend(
                "chamado-response-sucesso-exchange",
                "chamado-response-sucesso-rout-key",
                message);
    }
}
