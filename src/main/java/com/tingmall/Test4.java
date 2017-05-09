package com.tingmall;

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.*;

import java.util.List;

/**
 * Created by lsx on 2017/4/14.
 */
public class Test4 {
    public static void main(String[] args) {
        //在本行按ctrl + alt + v 自动填充变量
        //  Cluster cluster=Cluster.builder().addContactPoint("192.168.1.108").build();
        Cluster cluster = null;
        Session session = null;

            cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
            session = cluster.connect();
            //QueryBuilder方式实现增删改查
        PreparedStatement preparedStatement=session.prepare("insert into testkeyspace1.student(name,age)values(?,?)");
        session.execute(preparedStatement.bind("yyy2",30));

        Select.Where where=QueryBuilder.select().all().from("testkeyspace1","student").where(QueryBuilder.eq("age",30));
        System.out.println(where);
        ResultSet resultSet=session.execute(where);
        List<Row> rows = resultSet.all();
        for (Row r : rows) {
            System.out.println("name=" + r.getString("name"));
            System.out.println("age=" + r.getInt("age"));
        }


    }
}
