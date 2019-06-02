package com.chen.study.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.List;

public class DeleteGroup extends ConnectionWatcher{
    public void delete(String groupName) throws InterruptedException, KeeperException {
        String path="/"+groupName;
        List<String> children;
        try {
            children = zk.getChildren(path, false);
            for(String child:children){
                zk.delete(path+"/"+child, -1);            
            }
            zk.delete(path, -1);
        } catch (KeeperException.NoNodeException e) {
            System.out.printf("Group %s does not exist\n", groupName);
            System.exit(1);
        }    
    }
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connect("127.0.0.1:2181");
        deleteGroup.delete("myGroup");
        deleteGroup.close();
    }
}