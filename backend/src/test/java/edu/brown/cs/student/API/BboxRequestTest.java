// package edu.brown.cs.student.API;

// import edu.brown.cs.student.main.API.BboxRequest;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.assertFalse;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;


// public class BboxRequestTest {
//     //Tests covered
//     //  1. all points within the bbox -> expect true
//     //      a. one point
//     //      b. many points,
//     //      c. TODO: use random testing to generate coordinates and bbox?
//     //  2. empty list of coordinates -> expect true
//     //  3. points outside of the bbox -> expect false
//     //      a. some points
//     //      b. all points
//     //      c. last point
//     //      d. on edge of bbox
//     //      e. on corner of bbox


//     @Test
//     public void testBboxRequestContainsPoint() throws IOException {
//         //bounding-box filtering
//         BboxRequest bbox = new BboxRequest(41.720464, -71.518092, 41.941462, -71.289694);
//         List<List<Double>> coordinates = new ArrayList<>();
//         List<Double> coordinate = new ArrayList<>();
//         coordinate.add(-71.500001);
//         coordinate.add(41.800012);
//         coordinates.add(coordinate);

//         assertTrue(bbox.contains(coordinates));

//     }

//     @Test
//     public void testBboxRequestContainsSquare() throws IOException {
//         BboxRequest bbox = new BboxRequest(41.720464, -71.518092, 41.941462, -71.289694);
//         List<List<Double>> coordinates = new ArrayList<>();
//         List<Double> coordinate1 = new ArrayList<>();
//         coordinate1.add(-71.500001);
//         coordinate1.add(41.800012);
//         coordinates.add(coordinate1);
//         List<Double> coordinate2 = new ArrayList<>();
//         coordinate2.add(-71.419285);
//         coordinate2.add(41.800012);
//         coordinates.add(coordinate2);
//         List<Double> coordinate3 = new ArrayList<>();
//         coordinate3.add(-71.419285);
//         coordinate3.add(41.758281);
//         coordinates.add(coordinate3);
//         List<Double> coordinate4 = new ArrayList<>();
//         coordinate4.add(-71.500001);
//         coordinate4.add(41.758281);
//         coordinates.add(coordinate4);
//         assertTrue(bbox.contains(coordinates));
//     }

//     @Test
//     public void testBboxRequestRepeatLine() throws IOException {
//         BboxRequest bbox = new BboxRequest(41.720464, -71.518092, 41.941462, -71.289694);
//         List<List<Double>> coordinates = new ArrayList<>();
//         List<Double> coordinate1 = new ArrayList<>();
//         coordinate1.add(-71.500001);
//         coordinate1.add(41.800012);
//         coordinates.add(coordinate1);
//         List<Double> coordinate2 = new ArrayList<>();
//         coordinate2.add(-71.419285);
//         coordinate2.add(41.800012);
//         coordinates.add(coordinate2);
//         //repeat line
//         coordinates.add(coordinate2);
//         coordinates.add(coordinate1);
//         assertTrue(bbox.contains(coordinates));
//     }

//     @Test
//     public void testBboxRequestEmptyCoords() {
//         BboxRequest bbox = new BboxRequest(41.720464, -71.518092, 41.941462, -71.289694);
//         List<List<Double>> coordinates = new ArrayList<>();
//         assertTrue(bbox.contains(coordinates));
//     }


//     @Test
//     public void testBboxRequestNotContains1MinLon() {
//         BboxRequest bbox = new BboxRequest(41.720464, -71.518092, 41.941462, -71.289694);
//         List<List<Double>> coordinates = new ArrayList<>();
//         List<Double> coordinate = new ArrayList<>();
//         coordinate.add(-74.500001);
//         coordinate.add(41.800012);
//         coordinates.add(coordinate);
//         assertFalse(bbox.contains(coordinates));
//     }
//     @Test
//     public void testBboxRequestNotContains1MinLat() {
//         BboxRequest bbox = new BboxRequest(41.720464, -71.518092, 41.941462, -71.289694);
//         List<List<Double>> coordinates = new ArrayList<>();
//         List<Double> coordinate = new ArrayList<>();
//         coordinate.add(-71.500001);
//         coordinate.add(40.800012);
//         coordinates.add(coordinate);
//         assertFalse(bbox.contains(coordinates));
//     }
//     @Test
//     public void testBboxRequestNotContains1MaxLon() {
//         BboxRequest bbox = new BboxRequest(41.720464, -71.518092, 41.941462, -71.289694);
//         List<List<Double>> coordinates = new ArrayList<>();
//         List<Double> coordinate = new ArrayList<>();
//         coordinate.add(-60.500001);
//         coordinate.add(40.800012);
//         coordinates.add(coordinate);
//         assertFalse(bbox.contains(coordinates));
//     }
//     @Test
//     public void testBboxRequestNotContains1MaxLat() {
//         BboxRequest bbox = new BboxRequest(41.720464, -71.518092, 41.941462, -71.289694);
//         List<List<Double>> coordinates = new ArrayList<>();
//         List<Double> coordinate = new ArrayList<>();
//         coordinate.add(-71.500001);
//         coordinate.add(48.800012);
//         coordinates.add(coordinate);
//         assertFalse(bbox.contains(coordinates));
//     }

//     @Test
//     public void testBboxRequestNotContains1Min() {
//         BboxRequest bbox = new BboxRequest(41.720464, -71.518092, 41.941462, -71.289694);
//         List<List<Double>> coordinates = new ArrayList<>();
//         List<Double> coordinate = new ArrayList<>();
//         coordinate.add(-72.500001);
//         coordinate.add(40.800012);
//         coordinates.add(coordinate);
//         assertFalse(bbox.contains(coordinates));
//     }

//     @Test
//     public void testBboxRequestNotContains1Max() {
//         BboxRequest bbox = new BboxRequest(41.720464, -71.518092, 41.941462, -71.289694);
//         List<List<Double>> coordinates = new ArrayList<>();
//         List<Double> coordinate = new ArrayList<>();
//         coordinate.add(-60.500001);
//         coordinate.add(48.800012);
//         coordinates.add(coordinate);
//         assertFalse(bbox.contains(coordinates));
//     }

//     @Test
//     public void testBboxRequestNotContainsEdges() {
//         BboxRequest bbox = new BboxRequest(41.720464, -71.518092, 41.941462, -71.289694);
//         List<List<Double>> coordinates = new ArrayList<>();
//         List<Double> coordinate = new ArrayList<>();

//         coordinate.add(-71.418092);//minLat
//         coordinate.add(41.720464);
//         coordinates.add(coordinate);
//         assertFalse(bbox.contains(coordinates));

//         coordinates.clear(); //maxLat
//         coordinate.clear();
//         coordinate.add(-71.418092);
//         coordinate.add(41.941462);
//         coordinates.add(coordinate);
//         assertFalse(bbox.contains(coordinates));

//         coordinates.clear(); //minLon
//         coordinate.clear();
//         coordinate.add(-71.518092);
//         coordinate.add(41.841462);
//         coordinates.add(coordinate);
//         assertFalse(bbox.contains(coordinates));

//         coordinates.clear(); //maxLon
//         coordinate.clear();
//         coordinate.add(-71.289694);
//         coordinate.add(41.841462);
//         coordinates.add(coordinate);
//         assertFalse(bbox.contains(coordinates));

//     }

//     @Test
//     public void testBboxRequestNotContainsCorners() {
//         BboxRequest bbox = new BboxRequest(41.720464, -71.518092, 41.941462, -71.289694);
//         List<List<Double>> coordinates = new ArrayList<>();
//         List<Double> coordinate = new ArrayList<>();

//         coordinate.add(-71.518092);//minLat, minLon
//         coordinate.add(41.720464);
//         coordinates.add(coordinate);
//         assertFalse(bbox.contains(coordinates));

//         coordinates.clear(); //maxLat , minLon
//         coordinate.clear();
//         coordinate.add(-71.518092);
//         coordinate.add(41.941462);
//         coordinates.add(coordinate);
//         assertFalse(bbox.contains(coordinates));

//         coordinate.add(-71.289694);//minLat, maxLon
//         coordinate.add(41.720464);
//         coordinates.add(coordinate);
//         assertFalse(bbox.contains(coordinates));

//         coordinates.clear(); //maxLat , maxLon
//         coordinate.clear();
//         coordinate.add(-71.289694);
//         coordinate.add(41.941462);
//         coordinates.add(coordinate);
//         assertFalse(bbox.contains(coordinates));
//     }

//     @Test
//     public void testBboxRequestNotContainsMany() {
//         BboxRequest bbox = new BboxRequest(41.720464, -71.518092, 41.941462, -71.289694);
//         List<List<Double>> coordinates = new ArrayList<>();
//         List<Double> coordinate1 = new ArrayList<>();
//         coordinate1.add(-71.500001);
//         coordinate1.add(41.800012);
//         coordinates.add(coordinate1);

//         List<Double> coordinate2 = new ArrayList<>();
//         coordinate2.add(-71.289694);//minLat, maxLon
//         coordinate2.add(41.720464);
//         coordinates.add(coordinate2);

//         List<Double> coordinate3 = new ArrayList<>();
//         coordinate3.add(-71.419285);
//         coordinate3.add(41.800012);
//         coordinates.add(coordinate3);

//         assertFalse(bbox.contains(coordinates));
//     }



// }
