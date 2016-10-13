package dljones79.com.eventbus;

import org.greenrobot.eventbus.EventBus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = BuildConfig.API_KEY;

    private Button mLoginButton;
    private Button mSecondActivityButton;
    private EditText mUserName;

    private EventBus mBus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("API_KEY: ", API_KEY);

        mLoginButton = (Button) findViewById(R.id.login_btn);
        mSecondActivityButton = (Button) findViewById(R.id.second_activity_btn);
        mUserName = (EditText) findViewById(R.id.user_name);

        // Show FragmentA inside the frame layout
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new FragmentA())
                .commit();

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // throw an error if user name was empty
                // else, login the user and send logged userName to all subscribers
                if (mUserName.getText().toString().isEmpty()) {
                    mUserName.setError("Please Enter Username Sucker!");
                } else {
                    /**
                     * Send the event to all subscribers
                     */
                    mBus.postSticky(new LoginEvent(mUserName.getText().toString()));
                }
            }
        });

        mSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

    }

    @Override
    protected void onDestroy() {
        mBus.unregister(this);
        super.onDestroy();
    }
}










