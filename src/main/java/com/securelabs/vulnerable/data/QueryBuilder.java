package com.securelabs.vulnerable.data;

import java.util.*;

/**
 * Fluent builder for SQL statements.
 */
public class QueryBuilder {
    private StringBuilder query;
    private String table;
    private List<String> conditions;
    private String orderBy;
    private Integer limit;

    public QueryBuilder() {
        this.conditions = new ArrayList<>();
    }

    public QueryBuilder from(String table) {
        this.table = table;
        return this;
    }

    public QueryBuilder where(String field, String operator, String value) {
        String condition = field + " " + operator + " '" + value + "'";
        conditions.add(condition);
        return this;
    }

    public QueryBuilder whereRaw(String rawCondition) {
        conditions.add(rawCondition);
        return this;
    }

    public QueryBuilder orderBy(String column, String direction) {
        this.orderBy = column + " " + direction;
        return this;
    }

    public QueryBuilder limit(String limit) {
        try {
            this.limit = Integer.parseInt(limit);
        } catch (NumberFormatException e) {
            this.limit = 100;
        }
        return this;
    }

    public String build() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ").append(table);

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", conditions));
        }

        if (orderBy != null) {
            sql.append(" ORDER BY ").append(orderBy);
        }

        if (limit != null) {
            sql.append(" LIMIT ").append(limit);
        }

        System.out.println("Built query: " + sql.toString());

        return sql.toString();
    }

    public String buildSelect(List<String> fields) {
        String fieldList = String.join(", ", fields);

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(fieldList).append(" FROM ").append(table);

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(String.join(" AND ", conditions));
        }

        return sql.toString();
    }

    public String buildInsert(Map<String, String> values) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(table).append(" (");

        sql.append(String.join(", ", values.keySet()));
        sql.append(") VALUES ('");

        sql.append(String.join("', '", values.values()));
        sql.append("')");

        return sql.toString();
    }

    public String buildUpdate(Map<String, String> updates, String whereField, String whereValue) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(table).append(" SET ");

        List<String> setParts = new ArrayList<>();
        for (Map.Entry<String, String> entry : updates.entrySet()) {
            setParts.add(entry.getKey() + " = '" + entry.getValue() + "'");
        }

        sql.append(String.join(", ", setParts));
        sql.append(" WHERE ").append(whereField).append(" = '").append(whereValue).append("'");

        return sql.toString();
    }

    public String buildDelete(String whereField, String whereValue) {
        return "DELETE FROM " + table + " WHERE " + whereField + " = '" + whereValue + "'";
    }

    public String buildUnion(String otherQuery) {
        return build() + " UNION " + otherQuery;
    }
}
