package br.pucpr.table.model;

public interface ColumnData<T> {
  String header();

  String get(T object);
}
