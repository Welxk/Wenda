package siena.makeData;

public class Predicate {
	private String predicateName;
	private float lowlimit;
	private float highlimit;
	public Predicate() {
	}
	public Predicate(String predicateName, float lowlimit, float highlimit) {
		super();
		this.predicateName = predicateName;
		this.lowlimit = lowlimit;
		this.highlimit = highlimit;
	}
	public String getPredicateName() {
		return predicateName;
	}
	public void setPredicateName(String predicateName) {
		this.predicateName = predicateName;
	}
	public float getLowlimit() {
		return lowlimit;
	}
	public void setLowlimit(float lowlimit) {
		this.lowlimit = lowlimit;
	}
	public float getHighlimit() {
		return highlimit;
	}
	public void setHighlimit(float highlimit) {
		this.highlimit = highlimit;
	}
}