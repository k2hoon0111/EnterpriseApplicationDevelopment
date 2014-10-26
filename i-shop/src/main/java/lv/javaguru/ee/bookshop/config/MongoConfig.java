package lv.javaguru.ee.bookshop.config;

import com.mongodb.Mongo;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;


/**
 * Spring JavaConfig configuration class to setup a Spring container and infrastructure components like a
 * {@link javax.sql.DataSource}, a {@link EntityManagerFactory} and a {@link org.springframework.transaction.PlatformTransactionManager}.
 *
 * @author Oliver Gierke
 */
@Configuration
@ComponentScan(basePackages = {"lv.javaguru.ee.bookshop.core.domain.mongo"})
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    private List<Converter<?, ?>> converters;

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mongodb.config.AbstractMongoConfiguration#getDatabaseName()
     */
    @Override
    protected String getDatabaseName() {
        return "e-store";
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mongodb.config.AbstractMongoConfiguration#mongo()
     */
    @Override
    public Mongo mongo() throws Exception {

        Mongo mongo = new Mongo("178.62.248.4", 27017);
        mongo.setWriteConcern(WriteConcern.SAFE);

        return mongo;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mongodb.config.AbstractMongoConfiguration#customConversions()
     */
    @Override
    public CustomConversions customConversions() {
        return new CustomConversions(converters);
    }
}
