package br.com.gobo.kafka.api.repository;

import br.com.gobo.kafka.api.model.ShopReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ReportRepository extends JpaRepository<ShopReport, Long> {
    @Modifying
    @Query(value = "UPDATE shop_report SET amount = amount+1 where identifier = :shopStatus",
    nativeQuery = true)
    void incrementShopStatus(@Param(value = "shopStatus") String shopStatus);
}
