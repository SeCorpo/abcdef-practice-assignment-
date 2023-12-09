import java.time.LocalTime;
import java.util.ArrayList;

public class Route
{
   public ArrayList<StopOver> getStopOvers() {
      return stopOvers;
   }

   private final ArrayList<StopOver> stopOvers = new ArrayList<>();

   ///////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////
   public Route( Location beginLocation, LocalTime departure )
   {
      StopOver stopover = new StopOver( beginLocation.getName(), departure, departure );
      stopOvers.add( stopover );
   }

   ///////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////
   public void addStopOver( Location loc, LocalTime arrival, LocalTime departure )
   {
      var stopover = new StopOver( loc.getName(), arrival, departure );
      stopOvers.add( stopover );
   }

   ///////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////
   public void addEndPoint( Location loc, LocalTime arrival )
   {
      var stopover = new StopOver( loc.getName(), arrival, arrival );
      stopOvers.add( stopover );
   }

   ///////////////////////////////////////////////////////////////
   // Construct a key associated with a Route instance by appending
   // the names of the stopovers in this route, separated by a '-'.
   // To make the key unique, append '|' + departure time.
   ///////////////////////////////////////////////////////////////
   public String getKey()
   {
      String key = stopOvers.get( 0 ).getName();

      for (int i = 1; i < stopOvers.size(); i++)
      {
         key += "-";
         key += stopOvers.get( i ).getName();
      }

      key += "|";
      key += stopOvers.get( 0 ).getDeparture();

      return key;
   }

   ///////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////
   public void write() {

      var first = stopOvers.get(0);
      var last = stopOvers.get(stopOvers.size() - 1);

      System.out.format("%-6s [%-13s] %-4s %-10s %-2s %-2s %-5s %-4s %-8s %-2s %-2s %-5s\n", "Route:", getKey(), " ", "Departure: ", first.getName(), "at ",
              first.getDeparture(), " ", "Arrival: ", last.getName(), "at ", last.getArrival());

      if (first.getName() == null) {
         System.out.println("The begin location cannot be found.");
      } else if (last.getName() == null) {
         System.out.println("The end location cannot be found.");
      }
   }

}
