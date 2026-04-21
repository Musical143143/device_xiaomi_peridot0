import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityOptions;
import android.view.View;
import android.widget.Button;

public class LauncherActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        
        Button appButton = findViewById(R.id.appButton);
        appButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the app with custom animations
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("com.example.app", "com.example.app.MainActivity");

                ActivityOptions options = ActivityOptions.makeCustomAnimation(
                        LauncherActivity.this, // Context
                        R.anim.slide_in_right, // Custom animation for app open
                        R.anim.slide_out_left  // Custom animation for app close
                );

                startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Apply animation when the app is closing
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
