/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.university.datamining.algorithms;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author nor
 */
public class Node {

        protected List<String> name = new LinkedList<String>();
        private int supportCount;
        protected Vector<Boolean> ba = new Vector<Boolean>();

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
        
        
    }
