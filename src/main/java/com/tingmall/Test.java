package com.tingmall;

import com.codahale.metrics.Metric;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Metadata;

import java.util.List;
import java.util.Set;

/**
 * Created by lsx on 2017/4/14.
 */
public class Test {
    //1报错找不到类com/google/common/collect/ImmutableMap，我从maven下载了guava1.90
    //2使用ip报错找不到host
  public static void main(String[] args){
      //在本行按ctrl + alt + v 自动填充变量
    //  Cluster cluster=Cluster.builder().addContactPoint("192.168.1.108").build();
      Cluster cluster=Cluster.builder().addContactPoint("127.0.0.1").build();
      String clustorname=cluster.getClusterName();
      Metadata metadata=cluster.getMetadata();
      Set<Host> hosts=metadata.getAllHosts();
      List<KeyspaceMetadata> keyspaces = metadata.getKeyspaces();
      for(Host h:hosts){
          System.out.println(h.getAddress());
      }
      for(KeyspaceMetadata k:keyspaces){
          System.out.println(k.getName());
      }
      cluster.close();



  }
}
