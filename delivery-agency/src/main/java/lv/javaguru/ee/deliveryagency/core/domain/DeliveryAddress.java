package lv.javaguru.ee.deliveryagency.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Viktor on 26/07/2014.
 */
@Entity
@Table(name="deliveryAddresses")
public class DeliveryAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="deliveryAddressId", nullable = false)
	private Long deliveryAddressId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "deliveryId", nullable = false)
    private Delivery delivery;

    @Column(name="city", nullable = false)
    private String city;

	@Column(name="postIndex", nullable = false)
    private String postIndex;

	@Column(name="street", nullable = false)
    private String street;

	@Column(name="house", nullable = false)
    private String house;

	@Column(name="flat", nullable = true)
    private String flat;


    public Long getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Long deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(String postIndex) {
        this.postIndex = postIndex;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

}
