package gui.utils;

public class UsagerElement {
	private int usagerId;
	private String label;
	
	public UsagerElement(int usagerId, String label) {
		this.usagerId = usagerId;
		this.label = label;
	}
	
	public int getUsagerId() {
		return usagerId;
	}
	public void setUsagerId(int usagerId) {
		this.usagerId = usagerId;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}
}
