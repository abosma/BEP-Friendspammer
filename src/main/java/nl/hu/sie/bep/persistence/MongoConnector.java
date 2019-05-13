package nl.hu.sie.bep.persistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoConnector {

    private MongoConnector()
    {

    }

    public static MongoClient getConnectionClient()
    {
        String userName = "AtillaBosma";
        String password = "Welkom123";
        String database = "cluster0-z2xmc";

        MongoClientURI uri = new MongoClientURI(
                String.format("mongodb+srv://%s:%s@%s.azure.mongodb.net/test?retryWrites=true", userName, password, database));

        MongoClient mongoClient = new MongoClient(uri);

        return mongoClient;
    }

}
