package nl.hu.sie.bep.friendspammer;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoSaver {

	static final Logger logger = LoggerFactory.getLogger(MongoSaver.class);

	private MongoSaver()
	{

	}

	public static boolean saveEmail(String to, String from, String subject, String text, Boolean html) {
		String userName = "AtillaBosma";
		String password = "Welkom123";
		String database = "cluster0";


		MongoClientURI uri = new MongoClientURI(
				"mongodb+srv://" + userName + ":" + password + "@cluster0-z2xmc.azure.mongodb.net/test?retryWrites=true");

		boolean success = true;
		
		try (MongoClient mongoClient = new MongoClient(uri)) {
			
			MongoDatabase db = mongoClient.getDatabase( database );
			
			MongoCollection<Document> c = db.getCollection("email");
			
			Document  doc = new Document ("to", to)
			        .append("from", from)
			        .append("subject", subject)
			        .append("text", text)
			        .append("asHtml", html);
			c.insertOne(doc);
		} catch (MongoException mongoException) {
			logger.error("Error while saving to Mongo");
			mongoException.printStackTrace();
			success = false;
		}
		
		return success;
 		
	}

}
