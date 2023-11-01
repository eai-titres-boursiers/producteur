package fr.miage.producteur.business;

import fr.miage.producteur.config.RabbitMQConfig;
import fr.miage.producteur.exposition.ExpoTitreBoursier;
import fr.saurel.remi.tp_tb.shared_tb.TitreBoursierDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class Sender {

    private final RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    public void sendTitreBoursier(ExpoTitreBoursier titreBoursier) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, titreBoursier.toString());

        logger.info("Sent message: " + titreBoursier);
    }

    @Scheduled(fixedRate = 1000)
    public void checkRecords() {
        List<TitreBoursierDTO> titresBoursiers = getTitresBoursiersFromDatabase();

        titresBoursiers.stream()
                .map(this::createExpoTitreBoursier)
                .forEach(this::sendTitreBoursier);
    }

    // TODO : implement this method with real database call
    private List<TitreBoursierDTO> getTitresBoursiersFromDatabase() {
        TitreBoursierDTO microsoft = new TitreBoursierDTO("MIC", "Microsoft", 15);
        TitreBoursierDTO apple = new TitreBoursierDTO("APP", "Apple", 23);
        TitreBoursierDTO google = new TitreBoursierDTO("GOO", "Google", 12);

        List<TitreBoursierDTO> titresBoursiers = new ArrayList<>();
        titresBoursiers.add(microsoft);
        titresBoursiers.add(apple);
        titresBoursiers.add(google);

        return titresBoursiers;
    }

    private ExpoTitreBoursier createExpoTitreBoursier(TitreBoursierDTO titreBoursier) {
        return new ExpoTitreBoursier(titreBoursier.getMnemo(), titreBoursier.getValue());
    }

}
