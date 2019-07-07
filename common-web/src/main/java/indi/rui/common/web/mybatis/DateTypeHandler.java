package indi.rui.common.web.mybatis;

import indi.rui.common.base.util.DateUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import java.util.Date;

import java.sql.*;

@MappedJdbcTypes(JdbcType.TIMESTAMP)
@MappedTypes(Date.class)
public class DateTypeHandler extends BaseTypeHandler<Date> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(i, new Timestamp(parameter.getTime()));
    }

    @Override
    public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String date = rs.getString(columnName);
        if (date != null) {
            return DateUtil.stringToDate(date);
        }
        return null;
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String date = rs.getString(columnIndex);
        if (date != null) {
            return DateUtil.stringToDate(date);
        }
        return null;
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String date = cs.getString(columnIndex);
        if (date != null) {
            return DateUtil.stringToDate(date);
        }
        return null;
    }
}
