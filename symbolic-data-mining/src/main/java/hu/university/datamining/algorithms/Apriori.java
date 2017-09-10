/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.university.datamining.algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Logger;

/**
 *
 * @author nor
 */
public final class Apriori extends Algorithm {
    
	private static Logger logger = Logger.getLogger(Apriori.class.getName());
	
    private LinkedList<Node> itNodes = new LinkedList<Node>();
  
    private List<Node> result = new LinkedList<Node>();

    @Override
    public List<Node> execute(String fileName, String columnDelimiter, String dataDelimiter, int minSupport) {
        this.fileName = fileName;
        this.minSupport = minSupport;
        BufferedReader br = null;
        try {
            File f = new File(this.fileName);
            br = new BufferedReader(new FileReader(f));

            String sCurrentLine = br.readLine();
            StringTokenizer st = new StringTokenizer(sCurrentLine, columnDelimiter);
            while (st.hasMoreElements()) {
                Node in = new Node();
                in.getName().add(st.nextToken());
                itNodes.add(in);
            }
            while ((sCurrentLine = br.readLine()) != null) {
                StringTokenizer st2 = new StringTokenizer(sCurrentLine, dataDelimiter);
                for (Node in : this.itNodes) {
                    int binaryValue = Integer.valueOf(st2.nextToken()).intValue();
                    if (binaryValue == 1) {
                        in.getBa().add(true);
                    } else {
                        in.getBa().add(false);
                    }
                }
            }
            while (itNodes.size() > 0) {
                this.count(itNodes);
                LinkedList<Node> frequent = new LinkedList<Node>();
                for (int j = 0; j < itNodes.size(); j++) {
                    if (itNodes.get(j).getSupportCount() >= minSupport) {
                        frequent.add(itNodes.get(j));
                    }
                }
                result.addAll(frequent);
                itNodes = generateFrequentList(frequent);
            }

        } catch (IOException io) {
            System.out.println(io.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

     Apriori() {
    }
    
      Apriori(String fileName, int minSupport) {
        this.fileName = fileName;
        this.minSupport = minSupport;
    }

    /**
     * It creates a list of frequent nodes.It uses the support number in order to select the candidate.
     * 
     * @param nodes from which frequent ones will be selected.
     * @return frequent nodes
     */
    private LinkedList<Node> generateFrequentList(LinkedList <Node> nodes) {
    	LinkedList <Node> newNodes = new LinkedList <>();
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                Node first = nodes.get(i);
                Node second = nodes.get(j);
                Vector<Boolean> firstBa = first.getBa();
                Vector<Boolean> secondBa = second.getBa();
                int support = 0;
                Node candidate = new Node();
                for (int g = 0; g < first.getBa().size(); g++) {
                    if (firstBa.get(g).equals(secondBa.get(g)) && firstBa.get(g) == true) {
                    	support++;
                        candidate.getBa().add(true);
                    } else {
                    	candidate.getBa().add(false);
                    }
                }
                for (String s : first.getName()) {
                    if (!candidate.getName().contains(s)) {
                    	candidate.getName().add(s);
                    }
                }
                for (String s : second.getName()) {
                    if (!candidate.getName().contains(s)) {
                    	candidate.getName().add(s);
                    }
                }
                candidate.setSupportCount(support);
                this.setSupportCountOfNode(first);
    //            logger.info("First support:"+candidate.getSupportCount());
                if (support >= minSupport) {
                      boolean found = false;
                    if (result.size() > 1) {
                            if(result.contains(candidate) || newNodes.contains(candidate))
                            	found=true;
                    }
                    if (found) {
                        break;
                    } else {
                        candidate.setConfidence((float)candidate.getSupportCount()/(float)first.getSupportCount());
                        candidate.setRule(candidate.getName()+" => "+first.getName());
                        newNodes.add(candidate);
                    }
                }
            }
        }
        return newNodes;
    }

}
