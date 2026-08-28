package br.pucpr.planet;

public record Planet(String name, double diameterKm, long sunDistanceKm, PlanetType type) {
  public static final long EARTH_SUN_DISTANCE_KM = 149_600_000L;

  public static double kmToAu(long km) {
    return km / (double) EARTH_SUN_DISTANCE_KM;
  }
}
