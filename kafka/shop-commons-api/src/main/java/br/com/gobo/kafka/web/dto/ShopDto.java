package br.com.gobo.kafka.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ShopDto {

	private String identifier;
	private String status;
	private LocalDate dateShop;
	
	@Builder.Default
	private List<ShopItemDto> items = new ArrayList<>();
	
}
