/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.university.datamining.algorithms.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hu.university.datamining.algorithms.AlgorithmFactory;
import hu.university.datamining.algorithms.AlgorithmFactory.Algorithms;
import hu.university.datamining.algorithms.Charm;
import hu.university.datamining.algorithms.ItNode;
import hu.university.datamining.algorithms.Node;

/**
 *
 * @author nor
 */
public class CharmTest {
    private List<Node> resultList;
    private List<Node> resultList2;
    
    public CharmTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        
        resultList=new LinkedList<Node>();
        resultList.add(new ItNode(Arrays.asList("A"),4));
        resultList.add(new ItNode(Arrays.asList("F"),4));
        resultList.add(new ItNode(Arrays.asList("B","F"),3));
        resultList.add(new ItNode(Arrays.asList("A","E"),3));
        resultList.add(new ItNode(Arrays.asList("A","C","F"),3));
        
        resultList2=new LinkedList<Node>();
        resultList2.add(new ItNode(Arrays.asList("A","C","W"),4));
        resultList2.add(new ItNode(Arrays.asList("A","C","T","W"),3));
        resultList2.add(new ItNode(Arrays.asList("C"),6));
        resultList2.add(new ItNode(Arrays.asList("C","D"),4));
        resultList2.add(new ItNode(Arrays.asList("C","D","W"),3));
        resultList2.add(new ItNode(Arrays.asList("C","T"),4));
        resultList2.add(new ItNode(Arrays.asList("C","W"),5));
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void TestCharm() {
        List<Node> result = AlgorithmFactory.getInstance().getAlgorithm(Algorithms.Charm).execute("src/test/resources/test.txt", ",", ",", 3);
        assertTrue(resultList.containsAll(result) && resultList.size()==result.size());

        List<Node> result2 = AlgorithmFactory.getInstance().getAlgorithm(Algorithms.Charm).execute("src/test/resources/test4.txt", "|", " ", 3);
        assertTrue(resultList2.containsAll(result2) && resultList2.size()==result2.size());
        
        List<Node> result3 = AlgorithmFactory.getInstance().getAlgorithm(Algorithms.Charm).execute("src/test/resources/data.txt", " ", ",", 5);
		for(Node node : result3){
       	 System.out.println("Node name:"+node.getName()+" support count:"+node.getSupportCount()+" confidence: "+(int)(node.getConfidence(2)*100)+"%"+" "+node.getRule());
       }
    }
}
