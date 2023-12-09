import java.time.LocalTime;

public class ABCDEF
{
   public static void main( String[] args )
   {
      var data = new Data();

//      data.writeAllRoutes();
//      System.out.println();
//      data.writeRoutesABCD();
//
//      System.out.println();
//      data.writeRoutesBD();
//
//      System.out.println();
//      data.writeRoutes_Search_ByLocation();
//
//      System.out.println();
//      data.writeRoutes_Search_ByTwoLocations();
//
//      System.out.println();
//      data.writeRoutes_Search_ByTwoLocations_v2("F","C");
//
//      System.out.println();
      data.findTrack("F","C", LocalTime.of(9,30));


   }
}
