package com.hyve.app.setup.factories;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hyve.app.setup.EntityFactory;

import java.time.LocalDateTime;

import static com.hyve.app.setup.JsonNodeBuilders.array;
import static com.hyve.app.setup.JsonNodeBuilders.object;
import static java.lang.String.format;

public class ApiJokeFactory implements EntityFactory {

    @Override
    public ObjectNode build() {

        String id = faker.idNumber().valid();
        return object()
                .with("categories",array().with("food"))
                .with("created_at", LocalDateTime.now().toString())
                .with("icon_url","https://assets.chucknorris.host/img/avatar/chuck-norris.png")
                .with("id",id)
                .with("updated_at",LocalDateTime.now().toString())
                .with("url",format("https://api.chucknorris.io/%s",id))
                .with("value",faker.chuckNorris().fact())
                .end();
    }
}
