package com.yangle.web.test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * programname: product_factory
 * <p>
 * title: 阻塞队列测试
 *
 * @author: yishao
 * <p>
 * created: 2018-11-02 11:39
 **/
public class BlockQueueTest {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);

        Consumer consumer = new Consumer(queue);
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(producer1);
        executorService.execute(producer2);
        executorService.execute(producer3);
        executorService.execute(consumer);


        Thread.sleep(10000);

        producer1.stop();
        producer2.stop();
        producer3.stop();

        Thread.sleep(2000);

        executorService.shutdown();

    }

}

/**
 * 消费者
 */
class Consumer implements Runnable{

    private BlockingQueue<String> queue;
    private static final  int DEFAULT_RANGE_FOR_SLEEP = 1000;
    private volatile boolean isRunning = true;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        System.out.println("启动消费者线程...");
        try {
            while (isRunning){

                System.out.println("正在从队列获取数据...");
                String data = queue.poll(2, TimeUnit.SECONDS);

                if(data!=null){
                    System.out.println("正在消费数据："+data);
                    Thread.sleep(new Random().nextInt(DEFAULT_RANGE_FOR_SLEEP));
                }else {
                    isRunning = false;
                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }finally {
            System.out.println("退出消费者线程");
        }

    }

    public void stop(){
        isRunning = false;
    }
}

class Producer implements Runnable{

    private volatile boolean isRunning = true;
    private BlockingQueue queue;
    private static AtomicInteger count = new AtomicInteger();
    private static final  int DEFAULT_RANGE_FOR_SLEEP = 1000;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        System.out.println("启动生成者线程...");
        try {
            while (isRunning){
                System.out.println("正在生成数据...");
                Thread.sleep(new Random().nextInt(DEFAULT_RANGE_FOR_SLEEP));
                String data = "data:"+count.incrementAndGet();

                if(queue.offer(data,2,TimeUnit.SECONDS)){
                    System.out.println("将数据："+data+" 成功放入队列...");
                }else {
                    System.out.println("====================放入数据失败:"+data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }finally {
            System.out.println("退出生产者线程。");
        }
    }

    public void stop(){
        isRunning = false;
    }
}
