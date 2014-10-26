/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package lv.javaguru.ee.bookshop.core.database.mongo;

import lv.javaguru.ee.bookshop.core.database.CategoryRepository;
import lv.javaguru.ee.bookshop.core.domain.mongo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.math.BigInteger;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * {@link CategoryRepository} implementation using the Spring Data {@link org.springframework.data.mongodb.core.MongoOperations} API.
 */
@Repository
//@Profile("mongodb")     WHAT FOR ???
class CategoryRepositoryImpl implements CategoryRepository {

    private final MongoOperations operations;

    /**
     * Creates a new {@link CategoryRepositoryImpl} using the given {@link org.springframework.data.mongodb.core.MongoOperations}.
     *
     * @param operations must not be {@literal null}.
     */
    @Autowired
    public CategoryRepositoryImpl(MongoOperations operations) {

        Assert.notNull(operations);
        this.operations = operations;
    }

    @Override
    public Category findOne(BigInteger id) {

        Query query = query(where("id").is(id));
        return operations.findOne(query, Category.class);
    }

    @Override
    public Category save(Category category) {

        operations.save(category);
        return category;
    }

    @Override
    public void remove(Category category) {
        operations.remove(category);
    }

    @Override
    public Category findByName(String name) {
        Query query = query(where("name").is(name));
        return operations.findOne(query, Category.class);
    }


}
