package com.hyve.app.setup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javafaker.Faker;
import com.hyve.app.setup.SpacedDisplayNameGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;

@DisplayNameGeneration(value = SpacedDisplayNameGenerator.class)
public class UnitTest {

    protected ObjectNode json(){
        return new ObjectMapper().createObjectNode();
    }

    protected Faker faker = new Faker();
}