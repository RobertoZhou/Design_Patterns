package br.pucpr.table.model;

/** A TableData decorator that exposes one page of the wrapped data. */
public final class PaginatedTableData implements TableData {
  private final TableData data;
  private final int pageSize;
  private int page;

  public PaginatedTableData(TableData data, int pageSize) {
    if (data == null) {
      throw new IllegalArgumentException("Data cannot be null");
    }
    if (pageSize <= 0) {
      throw new IllegalArgumentException("Page size must be positive");
    }
    this.data = data;
    this.pageSize = pageSize;
  }

  public TableData getData() {
    return data;
  }

  public int getPageSize() {
    return pageSize;
  }

  public int getPage() {
    return page;
  }

  public int getPageCount() {
    return (data.rowCount() + pageSize - 1) / pageSize;
  }

  public boolean hasPreviousPage() {
    return page > 0;
  }

  public boolean hasNextPage() {
    return page + 1 < getPageCount();
  }

  public void setPage(int page) {
    if (page < 0 || page >= getPageCount()) {
      throw new IllegalArgumentException("Page is out of bounds");
    }
    this.page = page;
  }

  public void nextPage() {
    if (hasNextPage()) {
      page++;
    }
  }

  public void previousPage() {
    if (hasPreviousPage()) {
      page--;
    }
  }

  @Override
  public int rowCount() {
    final var firstRow = page * pageSize;
    return Math.min(pageSize, data.rowCount() - firstRow);
  }

  @Override
  public int colCount() {
    return data.colCount();
  }

  @Override
  public String header(int col) {
    return data.header(col);
  }

  @Override
  public String get(int row, int col) {
    if (row < 0 || row >= rowCount()) {
      throw new IndexOutOfBoundsException("Row is out of bounds");
    }
    return data.get(page * pageSize + row, col);
  }
}