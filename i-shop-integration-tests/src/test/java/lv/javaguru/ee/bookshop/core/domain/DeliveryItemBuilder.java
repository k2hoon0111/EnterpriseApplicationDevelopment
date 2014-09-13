//package lv.javaguru.ee.deliveryagency.core.domain;
//
//import java.math.BigDecimal;
//
///**
// * Created by Viktor on 02/08/2014.
// */
//public class DeliveryItemBuilder {
//
//    private Long deliveryItemId;
//	private Delivery delivery;
//	private String productTitle;
//	private Integer productWeightInGram;
//	private BigDecimal productPrice;
//	private BigDecimal productDeliveryPrice;
//
//
//	public static DeliveryItemBuilder createDeliveryItem() {
//        return new DeliveryItemBuilder();
//    }
//
//    private DeliveryItemBuilder() { }
//
//    public DeliveryItem build() {
//        DeliveryItem deliveryItem = new DeliveryItem();
//        deliveryItem.setDeliveryItemId(deliveryItemId);
//	    deliveryItem.setDelivery(delivery);
//	    deliveryItem.setProductTitle(productTitle);
//	    deliveryItem.setProductWeightInGram(productWeightInGram);
//	    deliveryItem.setProductPrice(productPrice);
//	    deliveryItem.setProductDeliveryPrice(productDeliveryPrice);
//        return deliveryItem;
//    }
//
//    public DeliveryItemBuilder withDeliveryItemId(Long deliveryItemId) {
//        this.deliveryItemId = deliveryItemId;
//        return this;
//    }
//
//	public DeliveryItemBuilder with(Delivery delivery) {
//		this.delivery = delivery;
//		return this;
//	}
//
//	public DeliveryItemBuilder withProductTitle(String productTitle) {
//		this.productTitle = productTitle;
//		return this;
//	}
//
//	public DeliveryItemBuilder withProductWeightInGram(Integer productWeightInGram) {
//		this.productWeightInGram = productWeightInGram;
//		return this;
//	}
//
//	public DeliveryItemBuilder withProductPrice(BigDecimal productPrice) {
//		this.productPrice = productPrice;
//		return this;
//	}
//
//	public DeliveryItemBuilder withProductDeliveryPrice(BigDecimal productDeliveryPrice) {
//		this.productDeliveryPrice = productDeliveryPrice;
//		return this;
//	}
//
//}
