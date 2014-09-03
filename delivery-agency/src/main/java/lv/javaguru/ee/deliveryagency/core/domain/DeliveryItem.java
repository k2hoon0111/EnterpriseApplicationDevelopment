package lv.javaguru.ee.deliveryagency.core.domain;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Viktor on 26/07/2014.
 */
@Entity
@Table(name="deliveryItems")
public class DeliveryItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="deliveryItemId", nullable = false)
	private Long deliveryItemId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "deliveryId", nullable = false)
    private Delivery delivery;

	@Column(name="productTitle", nullable = false)
	private String productTitle;

	@Column(name="productWeightInGram", nullable = false)
	private Integer productWeightInGram;

	@Column(name="productPrice", nullable = false)
	private BigDecimal productPrice;

	@Column(name="productDeliveryPrice", nullable = false)
	private BigDecimal productDeliveryPrice;	


	public Long getDeliveryItemId() {
		return deliveryItemId;
	}

	public void setDeliveryItemId(Long deliveryItemId) {
		this.deliveryItemId = deliveryItemId;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public Integer getProductWeightInGram() {
		return productWeightInGram;
	}

	public void setProductWeightInGram(Integer productWeightInGram) {
		this.productWeightInGram = productWeightInGram;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public BigDecimal getProductDeliveryPrice() {
		return productDeliveryPrice;
	}

	public void setProductDeliveryPrice(BigDecimal productDeliveryPrice) {
		this.productDeliveryPrice = productDeliveryPrice;
	}
	
}
