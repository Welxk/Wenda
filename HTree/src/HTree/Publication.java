package HTree;

import java.util.ArrayList;
import java.util.List;

public class Publication {
	
	List<PubAttribute> attributes;
	
	public Publication() {
		attributes = new ArrayList<PubAttribute>();
	}

	public Publication(List<PubAttribute> attributes) {
		this.attributes = attributes;
	}

	public List<PubAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<PubAttribute> attributes) {
		this.attributes = attributes;
	}
	
	public void add(PubAttribute pub){
		attributes.add(pub);
	}
	
	public int size(){
		return attributes.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		//sb.append("<");
		for(PubAttribute pubAttri:attributes){
			sb.append(pubAttri.getAttributeName()+"="+pubAttri.getAttributeValue()+",");
		}
		
		return sb.substring(0, sb.length()-1); 
	}
}
