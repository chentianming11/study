package com.chen.study.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;

import java.io.IOException;

public class CreateGroup extends ConnectionWatcher{

    
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        CreateGroup createGroup = new CreateGroup();
        createGroup.connect("127.0.0.1:2181");
        createGroup.create("myGroup");
        createGroup.close();
    }


    private void create(String groupName) throws KeeperException, InterruptedException {
        String path="/"+groupName;
        if(zk.exists(path, false)== null){
            zk.create(path, null/*data*/, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println("Created:"+path);
    }
}