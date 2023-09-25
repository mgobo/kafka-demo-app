package br.com.gobo.kafka.api.repository;

import java.util.List;
import java.util.Optional;

import br.com.gobo.kafka.api.repository.impl.ShopImpl;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gobo.kafka.api.model.Shop;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ShopRepository {

	private final ShopImpl shopImpl;

	public List<Shop> findAll() {
		return shopImpl.findAll();
	}

	public <S extends Shop> S save(S entity) {
		return shopImpl.save(entity);
	}

	public Optional<Shop> findByIdentifier(String identifier) {
		return shopImpl.findByIdentifier(identifier);
	}
}
