package com.cooksys.merchantcreator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MerchantLocation {

	private long merchantLocationId;

	private String locationName;
	private String addressStreet;
	private String addressCity;
	private String addressState;
	private String addressZip;
	private String phone;

	private String hours;

	private Double lattitude;
	private Double longitude;

	@JsonCreator
	public MerchantLocation(@JsonProperty("id") long merchantLocationId, @JsonProperty("name") String locationName,
			@JsonProperty("street") String addressStreet, @JsonProperty("city") String addressCity,
			@JsonProperty("state") String addressState, @JsonProperty("zip") String addressZip,
			@JsonProperty("phone") String phone, @JsonProperty("hours") String hours,
			@JsonProperty("lattitude") double lattitude, @JsonProperty("longitude") double longitude) {
		super();
		this.merchantLocationId = merchantLocationId;
		this.locationName = locationName;
		this.addressStreet = addressStreet;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressZip = addressZip;
		this.phone = phone;
		this.hours = hours;
		this.lattitude = lattitude;
		this.longitude = longitude;
	}

	public Long getMerchantLocationId() {
		return merchantLocationId;
	}

	public void setMerchantLocationId(Long merchantLocationId) {
		this.merchantLocationId = merchantLocationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public String getAddressZip() {
		return addressZip;
	}

	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public Double getLattitude() {
		return lattitude;
	}

	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "MerchantLocation [merchantLocationId=" + merchantLocationId + ", locationName=" + locationName
				+ ", addressStreet=" + addressStreet + ", addressCity=" + addressCity + ", addressState=" + addressState
				+ ", addressZip=" + addressZip + ", phone=" + phone + ", hours=" + hours + ", lattitude=" + lattitude
				+ ", longitude=" + longitude + "]";
	}

}
