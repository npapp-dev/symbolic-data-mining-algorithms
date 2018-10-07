/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dataminer.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author nor
 */
public final class ItNode extends Node{

    List<ItNode> children=new LinkedList<ItNode>();
    ItNode parent;
    
    public ItNode(){
    }
    
    public ItNode(List<String> name, int supportCount){
    	this.name=name;
    	this.supportCount=supportCount;
    }

    void addChild(ItNode attr) {
        if (children == null) {
            children = new LinkedList<ItNode>();
        }
        attr.parent = this;
        children.add(attr);
    }

    ItNode getFirstChild() {
        if (children.size() > 0) {
            //TODO hibalehetőség!!!!!!!!
            ItNode first = children.remove(0);
            List<ItNode> ujItNode;
            ujItNode = children;
            children = ujItNode;
            return first;
        } else {
            ItNode itnode = new ItNode();
            itnode.name.add("N");
            return itnode;
        }
    }

    boolean isSubset(ArrayList<Boolean> currBa, ArrayList<Boolean> otherBa) {
        int igazak = 0;
        for (int i = 0; i < currBa.size(); i++) {

            if (ba.get(i)) {
                igazak++;
            }
        }
        int talalt = 0;
        for (int i = 0; i < otherBa.size(); i++) {
            if (currBa.get(i) && otherBa.get(i)) {
                talalt++;
            }
        }
        if (talalt == igazak) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o){
        if(o==null)
            return false;
        if(!(o instanceof ItNode)){
            return false;
        }
        ItNode node=(ItNode)o;
        if(!node.name.equals(this.name))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return new StringBuilder().append("{" + this.name + "} (" + this.supportCount + ")").toString();
    }
}
