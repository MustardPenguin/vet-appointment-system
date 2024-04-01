package com.vet.appointment.system.application.debezium;

import java.util.HashMap;

public record DebeziumConnectRequestBody(String name, HashMap<String, String> config) {
}