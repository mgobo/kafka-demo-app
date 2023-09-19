package br.com.gobo.kafka.api.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.gobo.kafka.web.dto.ShopDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaClient {

	private final KafkaTemplate<String, ShopDto> kafkaTemplate;
	private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";
	
	public void sendMessage(ShopDto shopDto) {
		this.kafkaTemplate.send(SHOP_TOPIC_NAME, shopDto);
	}
	
}
