package br.pucpr.table;

public enum Theme {
  LIGHT("-"),
  NORMAL("="),
  DARK("#");

  private final String borderChar;

  Theme(String BorderChar) {
    this.borderChar = BorderChar;
  }

  public String getBorderChar() {
    return borderChar;
  }
}
