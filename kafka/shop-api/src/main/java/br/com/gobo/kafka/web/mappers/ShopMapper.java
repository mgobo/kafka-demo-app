package br.com.gobo.kafka.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.gobo.kafka.api.model.Shop;
import br.com.gobo.kafka.web.dto.ShopDto;

@Mapper
public interface ShopMapper {

	@Mapping(source = "items",target = "shopItensCollection")
	Shop toEntity(ShopDto shopDto);
	
	@Mapping(source = "shopItensCollection",target = "items")
	ShopDto toDto(Shop shop);
	
}
