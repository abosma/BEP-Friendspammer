package nl.hu.sie.bep.domain;

import org.bson.Document;

import java.util.ArrayList;

public class MessagesDTO {

    public ArrayList<Document> documents = new ArrayList<>();

    public void addDocument(Document document)
    {
        if(!documents.contains(document))
        {
            documents.add(document);
        }
    }
}
