package br.com.gobo.kafka.api.repository;

import br.com.gobo.kafka.api.model.Product;
import br.com.gobo.kafka.api.repository.impl.ProductImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository implements Serializable {
    public static final long serialVersionUID = 1L;

    private final ProductImpl productImpl;

    public Product findByIdentifier(String identifier) {
        return productImpl.findByIdentifier(identifier);
    }

    public Product findByAmount(Integer amount) {
        return productImpl.findByAmount(amount);
    }

    public List<Product> findAll() {
        return productImpl.findAll();
    }
}
