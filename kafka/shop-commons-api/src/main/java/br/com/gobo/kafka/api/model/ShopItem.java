package br.com.gobo.kafka.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "shop_item")
@SequenceGenerator(name = "shopitem_seq", sequenceName = "shopitem_seq", initialValue = 1, allocationSize = 1)
public class ShopItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopitem_seq")
	private Long id;
	
	@Column(name = "product_identifier")
	private String productIdentifier;
	
	private Integer amount;
	
	private Float price;
	
	@ManyToOne
	@JoinColumn(name = "shop_id")
	private Shop shop;
}
