package br.com.gobo.kafka.api.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "shop")
@SequenceGenerator(name = "shop_seq", sequenceName = "shop_seq", initialValue = 1, allocationSize = 1)
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shop_seq")
	private Long id;
	private String identifier;
	private String status;
	
	@Column(name = "date_shop")
	private LocalDate dateShop;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "shop")
	private List<ShopItem> shopItensCollection;
	
}
