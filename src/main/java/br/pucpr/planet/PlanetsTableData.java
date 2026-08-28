package br.pucpr.planet;

import br.pucpr.table.model.TableData;
import java.util.ArrayList;

public class PlanetsTableData implements TableData {
  private final ArrayList<Planet> planets;
  private static final int COL_NAME = 0;
  private static final int COL_DIAMETER = 1;
  private static final int COL_SUN_DISTANCE_KM = 2;
  private static final int COL_SUN_DISTANCE_AU = 3;
  private static final int COL_TYPE = 4;

  public PlanetsTableData(ArrayList<Planet> planets) {
    this.planets = new ArrayList<>(planets);
  }

  private static String formatName(String name) {
    if (name == null || name.isEmpty()) {
      return "NÃO INFORMADO";
    }
    if (name.length() > 20) {
      name = name.substring(0, 17) + "...";
    }
    return name;
  }

  private static String formatType(PlanetType type) {
    return switch (type) {
      case ROCK -> "Rochoso";
      case GAS -> "Gasoso";
      case ICE -> "Gelado";
      case DWARF -> "Anão";
    };
  }

  @Override
  public int rowCount() {
    return planets.size();
  }

  @Override
  public int colCount() {
    return 5;
  }

  @Override
  public String header(int col) {
    return switch (col) {
      case COL_NAME -> "%-10s".formatted("Nome");
      case COL_DIAMETER -> "%10s".formatted("Diâmetro");
      case COL_SUN_DISTANCE_KM -> "%15s".formatted("Dist. sol (km)");
      case COL_SUN_DISTANCE_AU -> "%15s".formatted("Dist. sol (ua)");
      case COL_TYPE -> "%-10s".formatted("Tipo");
      default -> throw new IllegalStateException();
    };
  }

  @Override
  public String get(int row, int col) {
    final var planet = planets.get(row);
    return switch (col) {
      case COL_NAME -> formatName(planet.name());
      case COL_DIAMETER -> "%,10.1f".formatted(planet.diameterKm());
      case COL_SUN_DISTANCE_KM -> "%,15d".formatted(planet.sunDistanceKm());
      case COL_SUN_DISTANCE_AU -> "%,15.2f".formatted(Planet.kmToAu(planet.sunDistanceKm()));
      case COL_TYPE -> "%-10s".formatted(formatType(planet.type()));
      default -> throw new IllegalStateException();
    };
  }
}
