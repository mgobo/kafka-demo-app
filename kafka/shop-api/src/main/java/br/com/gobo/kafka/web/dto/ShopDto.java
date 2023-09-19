package br.com.gobo.kafka.web.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopDto {

	private String identifier;
	private String status;
	private LocalDate dateShop;
	
	@Builder.Default
	private List<ShopItemDto> items = new ArrayList<>();
	
}
