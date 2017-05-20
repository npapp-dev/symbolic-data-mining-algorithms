/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.university.datamining.algorithms.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hu.university.datamining.algorithms.Algorithm;
import hu.university.datamining.algorithms.Eclat;
import hu.university.datamining.algorithms.Node;

/**
 *
 * @author nor
 */
public class EclatTest {
	private static Algorithm eclat;
	private List<Node> resultList;
	private List<Node> resultList2;
	private List<Node> resultList3;

	public EclatTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		resultList = new LinkedList<Node>();
		resultList.add(new Node(Arrays.asList("A"), 4));
		resultList.add(new Node(Arrays.asList("B"), 3));
		resultList.add(new Node(Arrays.asList("C"), 3));
		resultList.add(new Node(Arrays.asList("E"), 3));
		resultList.add(new Node(Arrays.asList("F"), 4));
		resultList.add(new Node(Arrays.asList("A", "C"), 3));
		resultList.add(new Node(Arrays.asList("A", "E"), 3));
		resultList.add(new Node(Arrays.asList("A", "F"), 3));
		resultList.add(new Node(Arrays.asList("B", "F"), 3));
		resultList.add(new Node(Arrays.asList("C", "F"), 3));
		resultList.add(new Node(Arrays.asList("A", "C", "F"), 3));

		resultList2 = new LinkedList<Node>();
		resultList2.add(new Node(Arrays.asList("A"), 4));
		resultList2.add(new Node(Arrays.asList("B"), 5));
		resultList2.add(new Node(Arrays.asList("E"), 5));
		resultList2.add(new Node(Arrays.asList("F"), 4));
		resultList2.add(new Node(Arrays.asList("B", "E"), 4));
		resultList2.add(new Node(Arrays.asList("B", "F"), 4));
		resultList2.add(new Node(Arrays.asList("E", "F"), 4));
		resultList2.add(new Node(Arrays.asList("B", "E", "F"), 4));

		resultList3 = new LinkedList<Node>();
		resultList3.add(new Node(Arrays.asList("Apple"), 3));
		resultList3.add(new Node(Arrays.asList("Orange"), 4));
		resultList3.add(new Node(Arrays.asList("Peach"), 4));
		resultList3.add(new Node(Arrays.asList("Orange", "Peach"), 3));
	}

	@After
	public void tearDown() {
	}

	@Test
	public void test() {
		List<Node> result = (LinkedList<Node>) new Eclat().execute("src/test/resources/test.txt", ",", ",", 3);
		assertTrue(resultList.containsAll(result) && resultList.size()==result.size());

		List<Node> result2 = (LinkedList<Node>) new Eclat().execute("src/test/resources/test2.txt", "|", ",", 4);
		assertTrue(resultList2.containsAll(result2) && resultList2.size()==result2.size());

		List<Node> result3 = (LinkedList<Node>) new Eclat().execute("src/test/resources/test3.txt", "|", ",", 3);
		assertTrue(resultList3.containsAll(result3) && resultList3.size()==result3.size());
	}
}