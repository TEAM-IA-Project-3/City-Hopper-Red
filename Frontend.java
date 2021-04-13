// --== CS400 File Header Information ==--
// Name: Nadim Isaac
// Email: nisaac2@wisc.edu
// Team: IA Red
// Role: Frontend Developer
// TA: Sid Mohan
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Frontend {

    private String csv; //Path to routes data csv
    private Scanner scanner;
    private BackendInterface back;
    
    public Frontend() {
        System.out.println("Starting Team IA City Hopper");

        this.scanner = new Scanner(System.in);

        setCSV();

        back = new Backend(csv);

    }

    private void shortestPathCommand() {
        Scanner input = this.scanner;

        String departingCity = "";
        String arrivalCity = "";

        System.out.println("\nPlease enter the departing city");
        departingCity = input.nextLine();

        System.out.println("\nPlease enter the arrival city");
        arrivalCity = input.nextLine();

        if (back.getShortestPath(departingCity, arrivalCity) == null) {
            System.out.println("\nNo such path exists between these two cities!");
        }

        else {
            System.out.println(); //console output spacing
            System.out.println(back.getShortestPath(departingCity, arrivalCity));
        }
    }

    private void getCitiesDistanceCommand() {
        Scanner input = this.scanner;

        String city = "";
        int distance = 0;

        System.out.println("\nPlease enter the desired city");
        city = input.nextLine();

        boolean validDistance = false;

        do {
            try {

            System.out.println("\nPlease enter the desired distance (positive integer) within the city");
            distance = Integer.parseInt(input.nextLine());

            if(distance >= 0) {
            validDistance = true;
            }

            } catch (NumberFormatException nfe) {
                //valid distance boolean value remains false
            }
        } while(validDistance == false);

        if(back.getCitiesWithinDistance(city, distance).isEmpty()) {
            System.out.println("\nThere are no cities within the specified distance from the desired city!");
        }

        else {      
        System.out.println(); // provides spacing for output in console

        for(String location: back.getCitiesWithinDistance(city, distance)) {
            System.out.println(location);
        }

        }
    }

    private void getPathCommand() {
        Scanner input = this.scanner;
        List<String> stops = new ArrayList<>();

        System.out.println("\nEnter cities that will act as waypoints to visit on a path. When finished enter 'x'.");

        String entered = input.nextLine();

        while(!entered.equals("x")) {
            stops.add(entered);
            entered = input.nextLine();
        }

        if(back.getPath(stops).isEmpty()) {
            System.out.println("\nNo such path exists through these waypoint cities!");
        }

        else {
            System.out.println(); // spacing for console output

            for(RouteInterface route: back.getPath(stops)) {
                System.out.println(route.getEndpoints()[0]
                + " to " + route.getEndpoints()[1] + 
                " (" + route.getLength() + ")");  
            }
        }

    }

    private void directConnectionsCommand() {
        Scanner input = this.scanner;

        String city = "";

        System.out.println("\nPlease enter the desired city to find direct connections from");

        city = input.nextLine();

        if(back.getCities(city).isEmpty()) {
            System.out.println("\nThere are no direct connections from the desired city!");
        }

        else {
            System.out.println(); //console output spacing

            for(String location: back.getCities(city)) {
                System.out.println(location);
            }
        }
    }

    private void furthestCityCommand() {
        Scanner input = this.scanner;

        String city = "";

        System.out.println("\nPlease enter the desired city to find the furthest city from it");

        city = input.nextLine();

        if(back.getFurthestCity(city) == null) {
            System.out.println("\nNo such city exists that is reachable from the desired city!");
        }

        else {
            System.out.println(); //console output spacing
            System.out.println(back.getFurthestCity(city));
        }
    }

    public void run() {
		char instructionKey;
		Scanner input = this.scanner;
		System.out.println("\nHere are some City Hopper Commands:\n");

		loop: while (true) {

			instructionsPrint();
			instructionKey = input.nextLine().charAt(0);

			switch (instructionKey) { // Switches cases depending on the user input
				case 's':
					shortestPathCommand();
					break;
				case 'c':
					getCitiesDistanceCommand();
					break;
				case 'p':
					getPathCommand();
					break;
                case 'd':
                    directConnectionsCommand();
                    break;
                case 'f':
                    furthestCityCommand();
                    break;
				case 'o':
					break loop;
				default:
					System.out.println("That was not the right command. Here are the allowed commands for this car catolog.");
			}
        }
    }

/**
	 * prints all commands user can use
	 */
	public void instructionsPrint() {
		System.out.println("COMMANDS:");
		System.out.println("s -> get shortest path between two cities");
		System.out.println("c -> list all cities within a distance");
		System.out.println("p -> get path between cities given set in order layover cities");
		System.out.println("d -> list all direct connections to a city");
        System.out.println("f -> displays the city that is furthest from given city");
        System.out.println("o -> exits the program");
	}

/**
   * Constructor helper method that takes user input for file path
   * and assigns it to the csv private instance variable.
   */
  private void setCSV() {
    String userPath = "";
    int iter = 0;
    Scanner pathScanner = this.scanner;

    do {

      if(iter > 0) {
          System.out.println("\nFile not found at specified path, ");
        }

      System.out.println("Please type the file path for the routes data csv file:");

      userPath = pathScanner.nextLine();
      iter++;

    } while(!validFileCSV(userPath));

    this.csv = userPath;
  }

/**
   * Validates whether the file at the provided path exists.
   * @param userPath Path to file
   * @return true if file exists, false otherwise
   */
  private boolean validFileCSV(String userPath) {
    File routes = new File(userPath);
    if(routes.exists()) {
      return true;
    }
    return false;
  }

/**
	 * 
	 * @param Takes a scanner as an input
	 * @return returns the first character entered by the user
	 */
	public char getInputChar(Scanner input) {
		char result = input.nextLine().trim().toLowerCase().charAt(0);
		return result;
	}

    public static void main(String[] args) {
        Frontend front = new Frontend();
	    front.run();
    }
}
