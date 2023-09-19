package br.com.gobo.kafka.api.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gobo.kafka.api.model.ShopItem;

public interface ShopItemImpl extends JpaRepository<ShopItem, Long>{

}
