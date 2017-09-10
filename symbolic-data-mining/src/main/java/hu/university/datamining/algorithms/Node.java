/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.university.datamining.algorithms;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

/**
 *
 * @author nor
 */
public class Node {

		private static Logger logger = Logger.getLogger(Node.class.getName());
		/**
		 * Name contains list of items.
		 */
        protected List<String> name = new LinkedList<String>();
        /**
         * Support is an indication of how frequently the itemset appears in the dataset.
         */
        protected int supportCount;
       
        /**
         *Confidence is an indication of how often the rule has been found to be true
         */
        private float confidence;
        
        /**
         * List of transaction in which the itemset appears.
         */
        protected Vector<Boolean> ba = new Vector<Boolean>();
        
        String rule="";

        public Node(List<String> name, int supportCount, Vector<Boolean> ba) {
            this.name = name;
            this.supportCount = supportCount;
            this.ba = ba;
        }
        
        public Node(List<String> name, Vector<Boolean> ba){
        	this.name=name;
        	this.ba=ba;
        }
        
        public Node(List<String> name){
        	this.name=name;
        }
        
        public Node(List<String> name, int supportCount){
        	this.name=name;
        	this.supportCount=supportCount;
        }

        Node() {
        }

        @Override
        public boolean equals(Object o){
            if(o==null)
                return false;
            if(!(o instanceof Node))
                return false;
            Node n=(Node)o;
            //System.out.println(n.name+" "+this.name);
            Collections.sort(n.name);
            Collections.sort(this.name);
            if(!(n.name.equals(this.name)))
                return false;
            return true;        
        }

        public List<String> getName() {
            return name;
        }

        @Override
		public String toString() {
			return "Node [name=" + name + ", supportCount=" + supportCount + ", ba=" + ba + "]";
		}

		public int getSupportCount() {
			return supportCount;
		}

		public void setSupportCount(int supportCount) {
			this.supportCount = supportCount;
		}

		public Vector<Boolean> getBa() {
			return ba;
		}

		public void setBa(Vector<Boolean> ba) {
			this.ba = ba;
		}

		public void setName(LinkedList<String> name) {
			this.name = name;
		}

		public float getConfidence() {
			return confidence;
		}
		
		public float getConfidence(int precision){
			try{
			if(confidence!=0)
				return new BigDecimal(confidence).setScale(precision,BigDecimal.ROUND_HALF_EVEN).floatValue();
			return 0;
			}catch(NumberFormatException nfe){
				logger.info(nfe.getMessage()+": "+confidence);
				return 0;
			}
		}
		
		public int getCofidenceInPercent(){
			return (int)(getConfidence(2)*100);
		}

		public void setConfidence(float confidence) {
			this.confidence = confidence;
		}

		public String getRule() {
			return rule;
		}

		public void setRule(String rule) {
			this.rule = rule;
		}
    }
