package com.nextyu.mybatis;

public interface Dialect {
    boolean supportPage();
    String getPagingSql(String sql, int offset, int limit);
}
