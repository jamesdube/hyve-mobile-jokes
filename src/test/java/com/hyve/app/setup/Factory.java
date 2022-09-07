package com.hyve.app.setup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Factory {

    private final HashMap<Class<?>,EntityFactory> factories = new HashMap<>();
    private final Gson gson = new Gson();
    private final EntityManager entityManager;

    public Factory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void register(Class<?> clazz,EntityFactory factory){
        factories.put(clazz,factory);
    }

    public <E> FactoryBuilder<E> build(Class<E> eClass){
        EntityFactory builder = factories.get(eClass);
        ObjectNode node = builder.build();
        return new FactoryBuilder<>(eClass, node, entityManager, builder);
    }

    public static class FactoryBuilder<E> {

        private ObjectNode original;
        private final Class<E> clazz;
        private final Gson gson = new Gson();
        private final EntityManager entityManager;
        private final EntityFactory entityFactory;

        public FactoryBuilder(Class<E> clazz, ObjectNode original, EntityManager entityManager, EntityFactory entityFactory) {
            this.original = original;
            this.clazz = clazz;
            this.entityManager = entityManager;
            this.entityFactory = entityFactory;
        }

        public FactoryBuilder<E> attributes(ObjectNode json) throws IOException {
            ObjectReader updater = new ObjectMapper().readerForUpdating(original);
            original = updater.readValue(json);
            return this;
        }

        public FactoryCollectionBuilder<E> count(int count){
            return new FactoryCollectionBuilder<>(count,this);
        }

        public E make() {
            return gson.fromJson(entityFactory.build().toString(), clazz);
        }

        public ObjectNode makeJson() {
            return original;
        }

        public E create() {
            E entity = make();
            entityManager.persist(entity);
            return entity;
        }
    }

    public static class FactoryCollectionBuilder<E> {

        private final Integer count;
        private final FactoryBuilder<E> factoryBuilder;

        public FactoryCollectionBuilder(Integer count, FactoryBuilder<E> factoryBuilder) {
            this.count = count;
            this.factoryBuilder = factoryBuilder;
        }

        public Collection<E> make(){
            return IntStream.range(0, count)
                    .mapToObj(i -> factoryBuilder.make())
                    .collect(Collectors.toList());
        }

        public Collection<E> create(){
            return make().stream()
                    .peek(factoryBuilder.entityManager::persist)
                    .collect(Collectors.toList());
        }
    }
}
