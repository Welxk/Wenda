package HTree;

public class PubAttribute {
	private String attributeName;
	private double attributeValue;
	
	public PubAttribute(String attributeName, double attributeValue) {
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public double getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(double attributeValue) {
		this.attributeValue = attributeValue;
	}
}
