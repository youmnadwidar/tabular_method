package models;

import java.util.LinkedList;

public interface IMinimizer {
	/**
	 * Minimize a set of minterms to the minimum SOP of a circuit
	 * @param minterms array of integers representing the minterms of a logical circuit ro reduce
	 * @return a linkedList containing the reduced minterms
	 */
	public LinkedList<MintermReduced> minimize(int[] minterms);

}
