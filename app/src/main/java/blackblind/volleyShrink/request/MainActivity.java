package blackblind.volleyShrink.request;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VolleyShrink volleyShrink = new VolleyShrink(MainActivity.this);
//        volleyShrink.jsonRequest()
//        volleyShrink.Url("asdhfhasd");
    }
}
