package siena.makeData;

import java.util.ArrayList;

import HTree.SubAttribute;

public class Line{
	private ArrayList<Predicate> row;
	public Line() {
		row = new ArrayList<Predicate>();
	}
	public Line(ArrayList<Predicate> row) {
		super();
		this.row = row;
	}
	public ArrayList<Predicate> getRow() {
		return row;
	}
	public void setRow(ArrayList<Predicate> row) {
		this.row = row;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Predicate predicate:row){
			sb.append(predicate.getLowlimit()+"<="+predicate.getPredicateName()+"<="+predicate.getHighlimit()+",");
		}
		return sb.substring(0, sb.length()-1); 
	}
	
}
