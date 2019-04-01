package com.nextyu.mybatis;

public class MySQLDialect implements Dialect {
    @Override
    public boolean supportPage() {
        return true;
    }

    @Override
    public String getPagingSql(String sql, int offset, int limit) {
        sql = sql.trim();
        StringBuilder pagingSql = new StringBuilder(sql.length() + 100);
        pagingSql.append(" limit ");
        if (offset > 0) {
            pagingSql.append(offset).append(",").append(limit);
        }
        pagingSql.append(limit);
        return pagingSql.toString();
    }
}
