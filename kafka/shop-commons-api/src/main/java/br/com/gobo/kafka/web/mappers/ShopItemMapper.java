package br.com.gobo.kafka.web.mappers;

import br.com.gobo.kafka.api.model.ShopItem;
import br.com.gobo.kafka.web.dto.ShopItemDto;
import org.mapstruct.Mapper;

@Mapper
public interface ShopItemMapper {

	ShopItem toEntity(ShopItemDto shopDto);
	ShopItemDto toDto(ShopItem shop);
	
}
