package nl.hu.sie.bep.friendspammer;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import nl.hu.sie.bep.domain.MessagesDTO;
import nl.hu.sie.bep.persistence.MongoConnector;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class MongoSaver {

	private static final Logger logger = LoggerFactory.getLogger(MongoSaver.class);

	private static Dotenv dotenv = Dotenv.load();

	private MongoSaver()
	{

	}

	public static boolean saveEmail(String to, String from, String subject, String text, Boolean html) {
		String mongo_d = dotenv.get("MONGO_DATABASE");

		boolean success = true;

		try (MongoClient mongoClient = MongoConnector.getConnectionClient()) {
			
			MongoDatabase db = mongoClient.getDatabase(mongo_d);
			
			MongoCollection<Document> c = db.getCollection("email");
			
			Document  doc = new Document ("to", to)
			        .append("from", from)
			        .append("subject", subject)
			        .append("text", text)
			        .append("asHtml", html);
			c.insertOne(doc);
		} catch (MongoException mongoException) {
			logger.error("Error while saving to Mongo, error: \n{0}", mongoException);
			success = false;
		}
		
		return success;
 		
	}

	public static MessagesDTO getAllMessages()
	{
		String mongo_u = dotenv.get("MONGO_USERNAME_2");
		String mongo_p = dotenv.get("MONGO_PASSWORD_2");
		String mongo_d = dotenv.get("MONGO_DATABASE_2");

		MongoCredential credential = MongoCredential.createCredential(mongo_u, mongo_d, mongo_p.toCharArray());

		MongoClient mongoClient = new MongoClient(new ServerAddress("ds227939.mlab.com", 27939), credential, MongoClientOptions.builder().build());

		MongoDatabase db = mongoClient.getDatabase(mongo_d);

		MongoCollection<Document> c = db.getCollection("email");

		Iterator<Document> it = c.find().iterator();

		MessagesDTO messages = new MessagesDTO();

		while(it.hasNext()) {
			Document email = it.next();

			messages.addDocument(email);
		}

		if(!messages.getDocuments().isEmpty())
		{
			mongoClient.close();

			logger.info("Messages retrieved");

			return messages;
		}
		else
		{
			mongoClient.close();

			logger.error("No messages found");

			return null;
		}
	}
}
