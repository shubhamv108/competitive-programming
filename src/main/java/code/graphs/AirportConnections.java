package code.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class AirportConnections {

    HashMap<String, HashSet<String>> routes = new HashMap<>();
    String zeroOutOrderAirport;

    AirportConnections(String[][] airportRoutes) {
        for (String[] airportRoute : airportRoutes) {
            routes.putIfAbsent(airportRoute[0], new HashSet<>()).add(airportRoute[1]);
            routes.putIfAbsent(airportRoute[1], new HashSet<>());
        }
        for (String airport : routes.keySet()) {
            if (routes.get(airport).size() == 0) {
                zeroOutOrderAirport = airport;
                break;
            }
        }
    }

    public static void main(String[] args) {
        String[][] airportRoutes = {
                {"BLR", "PNE"},
                {"HYD", "LKO"}
        };
    }

}
