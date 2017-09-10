/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.university.datamining.algorithms;

import hu.university.datamining.algorithms.Node;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

/**
 * Parent class of Symbolic datamining algorithms.
 * It defines common methods and fields.
 * 
 * @author NPapp7
 */
public abstract class Algorithm {
	
	/**
	 * Name of the files from which the data is loaded.
	 */
	protected String fileName;
	
	/**
	 * Minimum support of symbolic datamining.
	 */
	protected int minSupport;
	
	/**
	 *
	 * @param fileName Data file name.
	 * @param columnDelimiter
	 * @param dataDelimiter
	 * @return key value pairs. Key is the frequent itemset. Value is the support count.
	 */
	   public abstract List<Node> execute(String fileName, String columnDelimiter, String dataDelimiter, int minSupport);

	   /**
	    * It sets the supoort of ItNodes.
	    * 
	    * @param itnodes
	    */
	   protected void supportCount(List<ItNode> itnodes) {
	        for (ItNode itnode : itnodes) {
	            int mySupport = 0;
	            for (int i = 0; i < itnode.ba.size(); i++) {
	                if (itnode.ba.get(i).equals(true)) {
	                    mySupport++;
	                }
	            }
	            itnode.supportCount = mySupport;
	        }
	    }
	   
	   protected void setSupportCountOfNode(Node node){
		   int support=0;
		   for(int i=0;i<node.getBa().size();i++){
			   if(node.getBa().get(i).equals(true)){
				   support++;
			   }
		   }
		   node.setSupportCount(support);
	   }
	   
	    protected void count(List<Node> nodes) {
	        int support;
	        for (Node it : nodes) {
	            support = 0;
	            Vector<Boolean> v = it.getBa();
	            for (Boolean b : v) {
	                if (b.booleanValue() == true) {
	                    support++;
	                }
	            }
	            it.setSupportCount(support);
	        }
	    }
}
