package jp.fkmsoft.sample.asynctask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

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
                new MyTask().execute(2 * 1000);
            }
        };
        
        View button = findViewById(R.id.button_start);
        button.setOnClickListener(listener);
    }

}
