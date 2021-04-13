// --== CS400 File Header Information ==--
// Name: Atreyo Chakrabarty
// Email: achakrabarty@wisc.edu
// Team: IA
// TA: Sid
// Lecturer: Gary

import java.util.Arrays;

public class Route implements RouteInterface {

	private String[] endpoints;
	private int length;
	
	/**
	 * Constructs a route object
	 * @param city1 One of the two cities connected
	 * @param city2 One of the two cities connected
	 * @param length distance between cities
	 */
	public Route(String city1, String city2, int length) {
		endpoints = new String[] {city1, city2};
		this.length = length;
	}
	
	/**
	 * Returns endpoints
	 */
	@Override
	public String[] getEndpoints() {
		return endpoints;
	}

	/**
	 * Returns length
	 */
	@Override
	public int getLength() {
		return length;
	}
	
	/**
	 * Modified equals method to aid in checking for duplicates
	 * @return true if endpoints arrays match, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if(Arrays.equals(this.endpoints, ((Route) obj).getEndpoints())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Simple toString methods for testing
	 * @return a formatted string of object data
	 */
	@Override
	public String toString() {
		return endpoints[0]+"<->"+endpoints[1]+":"+length;
	}

}
