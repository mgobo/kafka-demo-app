package br.com.gobo.kafka.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder @Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "shop_report")
@SequenceGenerator(name = "shopreport_seq", sequenceName = "shopreport_seq",initialValue = 1,allocationSize = 1)
public class ShopReport implements Serializable {
    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "shopreport_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, nullable = false)
    private String identifier;

    @Column(nullable = false)
    private Long amount;
}
