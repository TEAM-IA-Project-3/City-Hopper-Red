import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.zip.DataFormatException;

public class RouteReader implements RouteReaderInterface {

	ArrayList<String> cities;
	ArrayList<RouteInterface> routes;
	
	public RouteReader(FileReader inputFileReader) throws IOException, DataFormatException {
		HashSet<String> cities = new HashSet<String>();
		HashSet<RouteInterface> routes = new HashSet<RouteInterface>();

		BufferedReader scanner = new BufferedReader(inputFileReader);

		String headerLine = scanner.readLine();
		int columns = headerLine.split(",").length;
		String line = scanner.readLine();

		while (line != null) {

			String[] currInfo = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

			if (currInfo.length != columns) {
				throw new DataFormatException("Number of columns does not match header line.");
			}

			String city1 = currInfo[0];
			String city2 = currInfo[1];
			Integer distance = Integer.parseInt(currInfo[3]);
			
			Route route = null;
			
			//create the same route object for every pair, regardless of order
			if(city1.compareTo(city2) < 0) {
				route = new Route(city1, city2, distance);
			} else if(city1.compareTo(city2) > 0) {
				route = new Route(city2, city1, distance);
			}
			
			//if no duplicates, add city
			if (cities.add(city1)) this.cities.add(city1);
			
			//if no duplicates, add route
			if (route != null && routes.add(route)) this.routes.add(route);

			line = scanner.readLine();
		}
	}
	
	@Override
	public List<String> getCities() {
		return cities;
	}

	@Override
	public List<RouteInterface> getRoutes() {
		return routes;
	}

}
