package com.vet.appointment.system.application.debezium;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;

public class DebeziumConnectConfig {

    // Fixed values
    private final String CONNECTOR_CLASS = "io.debezium.connector.postgresql.PostgresConnector";
    private final String DATABASE_NAME = "postgres";
    private final String MAX_TASKS = "1";
    private final String TOMBSTONES_ON_DELETE = "false";
    private final String PLUGIN_NAME = "pgoutput";

    // Values to be set
    private String tableIncludeList;
    private String topicPrefix;
    private String slotName;
    private String name;

    private DebeziumConnectConfig(Builder builder) {
        tableIncludeList = builder.tableIncludeList;
        topicPrefix = builder.topicPrefix;
        slotName = builder.slotName;
        name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public HashMap<String, String> createHashmapConfig() {
        HashMap<String, String> config = new HashMap<>();
        // Manual configuration
        config.put("table.include.list", tableIncludeList);
        config.put("topic.prefix", topicPrefix);
        config.put("slot.name", slotName);

        config.put("connector.class",CONNECTOR_CLASS);
        config.put("tasks.max", MAX_TASKS);
        config.put("tombstones.on.delete", TOMBSTONES_ON_DELETE);
        config.put("plugin.name", PLUGIN_NAME);
        config.put("database.dbname", DATABASE_NAME);

        return config;
    }

    public static final class Builder {
        private String tableIncludeList;
        private String topicPrefix;
        private String slotName;
        private String name;

        private Builder() {
        }

        public Builder tableIncludeList(String val) {
            tableIncludeList = val;
            return this;
        }

        public Builder topicPrefix(String val) {
            topicPrefix = val;
            return this;
        }

        public Builder slotName(String val) {
            slotName = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public DebeziumConnectConfig build() {
            return new DebeziumConnectConfig(this);
        }
    }
}
