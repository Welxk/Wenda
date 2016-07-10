package HTree;

public class SubAttribute {
	private String attributeName;
	private double lolimit;
	private double hilimit;
	
	public SubAttribute(String attributeName, double lolimit, double hilimit) {
		this.attributeName = attributeName;
		this.lolimit = lolimit;
		this.hilimit = hilimit;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public double getLolimit() {
		return lolimit;
	}

	public void setLolimit(double lolimit) {
		this.lolimit = lolimit;
	}

	public double getHilimit() {
		return hilimit;
	}

	public void setHilimit(double hilimit) {
		this.hilimit = hilimit;
	}
}
