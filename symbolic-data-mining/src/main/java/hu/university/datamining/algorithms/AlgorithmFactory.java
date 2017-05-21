package hu.university.datamining.algorithms;

/**
 * Factory of symbolic data mining algorithms.
 * 
 * Symbolic data mining is for extracting frequent patterns from data sets.
 * 
 * Example,
 * 		| A | B | C |
 * 		| 1 | 0 | 1 |
 * 		| 1 | 1 | 0 |
 * 		| 0 | 0 | 1 |
 * 
 *  A, B and C are items. Rows are transactions. Frequent patterns are identified by their transactions.
 *  1 means that a given item is in a transaction. 0 means that the item is not present.
 *  Support number is a number which indicates that how many times the item is present in a transaction. 
 *  Support number of A is 2, for example.		
 * 
 * @author NPapp7
 */
public final class AlgorithmFactory {

	/**
	 * Predefined enum values for available algorithms.
	 */
	public enum Algorithms {
		Apriori,
		Eclat,
		Charm,
	}
	
	private static AlgorithmFactory instance;
	
	private AlgorithmFactory(){}
	
	/**
	 * It gives a new instance if it is necessary. Otherwise the same instance is provided.
	 * 
	 * @return instance of AlgorithmFactory
	 */
	public static AlgorithmFactory getInstance(){
			if(instance==null){
				instance = new AlgorithmFactory();
				return instance;
			}
			else {
				return instance;
			}
	}
	
	/**
	 * It can generate a new symbolic data mining algorithm by the predefined enum values.
	 * 
	 * @param algorithm <i>enum</i>
	 * @return new instance of the given algorithm
	 */
	public Algorithm getAlgorithm(Algorithms algorithm){
		switch(algorithm){
			case Apriori :
				Algorithm apriori = new Apriori();
				return apriori;
			case Eclat :
				Algorithm eclat = new Eclat();
				return eclat;
			case Charm :
				Algorithm charm = new Charm();
				return charm;
			default:
				return null;
		}
	}
	
	
}
