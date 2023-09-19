package br.com.gobo.kafka.api.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gobo.kafka.api.model.Shop;

import java.util.Optional;

public interface ShopImpl extends JpaRepository<Shop, Long>{
    Optional<Shop> findByIdentifier(String identifier);
}
