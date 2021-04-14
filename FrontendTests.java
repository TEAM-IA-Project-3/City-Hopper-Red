// --== CS400 File Header Information ==--
// Name: Nadim Isaac
// Email: nisaac2@wisc.edu
// Team: IA
// TA: Sid Mohan
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Tests the implementation of Frontend for Project Three: the implementation of 
 * City Hopper, through a dummy backend implemented by me, included in this project. 
 */

public class FrontendTests {

    @Test
    /**
     * This first test enters our program and immediately gives input "o"
     * to exit the program. We do this to check if our frontend has been instantiated properly.
     * We also check this through an assertion to make sure frontend is not null.
     */
    public void testFrontEnd1() {
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            // set the input stream to our input (with an o to exit program after a successful frontend instantiation)
            String input = "o";
            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            // set the output to the stream captor to read the output of the front end
            System.setOut(new PrintStream(outputStreamCaptor));
            
            // instantiate front end using constructor created for testing purposes
            //where csv file can be passed in to constructor

            Frontend front = new Frontend("routes.csv");
            front.run();

            // set the output back to standard out for running the test
            System.setOut(standardOut);
            // same for standard in
            System.setIn(standardIn);
            // add all tests to this method
            String appOutput = outputStreamCaptor.toString();

            assertTrue(appOutput.contains("Commands"));

            assertNotNull(front);

        } catch (Exception e) {
            // make sure stdin and stdout are set correctly after we get exception in test
            System.setOut(standardOut);
            System.setIn(standardIn);
            e.printStackTrace();
            // test failed
            fail("Exception thrown.");
        }
    }


    @Test
    /**
     * This second test enters our program and attempts to find the shortest path between two cities
     * and displays that path on the console. This test ensures through an assertion that the user commands
     * run the correct function in the backend (dummy backend for these tests). 
     */
    public void testFrontEnd2() {
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            // set the input stream to our input (d to find firect connections, then from desired city, then o to exit program)
            String input = "d" + System.lineSeparator() + "Chicago" + System.lineSeparator() + "o";
            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            // set the output to the stream captor to read the output of the front end
            System.setOut(new PrintStream(outputStreamCaptor));

            // instantiate front end using constructor created for testing purposes
            //where csv file can be passed in to constructor

            Frontend front = new Frontend("routes.csv");
            front.run();


            // set the output back to standard out for running the test
            System.setOut(standardOut);
            // same for standard in
            System.setIn(standardIn);
            // add all tests to this method
            String appOutput = outputStreamCaptor.toString();

            assertTrue(appOutput.contains("Atlanta\nMadison"));

        } catch (Exception e) {
            // make sure stdin and stdout are set correctly after we get exception in test
            System.setOut(standardOut);
            System.setIn(standardIn);
            e.printStackTrace();
            // test failed
            fail("Exception thrown.");
        }
    }

    
    @Test
    /**
     * This third test launches our program and tries to find direct connections to a user-specified city. 
     * The simulated user input is meant to display all directly connected cities to the user-specified city.
     * This test method uses an assertion to ensure that the appropriate method from the dummy backend is called and 
     * its formatted results are printed to the console.
     */
    public void testFrontEnd3() {
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        try {
            // set the input stream to our input (with an s to find shortest path, followed by city1, then city2 (cities to travel from and to), 
            //and then o to exit the program

            String input = "s" + System.lineSeparator() + "Atlanta" + System.lineSeparator() + "Madison"
            + System.lineSeparator() + "o";
            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            // set the output to the stream captor to read the output of the front end
            System.setOut(new PrintStream(outputStreamCaptor));
            
            // instantiate front end using constructor created for testing purposes
            //where csv file can be passed in to constructor

            Frontend front = new Frontend("routes.csv");
            front.run();

            // set the output back to standard out for running the test
            System.setOut(standardOut);
            // same for standard in
            System.setIn(standardIn);
            // add all tests to this method
            String appOutput = outputStreamCaptor.toString();

            assertTrue(appOutput.contains("Atlanta -> Chicago -> Madison"));

        } catch (Exception e) {
            // make sure stdin and stdout are set correctly after we get exception in test
            System.setOut(standardOut);
            System.setIn(standardIn);
            e.printStackTrace();
            // test failed
            fail("Exception thrown.");
        }
    }

}





