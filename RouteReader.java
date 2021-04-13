// --== CS400 File Header Information ==--
// Name: Atreyo Chakrabarty
// Email: achakrabarty@wisc.edu
// Team: IA
// TA: Sid
// Lecturer: Gary

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.zip.DataFormatException;

public class RouteReader implements RouteReaderInterface {

	private ArrayList<String> cities;
	private ArrayList<RouteInterface> routes;
	
	public RouteReader(Reader inputFileReader) throws IOException, DataFormatException {
		this.cities = new ArrayList<String>();
		this.routes = new ArrayList<RouteInterface>();
		HashSet<String> cities = new HashSet<String>();

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
			Integer distance = Integer.parseInt(currInfo[2]);
			
			Route route = null;
			
			//create the same route object for every pair, regardless of order
			if(city1.compareTo(city2) < 0) {
				route = new Route(city1, city2, distance);
			} else if(city1.compareTo(city2) > 0) {
				route = new Route(city2, city1, distance);
			}
			
			//if no duplicates, add city
			if (cities.add(city1)) {this.cities.add(city1);}
			if (cities.add(city2)) {this.cities.add(city2);}
			
			//if no duplicates, add route --> all routes are bidirectional
			if (route != null && !routes.contains(route)) this.routes.add(route);

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
