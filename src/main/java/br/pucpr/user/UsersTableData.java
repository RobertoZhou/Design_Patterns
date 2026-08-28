package br.pucpr.user;

import br.pucpr.table.model.TableData;
import java.util.ArrayList;

public class UsersTableData implements TableData {
  private static final int COL_ID = 0;
  private static final int COL_NAME = 1;
  private static final int COL_CPF = 2;
  private static final int COL_EMAIL = 3;

  private final ArrayList<User> users;
  private boolean maskCpf;

  public UsersTableData(ArrayList<User> users, boolean maskCpf) {
    this.users = new ArrayList<>(users);
    this.maskCpf = maskCpf;
  }

  public UsersTableData(ArrayList<User> users) {
    this(users, true);
  }

  public boolean isMaskCpf() {
    return maskCpf;
  }

  public void setMaskCpf(boolean maskCpf) {
    this.maskCpf = maskCpf;
  }

  private static String formatId(Long id) {
    return id != null ? "%4d".formatted(id) : "-";
  }

  private String formatCpf(String cpf) {
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

  private static String formatEmail(String email) {
    return email == null || !email.contains("@") ? "INVÁLIDO" : email;
  }

  private static String formatName(String name) {
    return name == null || name.isEmpty() ? "NÃO INFORMADO" : name;
  }

  @Override
  public int rowCount() {
    return users.size();
  }

  @Override
  public int colCount() {
    return 4;
  }

  @Override
  public String header(int col) {
    return switch (col) {
      case COL_ID -> "  ID";
      case COL_NAME -> "          NOME           ";
      case COL_CPF -> "       CPF      ";
      case COL_EMAIL -> "       E-MAIL       ";
      default -> throw new IllegalStateException();
    };
  }

  @Override
  public String get(int row, int col) {
    final var user = users.get(row);
    return switch (col) {
      case COL_ID -> formatId(user.id());
      case COL_NAME -> formatName(user.name());
      case COL_CPF -> formatCpf(user.cpf());
      case COL_EMAIL -> formatEmail(user.email());
      default -> throw new IllegalStateException();
    };
  }
}
