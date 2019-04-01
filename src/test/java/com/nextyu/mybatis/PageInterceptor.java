package com.nextyu.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class PageInterceptor implements Interceptor {
    private static int MAPPED_STATEMENT_INDEX = 0;
    private static int PARAMETER_OBJECT_INDEX = 0;
    private static int ROW_BOUNDS_INDEX = 0;

    private Dialect dialect ;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] queryArgs = invocation.getArgs();
        MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        Object parameter = queryArgs[PARAMETER_OBJECT_INDEX];
        RowBounds rowBounds = (RowBounds) queryArgs[ROW_BOUNDS_INDEX];

        int offset = rowBounds.getOffset();
        int limit = rowBounds.getLimit();

        BoundSql boundSql = ms.getBoundSql(parameter);
        String sql = boundSql.getSql();

        if (dialect.supportPage()) {
            sql = dialect.getPagingSql(sql, offset, limit);
            // 当前拦截的 Executor.query() 方法中的 RowBounds 参数不再控制查找结果集的范围，所以要进行重置
            queryArgs[ROW_BOUNDS_INDEX] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
        }

        queryArgs[MAPPED_STATEMENT_INDEX] = createMappedStatement(ms, boundSql, sql);

        return invocation.proceed();
    }

    private Object createMappedStatement(MappedStatement ms, BoundSql boundSql, String sql) {
        return null;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
