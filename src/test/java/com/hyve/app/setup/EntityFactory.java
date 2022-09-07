package com.hyve.app.setup;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javafaker.Faker;

public interface EntityFactory {

    Faker faker = new Faker();

    ObjectNode build();
}
