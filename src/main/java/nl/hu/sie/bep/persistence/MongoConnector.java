package nl.hu.sie.bep.persistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import io.github.cdimascio.dotenv.Dotenv;

public class MongoConnector {

    private static Dotenv dotenv = Dotenv.load();

    private MongoConnector()
    {

    }

    public static MongoClient getConnectionClient()
    {
        String userName = dotenv.get("MONGO_USERNAME");
        String password = dotenv.get("MONGO_PASSWORD");
        String database = dotenv.get("MONGO_DATABASE");

        MongoClientURI uri = new MongoClientURI(
                String.format("mongodb+srv://%s:%s@%s.azure.mongodb.net/test?retryWrites=true", userName, password, database));

        MongoClient mongoClient = new MongoClient(uri);

        return mongoClient;
    }

}
