package br.com.gobo.kafka.web.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gobo.kafka.api.model.Shop;
import br.com.gobo.kafka.api.kafka.KafkaClient;
import br.com.gobo.kafka.api.repository.ShopRepository;
import br.com.gobo.kafka.web.dto.ShopDto;
import br.com.gobo.kafka.web.mappers.ShopMapper;
import br.com.gobo.kafka.web.mappers.ShopMapperImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

	private final KafkaClient kafkaClient;
	private final ShopRepository shopRepository;
	private final ShopMapper shopDtoMapper = new ShopMapperImpl();
	private final br.com.gobo.kafka.api.repository.impl.ShopImpl shopImpl;
	
	@GetMapping
	public List<ShopDto> getShop(){
		return this.shopRepository
				.findAll()
				.stream()
				.map(shop -> shopDtoMapper.toDto(shop))
				.collect(Collectors.toList());
	}
	
	@PostMapping
	public ShopDto saveShop(@RequestBody ShopDto shopDto) {
		shopDto.setIdentifier(UUID.randomUUID().toString());
		shopDto.setDateShop(LocalDate.now());
		shopDto.setStatus("PENDING");
		Shop shop = shopDtoMapper.toEntity(shopDto);
		shop.getShopItensCollection().forEach(item->{
			item.setShop(shop);
		});
		
		shopDto = shopDtoMapper.toDto(this.shopRepository.save(shop));
		this.kafkaClient.sendMessage(shopDto);
		return shopDto;
	}

	@PostMapping("/validarPedido")
	public ResponseEntity<ShopDto> validarPedido(@RequestBody ShopDto shopDto){
		Optional<Shop> shop = this.shopRepository.findByIdentifier(shopDto.getIdentifier());
		if(shop.isEmpty()){
			return ResponseEntity.notFound().build();
		}
		this.kafkaClient.sendMessage(shopDto);
		return ResponseEntity.ok(shopDto);
	}
}
