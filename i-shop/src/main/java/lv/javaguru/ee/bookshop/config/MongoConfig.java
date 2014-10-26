package lv.javaguru.ee.bookshop.config;

import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;


/**
 * Spring JavaConfig configuration class to setup a Spring container and infrastructure components like a
 * {@link javax.sql.DataSource}, a {@link EntityManagerFactory} and a {@link org.springframework.transaction.PlatformTransactionManager}.
 *
 * @author Oliver Gierke
 */
@Configuration
@ComponentScan(basePackages = {"lv.javaguru.ee.bookshop.core.database.mongo"})
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration {

//    @Autowired
//    private List<Converter<?, ?>> converters;

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mongodb.config.AbstractMongoConfiguration#getDatabaseName()
     */
    @Override
    protected String getDatabaseName() {
        return "i-shop";
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mongodb.config.AbstractMongoConfiguration#mongo()
     */
    @Override
    public Mongo mongo() throws Exception {

        MongoCredential credential = MongoCredential.createMongoCRCredential("admin", "admin", "JavaGuru".toCharArray());

        List<ServerAddress> serverAddresses = Arrays.asList(
                new ServerAddress("178.62.248.4", 27017),
                new ServerAddress("178.62.247.188", 27017),
                new ServerAddress("178.62.247.189", 27017)
        );

        MongoClient mongoClient = new MongoClient(
                serverAddresses,
                Arrays.asList(credential));

//        mongoClient.setWriteConcern(WriteConcern.SAFE);

        return mongoClient;
    }
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.mongodb.config.AbstractMongoConfiguration#customConversions()
//     */
//    @Override
//    public CustomConversions customConversions() {
//        return new CustomConversions(converters);
//    }
}
