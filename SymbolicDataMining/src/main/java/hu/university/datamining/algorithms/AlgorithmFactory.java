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
 *  A, B and C are items. Columns are transactions. Frequent patterns are identified by their transactions.
 *  1 means that a given items is in a transaction. 0 means that the item is not present.
 *  Support number is a number which indicates that how many times the item is present in a transaction. 		
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
	
	private AlgorithmFactory instance;
	
	private AlgorithmFactory(){}
	
	/**
	 * It gives a new instance if it is necessary. Otherwise the same instance is provided.
	 * 
	 * @return instance of AlgorithmFactory
	 */
	public AlgorithmFactory getInstance(){
		synchronized(instance){
			if(instance==null){
				this.instance = new AlgorithmFactory();
				return this.instance;
			}
			else {
				return this.instance;
			}
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
