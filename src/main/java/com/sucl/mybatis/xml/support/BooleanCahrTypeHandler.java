package com.sucl.mybatis.xml.support;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 如果用包扫描，必须加上注解
 * 如果通过xml配置 可以然选其一
 */
//@MappedJdbcTypes(JdbcType.VARCHAR) //对应jdbcType
//@MappedTypes({String.class}) //对应javaType、泛型
@MappedJdbcTypes(JdbcType.CHAR)
public class BooleanCahrTypeHandler extends BaseTypeHandler<Boolean> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
        if(parameter){
            ps.setString(i,"Y");
        }else{
            ps.setString(i,"N");
        }
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return null;
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String result = cs.getString(columnIndex);
        if("Y".equals(result)){
            return true;
        }else if("N".equals(result)){
            return false;
        }
        return null;
    }
}
