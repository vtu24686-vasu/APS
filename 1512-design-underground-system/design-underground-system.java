import java.util.*;

class UndergroundSystem {

    class CheckIn {
        String station;
        int time;

        CheckIn(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    class Route {
        int totalTime;
        int count;

        Route(int totalTime, int count) {
            this.totalTime = totalTime;
            this.count = count;
        }
    }

    private Map<Integer, CheckIn> checkInMap;
    private Map<String, Route> routeMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckIn checkInData = checkInMap.get(id);

        String routeKey = checkInData.station + "-" + stationName;
        int travelTime = t - checkInData.time;

        Route route = routeMap.getOrDefault(routeKey, new Route(0, 0));
        route.totalTime += travelTime;
        route.count += 1;

        routeMap.put(routeKey, route);
    }

    public double getAverageTime(String startStation, String endStation) {
        Route route = routeMap.get(startStation + "-" + endStation);
        return (double) route.totalTime / route.count;
    }
}