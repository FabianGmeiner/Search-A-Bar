//Created by Fabian Gmeiner on 22.05.15.

package utils;

public class GPSService {

    // Method to use in the Graph to calculate the direct distance
    // between two points based on their GPS-data.
    // Formula used: the so-called Haversine-formula
    public static double getDistanceFromGPS(double lat1, double long1, double lat2, double long2) {
        double alpha1 = Math.toRadians(lat1);
        double alpha2 = Math.toRadians(lat2);
        double deltaAlpha = Math.toRadians(lat2 - lat1);
        double deltaBeta = Math.toRadians(long2 - long1);

        double a = Math.sin(deltaAlpha / 2) * Math.sin(deltaAlpha / 2) +
                Math.cos(alpha1) * Math.cos(alpha2) *
                        Math.sin(deltaBeta / 2) * Math.sin(deltaBeta / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        int earthRadius = 6371000;      // in meters
        double d = earthRadius * c;

        return (Math.round(d));
    }
}
