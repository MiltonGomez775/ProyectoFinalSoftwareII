package edu.co.uniquindio.demo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "ProyectoFinalHexas";
    }

    @Bean
    @Override
    public MongoClient mongoClient() {
        // ✅ Asegúrate de tener este formato de cadena
        String uri = "mongodb+srv://miltoncgomezr_db_user:ZOs0E291RZYgfmbL@cluster0.etwacx0.mongodb.net/ProyectoFinalHexas?retryWrites=true&w=majority&tls=true&ssl=true";

        ConnectionString connectionString = new ConnectionString(uri);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .applyToSslSettings(builder -> builder.enabled(true))
                .build();

        return MongoClients.create(settings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }
}
