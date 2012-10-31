package jp.fkmsoft.sample.asynctask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private static final int CORE_POOL_SIZE = 2;
    private static final int MAXIMUM_POOL_SIZE = 5;
    private static final long KEEP_ALIVE = 10;
    private BlockingQueue<Runnable> sWorkQueue = new ArrayBlockingQueue<Runnable>(10);
    private ThreadFactory sThreadFactory= new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
        }
    };
    
    ThreadPoolExecutor executor1 = new ThreadPoolExecutor(CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, sWorkQueue , sThreadFactory);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start 3 task
                new MyTask().execute(1 * 1000);
                new MyTask().execute(3 * 1000);
                new MyTask().executeOnExecutor(executor1, 2 * 1000);
            }
        };
        
        View button = findViewById(R.id.button_start);
        button.setOnClickListener(listener);
    }

}
