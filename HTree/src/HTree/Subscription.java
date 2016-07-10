package HTree;

import java.util.ArrayList;
import java.util.List;

public class Subscription {
	private List<SubAttribute> attributes;

	public Subscription() {
		attributes = new ArrayList<SubAttribute>();
	}
	
	public Subscription(List<SubAttribute> attributes) {
		this.attributes = attributes;
	}

	public List<SubAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<SubAttribute> attributes) {
		this.attributes = attributes;
	}
	
	public void addSubAttribute(SubAttribute sub){
		attributes.add(sub);
	}
	
	public int size(){
		return attributes.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(SubAttribute subAttri:attributes){
			sb.append(subAttri.getLolimit()+"<="+subAttri.getAttributeName()+"<="+subAttri.getHilimit()+",");
		}
		return sb.substring(0, sb.length()-1); 
	}
	
	
}
