package br.pucpr.user;

import br.pucpr.table.model.ColumnData;

public class NameColumn implements ColumnData<User> {
  @Override
  public String header() {
    return "          NOME           ";
  }

  @Override
  public String get(User user) {
    final var name = user.name();
    return name == null || name.isEmpty() ? "NÃO INFORMADO" : name;
  }
}