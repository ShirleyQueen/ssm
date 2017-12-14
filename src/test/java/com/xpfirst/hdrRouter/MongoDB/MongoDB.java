package com.xpfirst.hdrRouter.MongoDB;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
/**
 * Copyright (C) 北京学信科技有限公司
 *
 * @Des:
 * @Author: Gaojindan
 * @Create: 2017/12/11 下午4:26
 **/
public class MongoDB {

    @Test
    public void mongodb(){
        MongoClientURI connectionString = new MongoClientURI("mongodb://gaojindan:gaojindan001@121.40.180.70:27017/gaojindandb");
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase("gaojindandb");
        MongoCollection<Document> collection = database.getCollection("gaojindan");

//        添加
//        Document doc = new Document("name", "MongoDB")
//                .append("type", "database")
//                .append("count", 1)
//                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
//                .append("info", new Document("x", 203).append("y", 102));
//
//        collection.insertOne(doc);

//        添加多个
//        List<Document> documents = new ArrayList<Document>();
//        for (int i = 0; i < 100; i++) {
//            documents.add(new Document("i", i));
//        }
//        collection.insertMany(documents);

//        查询第一个
//            Document myDoc = collection.find().first();
//            System.out.println(myDoc.toJson());

//        查询全部
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }



    }
}
