package dacd.cabeza.model;

import com.google.gson.annotations.SerializedName;

public class HotelRate {
	@SerializedName("code")
	private String providerCode;

	@SerializedName("name")
	private String providerName;

	@SerializedName("rate")
	private double rate;

	@SerializedName("tax")
	private double tax;

	private String currency;
	private String checkIn;
	private String checkOut;
	private int roomsRequested;  // Habitaciones solicitadas
	private int adults;          // Número de adultos
	private int children;        // Número de niños

	// Getters y Setters
	public String getProviderCode() { return providerCode; }
	public void setProviderCode(String providerCode) { this.providerCode = providerCode; }

	public String getProviderName() { return providerName; }
	public void setProviderName(String providerName) { this.providerName = providerName; }

	public double getRate() { return rate; }
	public void setRate(double rate) { this.rate = rate; }

	public double getTax() { return tax; }
	public void setTax(double tax) { this.tax = tax; }

	public String getCurrency() { return currency; }
	public void setCurrency(String currency) { this.currency = currency; }

	public String getCheckIn() { return checkIn; }
	public void setCheckIn(String checkIn) { this.checkIn = checkIn; }

	public String getCheckOut() { return checkOut; }
	public void setCheckOut(String checkOut) { this.checkOut = checkOut; }

	public int getRoomsRequested() { return roomsRequested; }
	public void setRoomsRequested(int roomsRequested) { this.roomsRequested = roomsRequested; }

	public int getAdults() { return adults; }
	public void setAdults(int adults) { this.adults = adults; }

	public int getChildren() { return children; }
	public void setChildren(int children) { this.children = children; }
}
