package com.groupify.groupify.dto;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

	@SerializedName("country")
	@Expose
	private String country;
	@SerializedName("display_name")
	@Expose
	private String displayName;
	@SerializedName("first_name")
	@Expose
	private String firstName;
	@SerializedName("last_name")
	@Expose
	private String lastName;
	@SerializedName("phone_number")
	@Expose
	private String phoneNumber;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("external_urls")
	@Expose
	private ExternalUrls externalUrls;
	@SerializedName("followers")
	@Expose
	private Followers followers;
	@SerializedName("href")
	@Expose
	private String href;
	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("user_id")
	@Expose
	private int userId;
	@SerializedName("images")
	@Expose
	private List<GroupifyImage> images = null;
	@SerializedName("product")
	@Expose
	private String product;
	@SerializedName("type")
	@Expose
	private String type;
	@SerializedName("uri")
	@Expose
	private String uri;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getFirstName() { return firstName; }

	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }

	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getPhoneNumber() { return phoneNumber; }

	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ExternalUrls getExternalUrls() {
		return externalUrls;
	}

	public void setExternalUrls(ExternalUrls externalUrls) {
		this.externalUrls = externalUrls;
	}

	public Followers getFollowers() {
		return followers;
	}

	public void setFollowers(Followers followers) {
		this.followers = followers;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<GroupifyImage> getImages() {
		return images;
	}

	public void setImages(List<GroupifyImage> images) {
		this.images = images;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
