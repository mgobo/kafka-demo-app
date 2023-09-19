package br.com.gobo.kafka.web.mappers;

import br.com.gobo.kafka.api.model.Shop;
import br.com.gobo.kafka.web.dto.ShopDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ShopMapper {

	@Mapping(source = "items",target = "shopItensCollection")
	Shop toEntity(ShopDto shopDto);
	
	@Mapping(source = "shopItensCollection",target = "items")
	ShopDto toDto(Shop shop);
	
}
