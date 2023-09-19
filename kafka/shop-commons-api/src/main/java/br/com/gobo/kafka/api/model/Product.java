package br.com.gobo.kafka.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@SequenceGenerator(sequenceName = "product_seq", name = "product_seq", allocationSize = 1, initialValue = 1)
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
	private Long id;
	
	@Column(name="product_identifier")
	private String identifier;
	private Integer amount;
}
