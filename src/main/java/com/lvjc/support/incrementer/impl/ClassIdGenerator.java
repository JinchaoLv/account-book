package com.lvjc.support.incrementer.impl;

import com.lvjc.po.Transaction;
import com.lvjc.support.incrementer.PersistentObjectInfo;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lvjc on 2017/6/20.
 */
public class ClassIdGenerator extends AbstractIdGenerator {

    private Map<Class, String> sequenceIdMap;

    //序列表字段名
    private static final String GEN_SEQUENCE_ID = "gen_sequenceId";
    private static final String GEN_SEQUENCE_VALUE = "gen_sequenceValue";

    //从数据库读取后初始化
    private Map<String, SequenceInfo> sequenceInfoMap = new HashMap<>();

    //计数器，当count值达到cacheSize时，更新数据库
    private Map<String, Integer> countMap = new HashMap<>();

    @Override
    public void init() {
        Connection con = null;
        Statement stmt = null;
        try {
            con = DataSourceUtils.getConnection(dataSource);
            stmt = con.createStatement();
            String querySql = "SELECT * FROM " + sequenceTableName;
            ResultSet rs = stmt.executeQuery(querySql);
            try {
                while (rs.next()) {
                    SequenceInfo sequenceInfo = new SequenceInfo();
                    String sequenceId = rs.getString(1);
                    long sequenceValue = rs.getLong(2);
                    sequenceInfo.setSequenceId(sequenceId);
                    sequenceInfo.setSequenceValue(sequenceValue);
                    sequenceInfoMap.put(sequenceId, sequenceInfo);
                    countMap.put(sequenceId, 0);
                }
            } finally {
                JdbcUtils.closeResultSet(rs);
            }
        } catch (SQLException e) {
            throw new DataAccessResourceFailureException("Could not read sequenceId sequence table", e);
        } finally {
            JdbcUtils.closeStatement(stmt);
            DataSourceUtils.releaseConnection(con, dataSource);
        }
    }

    @Override
    protected long nextKeyValue(PersistentObjectInfo info) {
        String sequenceId = sequenceIdMap.get(info.getPersistentClass());
        SequenceInfo sequenceInfo = sequenceInfoMap.get(sequenceId);
        long sequenceValue = sequenceInfo.getSequenceValue();
        ++sequenceValue;
        sequenceInfo.setSequenceValue(sequenceValue);
        int count = countMap.get(sequenceId) + 1;
        if (count == cacheSize) {
            count -= cacheSize;
            Connection con = null;
            PreparedStatement stmt = null;
            try {
                con = DataSourceUtils.getConnection(dataSource);
                String updateSql = "UPDATE " + sequenceTableName + " SET " + GEN_SEQUENCE_VALUE + " = ? WHERE " + GEN_SEQUENCE_ID + " = ?";
                stmt = con.prepareStatement(updateSql);
                stmt.setLong(1, sequenceValue);
                stmt.setString(2, sequenceId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new DataAccessResourceFailureException("Could not update sequenceId sequence table", e);
            } finally {
                JdbcUtils.closeStatement(stmt);
                DataSourceUtils.releaseConnection(con, dataSource);
            }
        }
        countMap.put(sequenceId, count);
        return sequenceValue;
    }

    @Override
    public String nextStringId(PersistentObjectInfo info) throws Exception {
        if (strategyMap == null) {
            return generateIdBydefaultStrategy(info);
        } else {
            IdGenerationStrategy strategy = strategyMap.get(info.getPersistentClass());
            if (strategy == null)
                return generateIdBydefaultStrategy(info);
            else
                return strategy.nextId(info, nextKeyValue(info));
        }
    }

    private String generateIdBydefaultStrategy(PersistentObjectInfo info) {
        String sequenceId = sequenceIdMap.get(info.getPersistentClass());
        return sequenceId + nextKeyValue(info);
    }

    public void setSequenceIdMap(Map map) {
        this.sequenceIdMap = map;
    }

    public List<SequenceInfo> getSequenceInfoList() {
        List<SequenceInfo> list = new ArrayList<>();
        for (SequenceInfo info : sequenceInfoMap.values()) {
            list.add(info);
        }
        return list;
    }

}
