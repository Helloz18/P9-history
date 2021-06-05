package config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DatabaseConfig {

    //à mettre dans le fichier de config
    private String dbUri = "mongodb://localhost:27017";

    public MongoClient mongoClient() {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        return MongoClients.create(MongoClientSettings.builder()
                                                      .applyConnectionString(new ConnectionString(dbUri))
                                                      .codecRegistry(codecRegistry)
                                                      .build());
    }

    // remplacée par la méthode mongoClient() qui permet d'utiliser codecRegistry pour utiliser des objets Java.
    // public MongoClient connection() {
    //     try {
    //         MongoClient client = MongoClients.create(dbUri);	
    //         return client;
    //     } catch(Exception e) {
    //         e.printStackTrace();
    //         return null;
    //     }	
    // }

    public MongoDatabase getDatabase(MongoClient client) {
        return client.getDatabase("my_database");
    }
    
    public MongoCollection<Document> test(MongoDatabase database) {
        MongoCollection<Document> tests = database.getCollection("tests");
        return tests;      
    }

   

}
