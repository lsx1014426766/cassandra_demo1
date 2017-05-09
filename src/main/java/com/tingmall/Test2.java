package com.tingmall;

import com.datastax.driver.core.*;

import java.util.List;
import java.util.Set;

/**
 * Created by lsx on 2017/4/14.
 */
public class Test2 {

  public static void main(String[] args) {
      //在本行按ctrl + alt + v 自动填充变量
      //  Cluster cluster=Cluster.builder().addContactPoint("192.168.1.108").build();
      Cluster cluster = null;
      Session session = null;
      try {
          cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
          session = cluster.connect();
          String createkeyspacecql = "create keyspace if not exists testkeyspace1 with replication={'class':'SimpleStrategy','replication_factor':1}";
          session.execute(createkeyspacecql);
          String createtablecql = "create table if not exists testkeyspace1.student(name varchar primary key,age int)";
          session.execute(createtablecql);
          String insertcql = "insert into testkeyspace1.student(name,age)values('zhangsan',20)";
          session.execute(insertcql);
          String querycql = "select * from testkeyspace1.student";
          ResultSet resultSet = session.execute(querycql);
          List<Row> rows = resultSet.all();
          for (Row r : rows) {
              System.out.println("name=" + r.getString("name"));
              System.out.println("age=" + r.getInt("age"));
          }
          String updatecql = "update testkeyspace1.student set age=22 where name='zhangsan'";
          session.execute(updatecql);
          resultSet = session.execute(querycql);
          rows = resultSet.all();
          for (Row r : rows) {
              System.out.println("name=" + r.getString("name"));
              System.out.println("age=" + r.getInt("age"));
          }
          String deletecql = "delete from testkeyspace1.student where name='zhangsan'";
          session.execute(deletecql);
          resultSet = session.execute(querycql);
          rows = resultSet.all();
          for (Row r : rows) {
              System.out.println("name=" + r.getString("name"));
              System.out.println("age=" + r.getInt("age"));
          }
      } catch (Exception e) {
          e.printStackTrace();
      } finally {

          session.close();
          cluster.close();

      }

  }
}
