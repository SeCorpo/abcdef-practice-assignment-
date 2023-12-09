import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

public class Data
{
    private final Map<String, Location> locationMap = new TreeMap<>();
    private final Map<String, Route>    routeMap    = new TreeMap<>();

    public Data()
    {
        /// === Locations A ... F ========
        var location = new Location( "A" );
        locationMap.put( location.getName(), location );

        location = new Location( "B" );
        locationMap.put( location.getName(), location );

        location = new Location( "C" );
        locationMap.put( location.getName(), location );

        location = new Location( "D" );
        locationMap.put( location.getName(), location );

        location = new Location( "E" );
        locationMap.put( location.getName(), location );

        location = new Location( "F" );
        locationMap.put( location.getName(), location );

        ////////////////////////////////////////////////////////////

        /// === Routes A-B-C-D ========
        for (int hour = 7; hour <= 19; hour += 2)
        {
            var departure = LocalTime.of( hour, 0 );
            var route     = new Route( locationMap.get( "A" ), departure );
            route.addStopOver( locationMap.get( "B" ), LocalTime.of( hour, 15 ), LocalTime.of( hour, 16 ) );
            route.addStopOver( locationMap.get( "C" ), LocalTime.of( hour, 31 ), LocalTime.of( hour, 33 ) );
            route.addEndPoint( locationMap.get( "D" ), LocalTime.of( hour, 48 ) );
            routeMap.put( route.getKey(), route );
        }

        /// === Routes D-C-B-A
        for (int hour = 7; hour <= 19; hour += 2)
        {
            var departure = LocalTime.of( hour, 0 );
            var route     = new Route( locationMap.get( "D" ), departure );
            route.addStopOver( locationMap.get( "C" ), LocalTime.of( hour, 15 ), LocalTime.of( hour, 17 ) );
            route.addStopOver( locationMap.get( "B" ), LocalTime.of( hour, 32 ), LocalTime.of( hour, 33 ) );
            route.addEndPoint( locationMap.get( "A" ), LocalTime.of( hour, 48 ) );
            routeMap.put( route.getKey(), route );
        }

        /// === Routes E-B-C-F ========
        for(int hour = 8; hour <= 17; hour += 1)
        {
            LocalTime departure = LocalTime.of(hour, 30);
            Route route = new Route(locationMap.get("E"), departure);
            route.addStopOver(locationMap.get("B"), LocalTime.of(hour, 45), LocalTime.of(hour, 46));
            route.addStopOver(locationMap.get("C"), LocalTime.of(hour + 1, 1), LocalTime.of(hour + 1, 3));
            route.addEndPoint(locationMap.get("F"), LocalTime.of(hour + 1, 18));
            routeMap.put( route.getKey(), route );
        }

        /// === Routes F-C-B-E ========
        for(int hour = 8; hour <= 17; hour += 1)
        {
            LocalTime departure = LocalTime.of(hour, 30);
            Route route = new Route(locationMap.get("F"), departure);
            route.addStopOver(locationMap.get("C"), LocalTime.of(hour, 45), LocalTime.of(hour, 47));
            route.addStopOver(locationMap.get("B"), LocalTime.of(hour + 1, 2), LocalTime.of(hour + 1, 3));
            route.addEndPoint(locationMap.get("E"), LocalTime.of(hour + 1, 18));
            routeMap.put( route.getKey(), route );
        }

        // === Route B-C ===
        {
            var route = new Route(locationMap.get("B"), LocalTime.of(12, 00));
            route.addEndPoint(locationMap.get("C"), LocalTime.of(12, 15));
            routeMap.put(route.getKey(), route);
        }

        // === Route C-B ===
        {
            var route = new Route(locationMap.get("C"), LocalTime.of(12, 00));
            route.addEndPoint(locationMap.get("B"), LocalTime.of(12, 15));
            routeMap.put(route.getKey(), route);
        }
    }
    public void writeAllRoutes() {

        System.out.println("write all routes");

        for (Route oneRoute : routeMap.values()) {
            oneRoute.write();
        }
    }
    public void writeRoutesABCD() {
        String abcd = "A-B-C-D";

        System.out.println("write routes containing " + abcd);

        for(Route routeABCD : routeMap.values()) {

            if (routeABCD.getKey().contains(abcd)) {
                routeABCD.write();
            }
        }
    }
    public void writeRoutesBD() {
        String bc = "B-C";

        System.out.println("write routes containing " + bc);

        for(Route routeBD : routeMap.values()) {

            if (routeBD.getKey().contains(bc)) {
                routeBD.write();
            }
        }
    }
    public void writeRoutes_Search_ByLocation() {
        String searchA = "B";

        System.out.println("write routes by location, all routes containing location " + searchA);

        for(Route routeLocation : routeMap.values()) {
            for(StopOver stopSearch : routeLocation.getStopOvers()) {
                if(stopSearch.getName().equals(searchA)) {
                    routeLocation.write();
                }
            }
        }
    }
    public void writeRoutes_Search_ByTwoLocations() {
        String searchA = "F";
        String searchB = "C";

        System.out.println("write routes by two locations, between " + searchA + " & " + searchB);

        for (Route route : routeMap.values()) {
            for (StopOver stopOver : route.getStopOvers()) {
                if (stopOver.getName().equals(searchA)) {
                    for (StopOver stopOver2 : route.getStopOvers()) {
                        if (stopOver2.getName().equals(searchB)) {
                            route.write();
                        }
                    }
                }
            }
        }

    }

    public void writeRoutes_Search_ByTwoLocations_v2(String searchA, String searchB) {

        System.out.println("write routes by two locations v2, between " + searchA + " & " + searchB);

        boolean routeFound = false;
        for (Route route : routeMap.values()) {
            boolean foundSearchA = false;
            boolean foundSearchB = false;
            for (StopOver stopOver : route.getStopOvers()) {
                if (stopOver.getName().equals(searchA)) {
                    foundSearchA = true;
                } else if (stopOver.getName().equals(searchB)) {
                    foundSearchB = true;
                }
            }
            if (foundSearchA && foundSearchB) {
                route.write();
                routeFound = true;
            }
        }
        if (!routeFound) {
            System.out.println("Cannot find a route between: " + searchA + " & " + searchB);
        }
    }

    public void findTrack(String a, String b, LocalTime c) {
        System.out.println("write routes between " + a + " & " + b + " @ " + c.toString());

        int ia = 0;
        int ib = 0;

        boolean routeFound = false;
        for (Route route : routeMap.values()) {
            boolean foundSearchA = false;
            boolean foundSearchB = false;
            boolean foundSearchC = false;
            for (StopOver stopOver : route.getStopOvers()) {
                if (stopOver.getName().equals(a)) {
                    foundSearchA = true;
                    ia = route.getStopOvers().indexOf(stopOver);
                } else if (stopOver.getName().equals(b)) {
                    foundSearchB = true;
                    ib = route.getStopOvers().indexOf(stopOver);
                }
            }
            if(route.getStopOvers().get(0).getDeparture().equals(c)) {
                foundSearchC = true;
            }

            if (foundSearchA && foundSearchB && foundSearchC && ia < ib) {
                route.write();
                routeFound = true;
            }
        } if (!routeFound) {
            System.out.println("Cannot find a route between: " + a + " & " + b + " @ " + c);
        }
    }
}
