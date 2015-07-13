package com.dianping.wed.monitor.data.enums;

/**
 * @author dan.shan
 * @since 2015-06-05 10:46
 */
public enum Datasource {

    MongoWedding(DSType.MongoDB, "wedding.mongo"),
    MysqlWedding(DSType.MySQL, "wedding.mysql");

    public final DSType dsType;
    public final String dsName;

    private Datasource(DSType dsType, String dsName) {
        this.dsType = dsType;
        this.dsName = dsName;
    }

    public static enum DSType {
        MongoDB, MySQL, Hive, Redis
    }

}
