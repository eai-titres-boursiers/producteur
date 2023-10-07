package fr.miage.producteur.business;

import fr.miage.producteur.exposition.models.ExpoTitreBoursier;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Sender {
    static String QUEUE_NAME = "titres-boursiers";

    private final RabbitTemplate rabbitTemplate;

    public void sendTitreBoursier(ExpoTitreBoursier titreBoursier) {
        rabbitTemplate.convertAndSend(QUEUE_NAME, titreBoursier.toString());
    }

    @Scheduled(fixedRate=500)
    public void checkRecords() {
        ExpoTitreBoursier titreBoursier = new ExpoTitreBoursier("TEST");
        sendTitreBoursier(titreBoursier);
    }

}
