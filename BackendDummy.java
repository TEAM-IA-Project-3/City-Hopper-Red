import java.util.ArrayList;
import java.util.List;

/**
 * A dummy class for the backend that returns random results
 * so that I am able to test my implemented Frontend before
 * receiving the Backend from team member
 */
public class BackendDummy implements BackendInterface {

    @Override
    public List<String> getCities(String cityName) {
        List<String> cities = new ArrayList<>();
        
        cities.add("Atlanta");
        cities.add("Madison");
        cities.add("Chicago");
        cities.add("New York");
        cities.add("San Francisco");

        return cities;
        
    }

    @Override
    public List<String> getCitiesWithinDistance(String cityName, int distance) {
        List<String> cities = new ArrayList<>();
        
        cities.add("Ann Arbor");
        cities.add("Madison");
        cities.add("Fresno");
        cities.add("Mumbai");
        cities.add("London");

        return cities;
    }

    @Override
    public String getShortestPath(String city1, String city2) {
        return "Atlanta -> Chicago -> Madison";
    }

    @Override
    public String getFurthestCity(String cityName) {
        return "Chicago";
    }

    @Override
    public List<RouteInterface> getPath(List<String> waypoints) {
        List<RouteInterface> routes = new ArrayList<>();

        RouteInterface route1 = new RouteInterface() {

            @Override
            public String[] getEndpoints() {
                return new String[] {"Atlanta", "Chicago"};
            }

            @Override
            public int getLength() {
            return 6;
            }
            
        };

        RouteInterface route2 = new RouteInterface(){

            @Override
            public String[] getEndpoints() {
                return new String[] {"Chicago", "Madison"};
            }

            @Override
            public int getLength() {
                return 2;
            }
            
        };

        routes.add(route1);
        routes.add(route2);

        return routes;
    }
    
}
