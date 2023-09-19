package br.com.gobo.kafka.api.repository.impl;

import br.com.gobo.kafka.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImpl extends JpaRepository<Product, Long> {

	Product findByIdentifier(String identifier);
	Product findByAmount(Integer amount);
	
}
