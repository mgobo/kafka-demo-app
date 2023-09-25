package br.com.gobo.kafka.api;

import br.com.gobo.kafka.api.model.ShopReport;
import br.com.gobo.kafka.api.repository.impl.ShopReportImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShopReportRepository implements Serializable {
    public static final long serialVersionUID = 1L;
    private final ShopReportImpl shopReportImpl;

    public List<ShopReport> findAll() {
        return shopReportImpl.findAll();
    }

    public ShopReport findByIdentifier(String identifier) {
        return shopReportImpl.findByIdentifier(identifier);
    }

    public <S extends ShopReport> List<S> saveAll(Iterable<S> entities) {
        return shopReportImpl.saveAll(entities);
    }

    public void deleteAll() {
        shopReportImpl.deleteAll();
    }
}
