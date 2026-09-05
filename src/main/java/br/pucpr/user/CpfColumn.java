package br.pucpr.user;

import br.pucpr.table.model.ColumnData;

public class CpfColumn implements ColumnData<User> {
  private final boolean maskCpf;

  public CpfColumn() {
    this(true);
  }

  public CpfColumn(boolean maskCpf) {
    this.maskCpf = maskCpf;
  }

  @Override
  public String header() {
    return "       CPF      ";
  }

  @Override
  public String get(User user) {
    final var cpf = user.cpf();
    if (cpf == null || cpf.length() != 11) {
      return "CPF INVÁLIDO";
    }
    if (maskCpf) {
      return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
    }
    return cpf.substring(0, 3)
        + "."
        + cpf.substring(3, 6)
        + "."
        + cpf.substring(6, 9)
        + "-"
        + cpf.substring(9, 11);
  }
}