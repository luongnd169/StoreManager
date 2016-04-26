package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ItemDetail {
	private Integer itemId;
	private String imei;
	private String color;

	public ItemDetail() {
	}

	@Id
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "ItemDetail [itemId=" + itemId + ", imei=" + imei + ", color=" + color + "]";
	}

}
