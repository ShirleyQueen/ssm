package com.xpfirst.hdrRouter.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.*;

/**
 * Copyright (C) 北京学信科技有限公司
 *
 * @Des:
 * @Author: Gaojindan
 * @Create: 2017/12/12 下午1:56
 **/

@Controller
@RequestMapping(value = "mongodb")
public class MongoDBController {

    private MongoClientURI connectionString = new MongoClientURI("mongodb://gaojindan:gaojindan001@121.40.180.70:27017/gaojindandb");
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



    /**
    * @Author: Gaojindan
    * @Create: 2017/12/12 下午2:35
    * @Des: 学生信息操作
    * @Param:
    * @Return:
    */
    @RequestMapping(value = "student")
    public ModelAndView student(ModelMap modelMap){
        modelMap.put("title","学生信息操作");

        //设置为无效
//        for (int i = 0; i < students.length; i++) {
//            studentColl.updateMany(eq("studentID", students[i]), new Document("$set", new Document("isEffective", false)));
//        }
        studentColl.deleteMany(new Document());

//        添加4个学生
        List<Document> documents = new ArrayList<Document>();
        for (int i = 0; i < students.length; i++) {
            documents.add(new Document("studentID", students[i])
                    .append("studentName",studentNames[i]));
        }
        studentColl.insertMany(documents);

        modelMap.put("text","添加了4条学生记录");
        return new ModelAndView("mongodb/hello");
    }

    /**
     * @Author: Gaojindan
     * @Create: 2017/12/12 下午2:35
     * @Des: 学生答题记录操作
     * @Param:
     * @Return:
     */
    @RequestMapping(value = "records")
    public ModelAndView records(ModelMap modelMap){
        modelMap.put("title","答题记录操作");

        //答题记录
        int recordsID = 1;
        List<Document> recordList = new ArrayList<Document>();
        //成绩
        int scoreID = 1;
        for (int i = 0; i < students.length; i++) {
            String studentID = students[i];

            Document tmpDoc = new Document("recordsID", recordsID)
                    .append("paperID",paperID)
                    .append("studentID",studentID);

            recordsColl.deleteMany(new Document());
//            scoreColl.deleteMany(new Document());

            int totalScore = 0;
            int score = 2;
            List<Document> recordsInfo = new ArrayList<Document>();
            for (int j = 0; j < answers.length; j++) {
                //添加答题记录
                recordsInfo.add(new Document("paperNum",j+1)
                        .append("answer","b")
                        .append("score",score));
                score++;
                totalScore += score;
                recordsID++;
            }
            tmpDoc.append("recordsInfo",recordsInfo);
            tmpDoc.append("totalScore",totalScore);
            //添加成绩
            recordList.add(tmpDoc);
            scoreID++;
        }
        recordsColl.insertMany(recordList);

        modelMap.put("text","添加了"+recordsID+"条答题记录,和"+scoreID+"条成绩");
        return new ModelAndView("mongodb/hello");
    }
    /**
    * @Author: Gaojindan
    * @Create: 2017/12/13 上午11:45
    * @Des: 查询记录列表
    * @Param:
    * @Return:
    */
    @RequestMapping(value = "recordslist")
    public ModelAndView recordslist(ModelMap modelMap){
        modelMap.put("title","查询记录列表");

        BasicDBObject doc5 = new BasicDBObject();
        doc5.put("paperID", paperID);


        //读取试卷信息
        List<JSONObject> recordslist = new ArrayList<>();
        MongoCursor<Document> cursor = recordsColl.find(doc5).iterator();
        try {
            while (cursor.hasNext()) {
                //计算正确答案
                Document nextDoc = cursor.next();
                Integer paperID = nextDoc.getInteger("paperID");
                List<Document> recordsInfo = (List<Document>) nextDoc.get("recordsInfo");
                for (Document info : recordsInfo){
                    //查询正确的答案
                    Bson var1 = and(eq("paperID", paperID), eq("paperNum", info.getInteger("paperNum")));
                    Document tmpDoc = paperColl.find(var1).first();

                    info.append("paperAnswer",tmpDoc.getString("paperAnswer"));
                }
                nextDoc.append("recordsInfo",recordsInfo);
                String tmpStr = nextDoc.toJson();
                System.out.println(tmpStr);
                JSONObject jObject = JSON.parseObject(tmpStr);
                recordslist.add(jObject);
            }
        } finally {
            cursor.close();
        }
        modelMap.put("recordslist",recordslist);

        return new ModelAndView("mongodb/info");
    }

    /**
    * @Author: Gaojindan
    * @Create: 2017/12/13 下午1:16
    * @Des: 查询成绩
    * @Param:
    * @Return:
    */
    @RequestMapping(value = "scorelist")
    public ModelAndView scorelist(ModelMap modelMap,
                                  @RequestParam(required = false) String studentName,
                                  @RequestParam(required = false) Integer startScore,
                                  @RequestParam(required = false) Integer endScore){
        modelMap.put("title","查询记录列表");
        MongoCursor<Document> cursor = null;
        Bson var1 = null;
        //按分数段查询成绩查
        if (startScore != null && endScore != null){
            var1 = and(eq("paperID", paperID), gt("totalScore", startScore), lte("totalScore", endScore));
        }
        else if (studentName != null) {
            //按姓名查询
            Bson tmpbson = and(eq("studentName", studentName));
            Document tmpDoc = studentColl.find(tmpbson).first();
            String studentID = tmpDoc.getString("studentID");
            var1 = and(eq("paperID", paperID),eq("studentID", studentID));
        }
        else{
            //查询所有
            var1 = and(eq("paperID", paperID));

        }
        cursor = recordsColl.find(var1).iterator();


        //读取试卷信息
        List<JSONObject> scorelist = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                String tmpStr = cursor.next().toJson();
                System.out.println(tmpStr);
                JSONObject jObject = JSON.parseObject(tmpStr);
                scorelist.add(jObject);
            }
        } finally {
            cursor.close();
        }
        modelMap.put("scorelist",scorelist);

        return new ModelAndView("mongodb/info");
    }

    /**
    * @Author: Gaojindan
    * @Create: 2017/12/13 下午4:18
    * @Des: 修改总成绩
    * @Param:
    * @Return:
    */
    @RequestMapping(value = "scoreedit")
    public ModelAndView scoreedit(ModelMap modelMap,
                                  @RequestParam(required = true) Integer recordsID,
                                  @RequestParam(required = true) Integer totalScore){
        modelMap.put("title","修改总成绩");

        recordsColl.updateOne(eq("recordsID", recordsID), new Document("$set", new Document("totalScore", totalScore)));


        modelMap.put("text","修改成功了");

        return new ModelAndView("mongodb/info");
    }

    /**
     * @Author: Gaojindan
     * @Create: 2017/12/13 下午4:18
     * @Des: 删除总成绩
     * @Param:
     * @Return:
     */
    @RequestMapping(value = "scoredelete")
    public ModelAndView scoredelete(ModelMap modelMap,
                                  @RequestParam(required = true) Integer recordsID){
        modelMap.put("title","删除总成绩");

        recordsColl.deleteOne(eq("recordsID", recordsID));


        modelMap.put("text","删除成功了");

        return new ModelAndView("mongodb/info");
    }
}
