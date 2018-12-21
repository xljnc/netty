package com.wt.test.netty.concurrent;

import java.util.concurrent.*;

/**
 * @Auther: 埼玉
 * @Date: 2018/12/19 11:28
 * @Description:
 */
public class ForkJoinTest {

    private static final ForkJoinPool executors = new ForkJoinPool(Runtime.getRuntime().availableProcessors() * 2);

    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

    private long valve = 10000L;

    public static void main(String[] args) throws Exception {
//        pool.submit(new CalculateTask(0, 20000L));
        ForkJoinTask<Long> result = executors.submit(new ComputeTask(1, 10002));
        System.out.println(result.get());
    }


    static class ComputeTask extends RecursiveTask<Long> {
        long start;
        long end;
        long valve = 10000L;


        public ComputeTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            if (end - start < valve) {
                System.out.println("No need to fork sub tasks.");
                for (long i = start; i <= end; i++) {
                    sum += i;
                }

            } else {
                long mid = (start + end) / 2;
                ForkJoinTask<Long> left = executors.submit(new ComputeTask(start, mid));
                ForkJoinTask<Long> right = executors.submit(new ComputeTask(mid + 1, end));
                try {
                    sum = left.get() + left.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return sum;

        }

    }


    static class CalculateTask implements Runnable {
        long start;
        long end;
        long valve = 10000L;

        CalculateTask() {
        }

        public CalculateTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            long sum = 0;
            if (end - start < valve) {
                System.out.println("No need to fork sub tasks.");
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
                System.out.println(sum);

            } else {
                try {
                    long mid = (start + end) / 2;
                    Thread left = new Thread(new CalculateTask(start, mid), "left");
                    left.start();
                    left.join();
                    Thread right = new Thread(new CalculateTask(mid + 1, end), "right");
                    right.start();
                    right.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
