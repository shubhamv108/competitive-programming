package code.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class AirportConnections {

    HashMap<String, HashSet<String>> routes = new HashMap<>();
    Map<String, Integer> inDegrees = new HashMap<>();
    AirportConnections(String[] airports, String[][] airportRoutes, String startingAirport) {

        for (String airport : airports)
            inDegrees.put(airport, 0);

        for (String[] airportRoute : airportRoutes) {
            routes.putIfAbsent(airportRoute[0], new HashSet<>()).add(airportRoute[1]);
            routes.putIfAbsent(airportRoute[1], new HashSet<>());
            inDegrees.put(airportRoute[1], inDegrees.getOrDefault(airportRoute[1], 0) + 1);
        }
    }

    int get() {
        for (Map.Entry<String, Integer> entry : this.inDegrees.entrySet()) {

        }
        return 0;
    }

    public static void main(String[] args) {
        String[][] airportRoutes = {
                {"BLR", "PNE"},
                {"HYD", "LKO"}
        };
    }

}
