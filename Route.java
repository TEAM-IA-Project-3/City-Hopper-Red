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
	
	public Route(String city1, String city2, int length) {
		endpoints = new String[] {city1, city2};
		this.length = length;
	}
	
	@Override
	public String[] getEndpoints() {
		return endpoints;
	}

	@Override
	public int getLength() {
		return length;
	}
	
	/**
	 * 
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
