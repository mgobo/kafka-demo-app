package br.com.gobo.kafka.configuration;

import br.com.gobo.kafka.api.ShopReportRepository;
import br.com.gobo.kafka.api.model.ShopReport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ShopReportConfig {

    private final ShopReportRepository shopReportRepository;

    @EventListener(classes = {ApplicationStartedEvent.class})
    public void applicationStartedEvent() {
        List<ShopReport> shopReportCollection = this.shopReportRepository.findAll();
        if (shopReportCollection.isEmpty()) {
            log.info("Iniciando collections...");
            shopReportCollection = List.of(ShopReport.builder().amount(0l).identifier("SUCCESS").build(),
                    ShopReport.builder().amount(0l).identifier("ERROR").build());
            this.shopReportRepository.saveAll(shopReportCollection);
            log.info("Registros gerados...");
        }
    }
}
