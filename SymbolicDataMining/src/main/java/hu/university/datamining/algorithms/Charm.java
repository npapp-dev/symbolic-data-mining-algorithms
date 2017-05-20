/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.university.datamining.algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Logger;

/**
 *
 * @author nor
 */
public class Charm extends Algorithm {

    private static Logger logger = Logger.getAnonymousLogger();

    private int minSupport;
    private MyHashMap hashItNodes;
    private int numberOfExtractedItems;

    //Map<String, Integer> result = new HashMap<>();
    private List<ItNode> result = new LinkedList<ItNode>();

    private static class ListOfNodes {

        private static class ClosedItNode {

            List<String> name = new LinkedList<String>();
            int support;
            ClosedItNode next;

            ClosedItNode(List<String> name, int support) {
                this.name = name;
                this.support = support;
                this.next = null;
            }
        }

        private ClosedItNode head;
        private int size = 0;

        void addNode(ItNode node) {
            if (size == 0) {
                head = new ClosedItNode(node.name, node.supportCount);
                size++;
                return;
            }
            ClosedItNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ClosedItNode(node.name, node.supportCount);
            size++;
        }

        boolean contains(ItNode node) {
            if (head == null) {
                return false;
            }
            ClosedItNode current = head;
            while (current != null) {
                if (current.support == node.supportCount) {
                    Collections.sort(current.name);
                    Collections.sort(node.name);
                    if (current.name.equals(node.name) || containsItemsets(current.name, node.name)) {
                        return true;
                    }
                }
                current = current.next;
            }
            return false;
        }
    }

    static boolean containsItemsets(List<String> name, List<String> name2) {
        int itContains = 0;
        for (String n2 : name2) {
            for (String n : name) {
                if (n2.equals(n)) {
                    itContains++;
                }
            }
        }
        if (itContains >= name2.size()) {
            return true;
        }
        return false;
    }

    private class MyHashMap {

        int sor;
        ListOfNodes[] list;

        MyHashMap(int sor) {
            this.sor = sor;
            this.list = new ListOfNodes[sor + 1];
        }

        boolean addElement(ItNode itnode) {
            int igazak = 0;
            int tidsetOsszeg = 0;
            for (int i = 0; i < itnode.ba.size(); i++) {
                if (itnode.ba.get(i)) {
                    tidsetOsszeg += i;
                    igazak++;
                }
            }
            int kulcs = tidsetOsszeg % sor;

            if (list[kulcs] == null) {
                list[kulcs] = new ListOfNodes();
                list[kulcs].addNode(itnode);
                return true;
            } else if (!list[kulcs].contains(itnode)) {
                list[kulcs].addNode(itnode);
                return true;
            }
            return false;
        }
    }


    static void replaceInSubTree(ItNode curr, ItNode other) {
        ItNode newItNode = new ItNode();
        for (String s : curr.name) {
            newItNode.name.add(s);
        }
        for (int l = 0; l < other.name.size(); l++) {
            boolean benneVanE = false;;
            for (int k = 0; k < newItNode.name.size(); k++) {
                if (newItNode.name.get(k).equals(other.name.get(l))) {
                    benneVanE = true;
                    break;
                }
            }
            if (!benneVanE) {
                newItNode.name.add(other.name.get(l));
                break;
            }
        }

        curr.name = new LinkedList<String>();
        for (String s : newItNode.name) {
            curr.name.add(s);
        }
        for (ItNode itnode : curr.children) {
            replaceInSubTree(itnode, other);
        }

    }

    void deleteItNode(ItNode itnode, List<ItNode> itnodes) {
        int index = 0;
        //Original D code foreach(i,node;itnodes)
        for (int i = 0; i < itnodes.size(); i++) {
            if (itnodes.get(i).name.equals(itnode.name)) {
                index = i;
                break;
            }
        }
        itnodes.remove(index);
    }



    void save(ItNode curr) {
        if (hashItNodes.addElement(curr)) {
        	result.add(curr);
            numberOfExtractedItems++;
        }
    }

    public Charm() {
    }

    void extend(ItNode curr) {
        for (int i = 0; i < curr.parent.children.size(); i++) {
            ItNode other = curr.parent.children.get(i);
            if (containsItemsets(curr.name, other.name)) {
                continue;
            }
            if (curr.ba.equals(other.ba)) {
                replaceInSubTree(curr, other);
                deleteItNode(other, curr.parent.children);
                i--;
            } else if (isSubList(curr.ba, other.ba)) {
                replaceInSubTree(curr, other);
            } else if (isSubList(other.ba, curr.ba)) {
                ItNode candidate = getCandidate(curr, other);
                deleteItNode(other, curr.parent.children);
                i--;

                if(candidate!=null){
                	curr.addChild(candidate);
                }
            } else {
                ItNode candidate = getCandidate(curr, other);
                //New candidate was found.
                if(candidate!=null){
                	curr.addChild(candidate);
                }
            }
        }

        while (curr.children.size() > 0) {
            ItNode child = curr.getFirstChild();
            if (child.name.get(0).equals("N")) {
                break;
            }
            extend(child);
            save(child);
            //       delete(child);
        }
    }

     boolean isSubList(List<Boolean> list1, List<Boolean> list2) {
        int igazak = 0;
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i)) {
                igazak++;
            }
        }
        int talalt = 0;
        for (int i = 0; i < list2.size(); i++) {
            if (list1.get(i) && list2.get(i)) {
                talalt++;
            }
        }
        if (talalt == igazak) {
            return true;
        }
        return false;
    }

    private ItNode getCandidate(ItNode curr, ItNode other) {
        ItNode newItNode = new ItNode();
        for (String s : curr.name) {
            newItNode.name.add(s);
        }
        //newItNode.name=curr.name.dup;
        for (int l = 0; l < other.name.size(); l++) {
            boolean contains = false;
            for (int k = 0; k < newItNode.name.size(); k++) {
                if (newItNode.name.get(k).equals(other.name.get(l))) {
                	contains = true;
                    break;
                }
            }
            if (!contains) {
                newItNode.name.add(other.name.get(l));
                newItNode.ba = new Vector<Boolean>();
                for (int i = 0; i < curr.ba.size(); i++) {
                    if (curr.ba.get(i).equals(other.ba.get(i)) && curr.ba.get(i).equals(true) && other.ba.get(i).equals(true)) {
                        newItNode.ba.add(true);
                    } else {
                        newItNode.ba.add(false);
                    }
                }
                break;
            }
        }
        newItNode = this.supportCount(newItNode);
        if (newItNode.getSupportCount() >= minSupport) {
            return newItNode;
        } else {
            return null;
        }
    }

    @Override
    public List<Node> execute(String fileName, String columnDelimiter, String dataDelimiter, int minSupport) {
        this.minSupport = minSupport;
        List<ItNode> itnodes = new LinkedList<>();
        BufferedReader br = null;
        try {
            File f = new File(fileName);
            br = new BufferedReader(new FileReader(f));

            String sCurrentLine = br.readLine();
            StringTokenizer st = new StringTokenizer(sCurrentLine, columnDelimiter);
            while (st.hasMoreElements()) {
                ItNode in = new ItNode();
                in.name.add(st.nextToken());
                itnodes.add(in);
            }
            int sorok = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                StringTokenizer st2 = new StringTokenizer(sCurrentLine, dataDelimiter);
                sorok++;
                for (ItNode in : itnodes) {
                    int szam = Integer.valueOf(st2.nextToken()).intValue();
                    if (szam == 1) {
                        in.ba.add(true);
                    } else {
                        in.ba.add(false);
                    }
                }
            }
            this.hashItNodes = new MyHashMap(sorok);
            ItNode root = new ItNode();
            super.supportCount(itnodes);
            for (ItNode itnode : itnodes) {
                if (itnode.supportCount >= minSupport) {
                    root.addChild(itnode);
                }
            }

            while (root.children.size() > 0) {
                ItNode child = root.getFirstChild();
                if (child.name.get(0).equals("N")) {
                    break;
                }
                extend(child);
                save(child);
                // delete child from nodes
                itnodes.remove(child);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (List<Node>)(List<?>)result;
    }
    
	   private ItNode supportCount(ItNode node) {
	        int mySupport = 0;
	        for (int i = 0; i < node.getBa().size(); i++) {
	            if (node.getBa().get(i).equals(true)) {
	                mySupport++;
	            }
	        }
	        node.setSupportCount(mySupport);
	        return node;
	    }

	public int getNumberOfExtractedItems() {
		return numberOfExtractedItems;
	}

	public void setNumberOfExtractedItems(int numberOfExtractedItems) {
		this.numberOfExtractedItems = numberOfExtractedItems;
	}
}
