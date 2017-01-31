package com.positions;

public class Position {
	private String securityId;
	private String clientId;
	private String positionId;
	private double quantity;
	
	public String getSecurityId() {
		return securityId;
	}
	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");
		
		sb.append(this.getClass().getName() + " Object {" + NEW_LINE);
		sb.append("Security Id: " + securityId + NEW_LINE);
		sb.append("Client Id: " + clientId + NEW_LINE);
		sb.append("Position Id: " + positionId + NEW_LINE);
		sb.append("Quantity: " + quantity + NEW_LINE);
		sb.append("}");
		
		return sb.toString();
	}
}
