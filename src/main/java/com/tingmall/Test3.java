package com.tingmall;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.*;

import java.util.List;

/**
 * Created by lsx on 2017/4/14.
 */
public class Test3 {
    public static void main(String[] args) {
        //在本行按ctrl + alt + v 自动填充变量
        //  Cluster cluster=Cluster.builder().addContactPoint("192.168.1.108").build();
        Cluster cluster = null;
        Session session = null;

            cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
            session = cluster.connect();
            //QueryBuilder方式实现增删改查
            Insert insert=QueryBuilder.insertInto("testkeyspace1","student").value("name","lsx").value("age",27);
            session.execute(insert);
            Select.Where where=QueryBuilder.select().all().from("testkeyspace1","student").where(QueryBuilder.eq("name","lsx"));
        ResultSet resultSet=session.execute(where);
        List<Row> rows = resultSet.all();
        for (Row r : rows) {
            System.out.println("name=" + r.getString("name"));
            System.out.println("age=" + r.getInt("age"));
        }
        Update.Where update=QueryBuilder.update("testkeyspace1","student").with(QueryBuilder.set("age",17)).where(QueryBuilder.eq("name","lsx"));
        //可以直接打印构建的cql语句
        session.execute(update);
        resultSet=session.execute(where);
        rows = resultSet.all();
        for (Row r : rows) {
            System.out.println("name=" + r.getString("name"));
            System.out.println("age=" + r.getInt("age"));
        }
        Delete.Where delete=QueryBuilder.delete().from("testkeyspace1","student").where(QueryBuilder.eq("name","lsx"));
        session.execute(delete);
        resultSet=session.execute(where);
        rows = resultSet.all();
        for (Row r : rows) {
            System.out.println("name=" + r.getString("name"));
            System.out.println("age=" + r.getInt("age"));
        }

    }
}
