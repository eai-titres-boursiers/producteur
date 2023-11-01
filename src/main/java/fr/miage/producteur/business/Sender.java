package fr.miage.producteur.business;

import fr.miage.producteur.config.InitCollection;
import fr.miage.producteur.config.RabbitMQConfig;
import fr.miage.producteur.dao.models.TitreBoursier;
import fr.miage.producteur.dao.repositories.TitreBoursierRepository;
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

    private TitreBoursierRepository titreBoursierRepository;
    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    public void sendTitreBoursier(ExpoTitreBoursier titreBoursier) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.queueName, titreBoursier.toString());

        logger.info("Sent message: " + titreBoursier);
    }

    @Scheduled(fixedRate = 1000)
    public void checkRecords() {
        List<TitreBoursier> titresBoursiers = getTitresBoursiersFromDatabase();

        // Update the value of the titres boursiers randomly and save the changes
        for (TitreBoursier titreBoursier : titresBoursiers) {
            float randomChange = (float) (Math.random() * 2 - 1);
            float updatedValue = titreBoursier.getValue() + randomChange;
            titreBoursier.setValue(updatedValue);
            titreBoursierRepository.save(titreBoursier);
        }

        // Map and send the updated objects
        titresBoursiers
                .stream()
                .map(this::createExpoTitreBoursier)
                .forEach(this::sendTitreBoursier);
    }


    private List<TitreBoursier> getTitresBoursiersFromDatabase() {
        // If collection is empty, create some data

        if (titreBoursierRepository.findAll().isEmpty()) {
            List<TitreBoursier> titresBoursiers = InitCollection.initCollection();
            titreBoursierRepository.saveAll(titresBoursiers);
        }

        return titreBoursierRepository.findAll();
    }

    private ExpoTitreBoursier createExpoTitreBoursier(TitreBoursier titreBoursier) {
        return new ExpoTitreBoursier(
                titreBoursier.getMnemo(),
                titreBoursier.getCompany(),
                titreBoursier.getValue()
        );
    }

}
