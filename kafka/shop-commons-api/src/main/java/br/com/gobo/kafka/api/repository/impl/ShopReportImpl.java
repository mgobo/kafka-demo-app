package br.com.gobo.kafka.api.repository.impl;

import br.com.gobo.kafka.api.model.ShopReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ShopReportImpl extends JpaRepository<ShopReport, Long> {

    @Modifying
    @Query(value = "UPDATE shop_report SET amount = amount+1 where identifier = :shopStatus",
            nativeQuery = true)
    void incrementShopStatus(@Param(value = "shopStatus") String shopStatus);

    ShopReport findByIdentifier(String identifier);
}
