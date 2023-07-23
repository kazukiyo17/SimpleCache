import com.xyunli.cache.manager.impl.CacheManagerImpl;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestCache {

    /**
     * 测试缓存和缓存失效
     */
    @Test
    public void testCacheManager() {
        CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
        cacheManagerImpl.put("test", "test", 10 * 1000L);
        cacheManagerImpl.put("myTest", "myTest", 15 * 1000L);
        log.info("test:" + cacheManagerImpl.get("test"));
        log.info("myTest:" + cacheManagerImpl.get("myTest"));
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("test:" + cacheManagerImpl.get("test"));
        log.info("myTest:" + cacheManagerImpl.get("myTest"));
    }

    /**
     * 测试线程安全
     */
    @Test
    public void testThredSafe() {
        final String key = "thread";
        final CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            exec.execute(new Runnable() {
                public void run() {
                    if (!cacheManagerImpl.contains(key)) {
                        cacheManagerImpl.put(key, 1, 0);
                    } else {
                        //因为+1和赋值操作不是原子性的，所以把它用synchronize块包起来
                        synchronized (cacheManagerImpl) {
                            int value = (Integer) cacheManagerImpl.get(key) + 1;
                            cacheManagerImpl.put(key,value , 0);
                        }
                    }
                }
            });
        }
        exec.shutdown();
        try {
            exec.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        log.info(cacheManagerImpl.get(key).toString());
    }
}
