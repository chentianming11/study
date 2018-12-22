package com.chen.study.concurrent.concurrent3.juc.util.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 陈添明
 * @date 2018/11/4
 */
public class CountDownLatchExample4 {

    private static Random random = new Random(System.currentTimeMillis());

    static class Event {
        int id;

        public Event(int id) {
            this.id = id;
        }
    }

    interface Watcher {
//        void startWatch();
        void done(Table table);
    }

    static class TaskBatch implements Watcher {

        private CountDownLatch countDownLatch;

        private final TaskGroup taskGroup;

        public TaskBatch(int size, TaskGroup taskGroup){
            this.countDownLatch = new CountDownLatch(size);
            this.taskGroup = taskGroup;
        }


        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0){
                System.out.println("the table:" + table.tableName + " finish work");
                taskGroup.done(table);
            }
        }
    }

    static class TaskGroup implements Watcher {

        private CountDownLatch countDownLatch;

        private final Event event;

        public TaskGroup(int size, Event event){
            this.countDownLatch = new CountDownLatch(size);
            this.event = event;
        }


        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0){
                System.out.println("all table have finished in event-" + event.id);
            }
        }
    }


    static class Table {
        String tableName;
        long sourceRecordCount = 10L;
        String sourceColumnSchema = "<table name='a'><column name='coll' type='varchar2' /></table>";
        long targetCount;
        String targetColumnSchema = "";

        public Table(String tableName, long sourceRecordCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourceRecordCount=" + sourceRecordCount +
                    ", sourceColumnSchema='" + sourceColumnSchema + '\'' +
                    ", targetCount=" + targetCount +
                    ", targetColumnSchema='" + targetColumnSchema + '\'' +
                    '}';
        }
    }

    private static List<Table> capture(Event event){
        ArrayList<Table> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Table("table-" + event.id + "-" +  i, i*1000));
        }
        return list;
    }

    public static void main(String[] args) {
        Event[] events = {new Event(1), new Event(2)};
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (Event event : events) {
            List<Table> tableList = capture(event);
            TaskGroup taskGroup = new TaskGroup(tableList.size(), event);
            for (Table table : tableList) {
                TaskBatch taskBatch = new TaskBatch(2, taskGroup);
                executorService.submit(new TrustResourceRecordCount(table, taskBatch));
                executorService.submit(new TrustResourceColumns(table, taskBatch));
            }
        }

    }

    private static class TrustResourceRecordCount implements Runnable{
        private final Table table ;
        private final  TaskBatch taskBatch;
        public TrustResourceRecordCount(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(1_000));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetCount = table.sourceRecordCount;
//            System.out.println("the table:" + table.tableName + " target RecordCount and update db done");
            taskBatch.done(table);
        }
    }

    private static class TrustResourceColumns implements Runnable{
        private final Table table ;
        private final  TaskBatch taskBatch;
        public TrustResourceColumns(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(1_000));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetColumnSchema = table.sourceColumnSchema;
//            System.out.println("the table:" + table.tableName + " target column and update db done...");
            taskBatch.done(table);
        }
    }
}
