package br.com.gobo.kafka.services.events;

import br.com.gobo.kafka.api.model.Product;
import br.com.gobo.kafka.api.model.Shop;
import br.com.gobo.kafka.api.repository.ShopRepository;
import br.com.gobo.kafka.api.repository.impl.ProductImpl;
import br.com.gobo.kafka.web.dto.ShopDto;
import br.com.gobo.kafka.web.dto.ShopItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessage {

    private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";
    private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

    private final ProductImpl productImpl;
    private final ShopRepository shopRepository;
    private final KafkaTemplate<String, ShopDto> kafkaTemplate;

    @KafkaListener(topics = SHOP_TOPIC_NAME, groupId = "group")
    public void listenShopTopic(ShopDto shopDto) {
        try {
            log.info("Compra recebida no topico: {}.", shopDto.getIdentifier());
            boolean success = true;
            for (ShopItemDto item : shopDto.getItems()) {
                Product product = productImpl.findByIdentifier(item.getProductIdentifier());
                if (!isValidShop(item, product)) {
                    shopError(shopDto);
                    success = false;
                    break;
                }
            }
            if (success) {
                shopSuccess(shopDto);
            }
        } catch (Exception e) {
            log.error("Erro no processamento da compra {}", shopDto.getIdentifier());
        }
    }

    @KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group")
    public void listenShopEvents(ShopDto shopDto) {
        try {
            log.info("Status da compra recebida no topico: {}.", shopDto.getIdentifier());
            Optional<Shop> shop = this.shopRepository.findByIdentifier(shopDto.getIdentifier());
            if (shop.isPresent()) {
                shop.get().setStatus(shopDto.getStatus());
                this.shopRepository.save(shop.get());
            }
        } catch (Exception e) {
            log.error("Erro no processamento da compra {}", shopDto.getIdentifier());
        }
    }

    private boolean isValidShop(ShopItemDto item, Product product) {
        return product != null && product.getAmount() >= item.getAmount();
    }

    // Envia uma mensagem para o Kafka indicando erro na compra
    private void shopError(ShopDto shopDto) {
        log.info("Erro no processamento da compra {}.", shopDto.getIdentifier());
        shopDto.setStatus("ERROR");
        kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDto);
    }

    // Envia uma mensagem para o Kafka indicando sucesso na compra
    private void shopSuccess(ShopDto shopDto) {
        log.info("Compra {} efetuada com sucesso.", shopDto.getIdentifier());
        shopDto.setStatus("SUCCESS");
        kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDto);
    }

}
