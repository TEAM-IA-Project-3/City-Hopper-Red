
public class Route implements RouteInterface{

	String[] endpoints;
	int length;
	
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

}
