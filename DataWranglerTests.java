// --== CS400 File Header Information ==--
// Name: Atreyo Chakrabarty
// Email: achakrabarty@wisc.edu
// Team: IA
// TA: Sid
// Lecturer: Gary

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.StringReader;
import java.util.zip.DataFormatException;

public class DataWranglerTests {

	private RouteReader routeReader;
	
	@BeforeEach
	public void createReader() {
		try {
			routeReader = new RouteReader(new StringReader(
					"city1, city2, length\n"+
					"A,B,2\n"+
					"A,C,6\n"+
					"C,A,6\n"+
					"A,D,5\n"+
					"B,D,4\n"+
					"C,D,4"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetCities() {
		assertEquals(routeReader.getCities().toString(), "[A, B, C, D]");
	}
	
	@Test
	public void testGetRoutes() {
		assertEquals(routeReader.getRoutes().toString(), "[A<->B:2, A<->C:6, A<->D:5, B<->D:4, C<->D:4]");
	}
	
	@Test
	public void testGetConnectedVertices() {
		CS400Graph<String> graph = new CS400Graph<String>();
		//insert data into graph
		for(String s:routeReader.getCities()) {
			graph.insertVertex(s);
		}
		for(RouteInterface r:routeReader.getRoutes()) {
			graph.insertEdge(r.getEndpoints()[0], r.getEndpoints()[1], r.getLength());
			graph.insertEdge(r.getEndpoints()[1], r.getEndpoints()[0], r.getLength());
		}
		assertEquals(graph.getConnectedVertices("A").toString(), "[B, C, D]");
	}
	
}
