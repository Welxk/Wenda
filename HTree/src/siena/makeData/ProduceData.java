package siena.makeData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ProduceData {
	private Map<Integer,ArrayList<Predicate>> table;
	private int lineNum;
	private int predicateNum;
	private String[] predicateNames;
	
	public String[] getPredicateNames() {
		return predicateNames;
	}

	public void setPredicateNames(String[] predicateNames) {
		this.predicateNames = predicateNames;
	}

	public ProduceData() {
		table = new HashMap<Integer,ArrayList<Predicate>>();
	}
	
	public Map<Integer, ArrayList<Predicate>> getTable() {
		return table;
	}

	public void setTable(Map<Integer, ArrayList<Predicate>> table) {
		this.table = table;
	}

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	public int getPredicateNum() {
		return predicateNum;
	}

	public void setPredicateNum(int predicateNum) {
		this.predicateNum = predicateNum;
		//predicateNames = new String[predicateNum];
	}
	
	private float randomFloat(float min,float max){
		Random rand = new Random();
		float f = (rand.nextFloat()*(max-min)+min);
		BigDecimal b = new BigDecimal(f);
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	public boolean produceDataTable(){
		
		for(int i = 1;i <= lineNum; i++){
			System.out.print(i+" : ");
			Line line = new Line();
			for(int j = 1;j <= predicateNum; j++){
				Predicate predicate = new Predicate();
				predicate.setPredicateName(predicateNames[j-1]);
				//System.out.println();
				predicate.setLowlimit(randomFloat(Constant_Limit.LOW_LIMIT,Constant_Limit.HIGH_LIMIT));
				predicate.setHighlimit(randomFloat(predicate.getLowlimit(),Constant_Limit.HIGH_LIMIT));
				line.getRow().add(predicate);
				
				System.out.print(predicate.getLowlimit()+"<="+predicate.getPredicateName()+"<="+predicate.getHighlimit()+" ");
			}
			System.out.println();
			this.table.put(i,line.getRow());
		}
		return true;
	}
	
	public static ProduceData getData(){
		String[] predicateNames = {
				"a1","a2","a3","a4","a5"	
		};
		ProduceData data = new ProduceData();
		data.setPredicateNames(predicateNames);
		data.setLineNum(1000);
		data.setPredicateNum(predicateNames.length);
		data.produceDataTable();
		
		return data;
	}
	
	public static void main(String[] args) {
		ProduceData.getData();
	}
}
