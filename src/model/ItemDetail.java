package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ItemDetail {
	private Integer itemId;
	private String imei;

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

	@Override
	public String toString() {
		return "ItemDetail [itemId=" + itemId + ", imei=" + imei + "]";
	}
	
}
