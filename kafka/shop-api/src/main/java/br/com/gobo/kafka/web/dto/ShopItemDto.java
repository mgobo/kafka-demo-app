package br.com.gobo.kafka.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopItemDto {

	private String productIdentifier;
	private Integer amount;
	private Float price;
	
}
