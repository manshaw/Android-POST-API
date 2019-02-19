package www.pakzarzameen.com.pk;

import android.Manifest;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button Json;
    public static TextView Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // This is some magic for Android to load a previously saved state for when you are switching between actvities.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.CHANGE_WIFI_MULTICAST_STATE,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.CHANGE_WIFI_STATE,
                            Manifest.permission.ACCESS_NETWORK_STATE
                    }
                    , 12345);
        }
        setContentView(R.layout.activity_main);
        Json = (Button) findViewById(R.id.json);
        Data = (TextView) findViewById(R.id.fetched_data);
        Json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                json process = new json();
                Toast.makeText(MainActivity.this, "coomit test", Toast.LENGTH_SHORT).show();
                process.execute();
            }
        });
    }
}
