package br.com.gobo.kafka.consumers;

import br.com.gobo.kafka.api.repository.impl.ShopReportImpl;
import br.com.gobo.kafka.web.dto.ShopDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional(propagation = Propagation.REQUIRED)
public class ReceiveKafkaMessage {

    private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";
    private final ShopReportImpl shopReportImpl;

    @KafkaListener(topics = SHOP_TOPIC_EVENT_NAME,
            groupId = "group_report")
    public void listenShopTopic(ShopDto shopDto) {
        try {
            log.info("Compra recebida no tópico: {}.",
                    shopDto.getIdentifier());
            shopReportImpl.incrementShopStatus(shopDto.getStatus());
        } catch (Exception e) {
            log.error("Erro no processamento da mensagem", e);
        }
    }

}
