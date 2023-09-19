package br.com.gobo.kafka.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.gobo.kafka.api.domain.Product;
import br.com.gobo.kafka.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.gobo.kafka.web.dto.ShopDto;


@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

	private final ProductRepository productRepository;
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapAddress;
	
	public ProducerFactory<String, ShopDto> producerFactory(){
		Map<String,Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "shop-api");
		
		return new DefaultKafkaProducerFactory<String, ShopDto>(props);
	}
	
	@Bean
	KafkaTemplate<String, ShopDto> kafkaTemplate(){
		return new KafkaTemplate<String, ShopDto>(this.producerFactory());
	}
	
	public ConsumerFactory<String, ShopDto> consumerFactory(){
		JsonDeserializer<ShopDto> deserializer = new JsonDeserializer<>(ShopDto.class);
		Map<String,Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		return new DefaultKafkaConsumerFactory<String, ShopDto>(props, new StringDeserializer(), deserializer);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,ShopDto> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, ShopDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(this.consumerFactory());
		return factory;
	}
	private static final List<Product> productList = new ArrayList<>();
	static {
		productList.add(Product.builder().identifier("123456789").amount(1000).build());
		productList.add(Product.builder().identifier("444578987").amount(1000).build());
	}

	@EventListener(classes = {ApplicationStartedEvent.class})
	public void applicationStartedEvent(){
		productRepository.deleteAll();
		productRepository.saveAll(productList);
	}
}
