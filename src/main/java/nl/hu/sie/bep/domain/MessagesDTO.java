package nl.hu.sie.bep.domain;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MessagesDTO {

    private List<Document> documents = new ArrayList<>();

    public List<Document> getDocuments()
    {
        return documents;
    }

    public void addDocument(Document document)
    {
        if(!documents.contains(document))
        {
            documents.add(document);
        }
    }
}
