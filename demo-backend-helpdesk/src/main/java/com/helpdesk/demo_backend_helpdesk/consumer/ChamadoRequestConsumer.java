package com.helpdesk.demo_backend_helpdesk.consumer;

import com.helpdesk.demo_backend_helpdesk.producer.ChamadoErroProducer;
import com.helpdesk.demo_backend_helpdesk.producer.ChamadoSucessoProducer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("ChamadoRequestConsumer")
public class ChamadoRequestConsumer {
    @Autowired
    private ChamadoErroProducer chamadoErroProducer;
    @Autowired
    private ChamadoSucessoProducer chamadoSucessoProducer;

    @RabbitListener(queues = {"chamado-request-queue"})
    public void receberMensagem(@Payload Message message) {
        System.out.println(message);
        if(new Random().nextBoolean()){
            chamadoSucessoProducer.gerarResposta("Chamado aberto com sucesso" + message);
        }
        chamadoErroProducer.gerarResposta("Erro ao tentar abrir o chamado" + message);
    }
}
