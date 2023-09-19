package br.com.gobo.kafka.web.mappers;

import org.mapstruct.Mapper;

import br.com.gobo.kafka.api.model.ShopItem;
import br.com.gobo.kafka.web.dto.ShopItemDto;

@Mapper
public interface ShopItemMapper {

	ShopItem toEntity(ShopItemDto shopDto);
	ShopItemDto toDto(ShopItem shop);
	
}
