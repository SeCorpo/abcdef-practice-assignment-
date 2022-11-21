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
         route.addStopOver( locationMap.get( "C" ), LocalTime.of( hour, 31 ), LocalTime.of( hour, 31 ) );
         route.addEndPoint( locationMap.get( "D" ), LocalTime.of( hour, 46 ) );
         routeMap.put( route.getKey(), route );
         route.write();
      }

      /// === Routes D-C-B-A
      // TODO!

      /// === Routes E-B-C-F ========
      // TODO!

      /// === Routes F-C-B-E ========
      // TODO!

      // === Route B-C ===
      // TODO!

      // === Route C-B ===
      // TODO!
   }
}
