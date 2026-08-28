package br.pucpr.user;

import br.pucpr.table.model.ColumnData;

public class IdColumn implements ColumnData<User> {
  @Override
  public String header() {
    return "  ID";
  }

  @Override
  public String get(User user) {
    final var id = user.id();
    return id != null ? "%4d".formatted(id) : "-";
  }
}
