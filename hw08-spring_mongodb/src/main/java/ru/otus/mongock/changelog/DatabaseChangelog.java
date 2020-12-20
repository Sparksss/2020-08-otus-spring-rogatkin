package ru.otus.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/*
 * @created 20/12 - otus-spring
 * @author Ilya Rogatkin
 */
@ChangeLog
public class DatabaseChangelog {
    @ChangeSet(order = "001", id = "dropDb", author = "ilya", runAlways = true)
    public void dropDb(MongoDatabase db) {db.drop();}

    @ChangeSet(order = "002", id = "insertBook", author = "ilya")
    public void insertBook(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("books");
        Document doc = new Document().append("name", "Freedom");
        myCollection.insertOne(doc);
    }
}
