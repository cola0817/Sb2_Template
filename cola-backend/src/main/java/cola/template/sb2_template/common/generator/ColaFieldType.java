package cola.template.sb2_template.common.generator;

import java.util.Optional;

/**
 * @author Cola0817
 * @date 2023/9/8 15:13
 * @since 1.0
 */
public enum ColaFieldType {
    VARCHAR("varchar", "String"),
    INT("int", "Integer"),
    BIGINT("bigint", "Long"),
    TINYINT("tinyint", "Integer"),
    DATETIME("datetime", "LocalDateTime"),
    DATE("date", "LocalDateTime"),
    TIMESTAMP("timestamp", "LocalDateTime"),
    TEXT("text", "String"),
    LONGTEXT("longtext", "String"),
    DECIMAL("decimal", "BigDecimal");

    private String dbType;
    private String javaType;

    ColaFieldType(String dbType, String javaType) {
        this.dbType = dbType;
        this.javaType = javaType;
    }

    public static Optional<ColaFieldType> of(String dbType) {
        for (ColaFieldType fieldType : ColaFieldType.values()) {
            if (fieldType.getDbType().equals(dbType)) {
                return Optional.of(fieldType);
            }
        }
        return Optional.empty();
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}
