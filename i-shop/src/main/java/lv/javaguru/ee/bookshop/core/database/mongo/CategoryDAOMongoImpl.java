package lv.javaguru.ee.bookshop.core.database.mongo;

import com.google.gson.Gson;
import com.mongodb.*;
import lv.javaguru.ee.bookshop.core.database.CategoryDAO;
import lv.javaguru.ee.bookshop.core.domain.Category;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MumboJumbo on 18/10/14.
 */
//@Component
public class CategoryDAOMongoImpl implements CategoryDAO {

    Gson gson = new Gson();

    @Override
    public void create(Category category) {

        MongoClient mongoClient = null;
        try {
            mongoClient = getMongoClient();
            DB db = mongoClient.getDB("Nikolay");

            DBCollection categories = db.getCollection("categories");

            BasicDBObject basicDBObject = new BasicDBObject("categoryId", category.getCategoryId())
                    .append("name", category.getName());
            categories.insert(basicDBObject);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();

        }

    }

    @Override
    public Category getById(Long categoryId) {
        Category category = null;

        MongoClient mongoClient = null;
        try {
            mongoClient = getMongoClient();
            DB db = mongoClient.getDB("Nikolay");

            DBCollection categories = db.getCollection("categories");

            BasicDBObject query = new BasicDBObject("categoryId", categoryId);

            DBCursor cursor = categories.find(query);

            if (cursor.hasNext()) {
                category = gson.fromJson(cursor.next().toString(), Category.class);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();

        }
        return category;
    }

    @Override
    public void update(Category category) {
        MongoClient mongoClient = null;
        try {
            mongoClient = getMongoClient();
            DB db = mongoClient.getDB("Nikolay");

            DBCollection categories = db.getCollection("categories");

            BasicDBObject searchQuery = new BasicDBObject("categoryId", category.getCategoryId());
            BasicDBObject basicDBObject = new BasicDBObject("categoryId", category.getCategoryId())
                    .append("name", category.getName());

            categories.findAndModify(searchQuery, basicDBObject);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();

        }
    }

    @Override
    public void delete(Category category) {
        MongoClient mongoClient = null;
        try {
            mongoClient = getMongoClient();
            DB db = mongoClient.getDB("Nikolay");

            DBCollection categories = db.getCollection("categories");

            BasicDBObject query = new BasicDBObject("categoryId", category.getCategoryId())
                    .append("name", category.getName());
            categories.findAndRemove(query);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();

        }

    }

    @Override
    public List<Category> getAll() {
        List<Category> categoryList = new ArrayList<Category>();
        ;
        MongoClient mongoClient = null;
        try {
            mongoClient = getMongoClient();
            DB db = mongoClient.getDB("Nikolay");

            DBCollection categories = db.getCollection("categories");

            DBCursor cursor = categories.find();

            try {
                while (cursor.hasNext()) {
                    DBObject categoryJSON = cursor.next();
                    categoryList.add(gson.fromJson(categoryJSON.toString(), Category.class));
                }
            } finally {
                cursor.close();
            }

        } catch (
                UnknownHostException e
                )

        {
            e.printStackTrace();
        } finally

        {
            mongoClient.close();
        }

        return categoryList;
    }

    private static MongoClient getMongoClient() throws UnknownHostException {
        MongoCredential credential = MongoCredential.createMongoCRCredential("admin", "admin", "JavaGuru".toCharArray());

        List<ServerAddress> serverAddresses = Arrays.asList(
                new ServerAddress("178.62.248.4", 27017),
                new ServerAddress("178.62.247.188", 27017),
                new ServerAddress("178.62.247.189", 27017)
        );
        return new MongoClient(
                serverAddresses,
                Arrays.asList(credential)
        );
    }
}
