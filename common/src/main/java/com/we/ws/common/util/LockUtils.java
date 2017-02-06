package com.we.ws.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by twogoods on 16/10/14.
 */
public class LockUtils {

    private static Logger log= LoggerFactory.getLogger(LockUtils.class);

    private static ConcurrentHashMap<String, LockDelay> map = new ConcurrentHashMap<>();

    private static ExecutorService executor= Executors.newFixedThreadPool(20);

    private static LockDelay get(String key){
        return map.get(key);
    }

    /**
     *
     * @param key
     * @param delayTime 延迟释放的时间(毫秒)
     * @return
     */
    public static boolean tryLock(String key, long delayTime) {
        log.debug("before trylock:{}",map);
        LockDelay delay = map.putIfAbsent(key, new LockDelay(System.currentTimeMillis(),delayTime));
        return delay == null;
    }

    public static void relaseLock(String key) {
        map.remove(key);
    }


    public static void asyncrelaseLock(String key){
        if(map.containsKey(key)){
            executor.execute(new RelaselockDelay(key));
        }
    }

    private static class RelaselockDelay implements Runnable {
        private String key;

        public RelaselockDelay(String key) {
            this.key = key;
        }

        @Override
        public void run() {
            try {
                LockDelay lockDelay=LockUtils.get(key);
                if(lockDelay==null){
                    return;
                }
                long now = System.currentTimeMillis();
                while (now - lockDelay.getNow() < lockDelay.getDelay()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        //ignore
                    }
                    now = System.currentTimeMillis();
                }
            }finally {
                LockUtils.relaseLock(key);
            }
        }
    }


    private static class LockDelay {
        private long now;
        private long delay;

        public LockDelay() {
        }

        public LockDelay(long now, long delay) {
            this.now = now;
            this.delay = delay;
        }

        public long getNow() {
            return now;
        }

        public void setNow(long now) {
            this.now = now;
        }

        public long getDelay() {
            return delay;
        }

        public void setDelay(long delay) {
            this.delay = delay;
        }
    }
}
