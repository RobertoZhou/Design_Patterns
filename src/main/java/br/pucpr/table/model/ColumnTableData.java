package br.pucpr.table.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ColumnTableData<T> implements TableData {
  private final List<ColumnData<? super T>> columns;
  private final List<T> data;

  public ColumnTableData(Collection<? extends T> data, Collection<ColumnData<? super T>> columns) {
    this.columns = new ArrayList<>(columns);
    this.data = new ArrayList<>(data);
  }

  @SafeVarargs
  public ColumnTableData(Collection<? extends T> data, ColumnData<? super T>... columns) {
    this(data, Arrays.asList(columns));
  }

  @Override
  public int rowCount() {
    return data.size();
  }

  @Override
  public int colCount() {
    return columns.size();
  }

  @Override
  public String header(int col) {
    return columns.get(col).header();
  }

  @Override
  public String get(int row, int col) {
    var line = data.get(row);
    return columns.get(col).get(line);
  }
}
