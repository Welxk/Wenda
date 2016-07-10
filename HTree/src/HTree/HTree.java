package HTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import siena.makeData.Constant_Limit;
import siena.makeData.Predicate;
import siena.makeData.ProduceData;

public class HTree {
	/**
	 *树的根节点
	 */
	private Node root;
	/**
	 * 树的层数
	 */
	private int level;
	/**
	 * 树中每个节点的取值空间划分数目
	 */
	private int rangeNum;
	/**
	 * 属性名称数组
	 */
	private String[] attributeNames;
	
	public HTree(Node root, int level, int rangeNum,String[] attributeNames) {
		this.root = root;
		this.level = level;
		this.rangeNum = rangeNum;
		this.attributeNames = attributeNames;
	}
	
	public void BuildHTree(){
		root = new Node();
		BuildHTree(root,0);
	}
	
	void BuildHTree(Node curnode,int pos){		//首先创建当前节点，然后将区间放入节点中，将订阅的指定属性放入到指定区间，进入下一层
		if(pos<level-1){
			List<Range> rangeList = new ArrayList<Range>();
			double piece = (Constant_Limit.HIGH_LIMIT-Constant_Limit.LOW_LIMIT)/rangeNum;
			for(int i = 0;i < rangeNum; i++){
				double lo = piece*i;
				double hi = piece*(i+1);
				Range range = new Range(lo,hi);
				range.son = new Node();
				rangeList.add(range);
			}
			Range range = new Range(Constant_Limit.LOW_LIMIT,Constant_Limit.HIGH_LIMIT);
			range.son = new Node();
			rangeList.add(range);
			curnode.setAttriName(attributeNames[pos]);
			curnode.setRanges(rangeList);
			for(Range temprange:rangeList){
				BuildHTree(temprange.son,pos+1);
			}
		}else if(pos==level-1){
			List<Range> rangeList = new ArrayList<Range>();
			double piece = (Constant_Limit.HIGH_LIMIT-Constant_Limit.LOW_LIMIT)/rangeNum;
			for(int i = 0;i < rangeNum; i++){
				double lo = piece*i;
				double hi = piece*(i+1);
				Range range = new Range(lo,hi);
				range.subs = new ArrayList<Subscription>();
				rangeList.add(range);
			}
			Range range = new Range(Constant_Limit.LOW_LIMIT,Constant_Limit.HIGH_LIMIT);
			range.subs = new ArrayList<Subscription>();
			rangeList.add(range);
			curnode.setAttriName(attributeNames[pos]);
			curnode.setRanges(rangeList);
		}
	}
	
	void put(Node cur,Subscription sub,int pos){
		if(pos<level-1){
			boolean flag = false;
			SubAttribute subAttri = sub.getAttributes().get(pos);
			//System.out.println(cur.getRanges().size());
			for(int i = 0;i < cur.getRanges().size()-1; i++)
				if(subAttri.getLolimit()>=cur.getRanges().get(i).getLolimit()&&subAttri.getHilimit()<=cur.getRanges().get(i).getHilimit()){
					put(cur.getRanges().get(i).getSon(),sub,pos+1);
					flag = true;
				}
			
			if(!flag){
				int index = cur.getRanges().size()-1;
				put(cur.getRanges().get(index).getSon(),sub,pos+1);
			}
		}else if(pos==level-1){
			SubAttribute subAttri = sub.getAttributes().get(pos);
			for(int i = 0;i < cur.getRanges().size()-1; i++)
				if(subAttri.getLolimit()>=cur.getRanges().get(i).getLolimit()&&subAttri.getHilimit()<=cur.getRanges().get(i).getHilimit()){
					//put(cur.getRanges().get(i).getSon(),sub,pos+1);
					cur.getRanges().get(i).getSubs().add(sub);
					return ;
				}
			
			int index = cur.getRanges().size()-1;
			cur.getRanges().get(index).getSubs().add(sub);
			return ;
		}
	}
	
	public void put(Subscription sub){
		Node cur = root;
//		if(cur!=null)
//			System.out.println("root size: "+root.getRanges().size());
		put(cur,sub,0);
	}

	public void find(Node cur,Publication pub,List<Subscription> result,int pos){
		if(pos<level-1){
			for(int i = 0;i < cur.getRanges().size(); i++)
				if(cur.getRanges().get(i).getLolimit()<=pub.getAttributes().get(pos).getAttributeValue()&&cur.getRanges().get(i).getHilimit()>=pub.getAttributes().get(pos).getAttributeValue()){
					find(cur.getRanges().get(i).getSon(),pub,result,pos+1);
				}
		}else if(pos==level-1){
			for(int i = 0;i < cur.getRanges().size(); i++)
				if(cur.getRanges().get(i).getLolimit()<=pub.getAttributes().get(pos).getAttributeValue()&&cur.getRanges().get(i).getHilimit()>=pub.getAttributes().get(pos).getAttributeValue()){
					List<Subscription> list = cur.getRanges().get(i).getSubs();
					for(int j = 0;j < list.size(); j++){
						boolean flag = false;
						Subscription listSub = list.get(j);
						for(int k = 0; k < level; k++){
							if(listSub.getAttributes().get(k).getLolimit()>pub.getAttributes().get(k).getAttributeValue()||listSub.getAttributes().get(k).getHilimit()<pub.getAttributes().get(k).getAttributeValue()){
								flag = true;
								break;
							}
						}
						if(!flag)
							result.add(listSub);
					}
				}
		}
	}
	
	public void find(Publication pub,List<Subscription> result){
		if(root!=null){
			Node cur = root;
			find(cur,pub,result,0);
			return ;
		}
		System.out.println("树为空");
	}
	
	public class Range{
		private double lolimit;
		private double hilimit;
		private Node son;
		private List<Subscription> subs;
		public Range(double lolimit, double hilimit) {
			this.lolimit = lolimit;
			this.hilimit = hilimit;
			this.son = son;
			this.subs = null;
		}
		
		public Range(double lolimit, double hilimit, List<Subscription> subs) {
			this.lolimit = lolimit;
			this.hilimit = hilimit;
			this.son = null;
			this.subs = subs;
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
		
		public Node getSon() {
			return son;
		}
		
		public void setSon(Node son) {
			this.son = son;
		}

		public List<Subscription> getSubs() {
			return subs;
		}

		public void setSubs(List<Subscription> subs) {
			this.subs = subs;
		}
	}
	
	private class Node{
		private String attriName;
		private List<Range> ranges;

		public Node() {
		}
		
		public Node(String attriName, List<Range> ranges) {
			this.attriName = attriName;
			this.ranges = ranges;
		}

		public List<Range> getRanges() {
			return ranges;
		}

		public void setRanges(List<Range> ranges) {
			this.ranges = ranges;
		}

		public String getAttriName() {
			return attriName;
		}

		public void setAttriName(String attriName) {
			this.attriName = attriName;
		}
	}
	
	public static void main(String[] args) {
		String[] attributeNames = {
				"a1","a2","a3","a4","a5"	
		};
		HTree tree = new HTree(null,5,4,attributeNames);
		tree.BuildHTree();
		
		ProduceData data = ProduceData.getData();
		for(int i = 1;i <= data.getLineNum(); i++){
			Subscription sub = new Subscription();
			for(int j = 0;j < 5; j++){
				Predicate predicate = data.getTable().get(i).get(j);
				SubAttribute attri = new SubAttribute(predicate.getPredicateName(),predicate.getLowlimit(),predicate.getHighlimit());
				sub.addSubAttribute(attri);
			}
			tree.put(sub);
		}
		System.out.println("Test :　            "+tree.root.getRanges().get(0).getSon().getRanges().get(4).getSon().getRanges().get(4).getSon().getRanges().get(4).getSon().getRanges().get(4).subs.size());

		
		Random rand = new Random();
		for(int i = 0;i <= 100; i++){
			Publication pub = new Publication();
			for(int j = 0;j < attributeNames.length; j++){
				double value = rand.nextDouble()*(Constant_Limit.HIGH_LIMIT-Constant_Limit.LOW_LIMIT)+Constant_Limit.LOW_LIMIT;
				PubAttribute attri = new PubAttribute(attributeNames[j], value);
				pub.add(attri);
			}
			List<Subscription> result = new ArrayList<Subscription>();
			tree.find(pub, result);
			System.out.println("Pub: "+pub);
			System.out.println("result size: "+result.size());
			for(Subscription sub:result)
				System.out.println(sub);
			
			int count = 0;
			for(int j = 1;j < data.getLineNum(); j++){
				boolean flag = true;
				for(int k = 0;k < attributeNames.length; k++)
					if(pub.getAttributes().get(k).getAttributeValue()>data.getTable().get(j).get(k).getHighlimit()||pub.getAttributes().get(k).getAttributeValue()<data.getTable().get(j).get(k).getLowlimit()){
						flag = false;
						break;
					}
				if(flag){
					count++;
					//System.out.println(data.getTable().get(j));
				}
			}
			System.out.println("True size: "+count);
			System.out.println("*************************************");
		}
		
	}
	
}
