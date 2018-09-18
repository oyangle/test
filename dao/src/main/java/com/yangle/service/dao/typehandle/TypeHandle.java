package com.yangle.service.dao.typehandle;

import com.yangle.service.common.enums.CodeDescEnum;
import com.yangle.service.common.enums.dao.DeleteFlagEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * programname: product_factory
 * <p>
 * title: 枚举与mysql值转化
 *
 * @author: yishao
 * <p>
 * created: 2018-09-17 17:22
 **/
@MappedTypes({DeleteFlagEnum.class})
public class TypeHandle<E extends  Enum<E>> extends BaseTypeHandler<E> {

    private Class<E> type;
    private final E[] enums;

    public TypeHandle(Class<E> type){
        if(type==null){
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        if(!CodeDescEnum.class.isAssignableFrom(type)){
            throw new IllegalArgumentException(type.getName() + " must implement the interface " + CodeDescEnum.class.getName());
        }
        this.type=type;
        this.enums =type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
    }

    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, ((CodeDescEnum) parameter).getCode());
    }

    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        if(rs.wasNull()){
            return null;
        }
        return convert(i);
    }

    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        if(rs.wasNull()){
            return null;
        }
        return convert(i);
    }

    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if(cs.wasNull()){
            return null;
        }
        return convert(i);
    }

    private E convert(int value){
        for (E e:enums) {
            if(value == ((CodeDescEnum)e).getCode()){
                return e;
            }
        }
        throw new IllegalArgumentException("Cannot convert " + value + " to " + type.getSimpleName() + " by value.");
    }
}
