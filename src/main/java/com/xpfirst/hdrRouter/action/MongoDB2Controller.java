package com.xpfirst.hdrRouter.action;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




/**
 * Copyright (C) 北京学信科技有限公司
 *
 * @Des:
 * @Author: Gaojindan
 * @Create: 2017/12/14 下午4:53
 **/

@Controller
@RequestMapping(value = "mongodb2")
public class MongoDB2Controller {

    private MongoClientURI connectionString = new MongoClientURI("mongodb://devuser:devpassword@121.40.180.70:27017/devmongodb");
    private MongoClient mongoClient = new MongoClient(connectionString);
    private MongoDatabase database = mongoClient.getDatabase("gaojindandb");
    private MongoCollection<Document> paperColl = database.getCollection("xsl_paper");
    private MongoCollection<Document> studentColl = database.getCollection("xsl_student");
    private MongoCollection<Document> recordsColl = database.getCollection("xsl_records");
//    private MongoCollection<Document> scoreColl = database.getCollection("xsl_score");


    String[] students = {"100001","100002","100003","100004"};

    String[] studentNames = {"郜1","郜2","郜3","郜4"};

    //        添加4个试题的正确答案
    String[] answers = {"a","b","c","d","e"};
    //    试卷ID
    private int paperID = 1;

    @RequestMapping(value = "/")
    public ModelAndView index(ModelMap modelMap){
        modelMap.put("title","mongoDB操作");
        return new ModelAndView("mongodb/index");
    }
    /**
     * @Author: Gaojindan
     * @Create: 2017/12/12 下午2:20
     * @Des: 试卷操作类,添加4条记录
     * @Param:
     * @Return:
     */
    @RequestMapping(value = "paper")
    public ModelAndView paper(ModelMap modelMap){
        modelMap.put("title","试卷操作");

        //设置为无效
//        paperColl.updateMany(eq("paperID", paperID), new Document("$set", new Document("isEffective", false)));
        paperColl.deleteMany(new Document());


        List<Document> docList = new ArrayList<Document>();
        for (int j = 0; j < answers.length; j++) {
            docList.add(new Document("paperID", paperID)
                    .append("paperNum",j+1)
                    .append("paperAnswer",answers[j]));
        }
        paperColl.insertMany(docList);

        modelMap.put("text","添加了4条试卷记录");
        return new ModelAndView("mongodb/hello");
    }
    /**
     * @Author: Gaojindan
     * @Create: 2017/12/13 上午10:01
     * @Des: 试卷信息
     * @Param:
     * @Return:
     */
    @RequestMapping(value = "paperinfo")
    public ModelAndView paperinfo(ModelMap modelMap){
        modelMap.put("title","试卷信息");

        BasicDBObject doc5 = new BasicDBObject();
        doc5.put("paperID", paperID);


        //读取试卷信息
        List<Map> infoList = new ArrayList<>();
        MongoCursor<Document> cursor = paperColl.find(doc5).iterator();
        try {
            while (cursor.hasNext()) {
                String tmpStr = cursor.next().toJson();
                System.out.println(tmpStr);
                JSONObject jObject = JSON.parseObject(tmpStr);
                infoList.add(jObject);
            }
        } finally {
            cursor.close();
        }
        modelMap.put("infoList",infoList);

        return new ModelAndView("mongodb/info");
    }


}
