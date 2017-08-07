package com.lvjc.cons;

/**
 * Created by lvjc on 2017/6/22.
 */
public class JdbcConstants {

    public static final String TABLE_USER = "t_user";

    public static final String TABLE_ANALYSIS = "t_analysis";

    public static final String TABLE_INCREMENTER = "t_id_generator";

    public static final String KEY_USER = "U";
    /**
     * 用户独立的数据表：表id，表名前缀，id标识符
     */
    public static final String PREFIX_TABLE_TABLE_INFO = "t_table_info_";

    //Transaction表命名："t_transaction_" + 年份 + 用户id
    //表id："table_transaction_" + 年份
    public static final String KEY_TRANSACTION = "TX";
    public static final String PREFIX_TABLE_TRANSACTION = "t_transaction_";

    public static final String KEY_TRANSACTION_MODE = "TM";
    public static final String PREFIX_TABLE_TRANSACTION_MODE = "t_transaction_mode_";

    public static final String KEY_TRANSACTION_FIELD = "TF";
    public static final String PREFIX_TABLE_TRANSACTION_FIELD = "t_transaction_field_";


    public static final String ID_TABLE_ID_GENERATOR = "table_id_generator";
    public static final String PREFIX_TABLE_ID_GENERATOR = "t_id_generator_";
}
