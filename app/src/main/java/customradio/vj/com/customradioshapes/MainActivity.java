package customradio.vj.com.customradioshapes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioGroup;

import customradio.vj.com.library.CustomRadio;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    CustomRadio radioButton;
    Button colorButton;

    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            radioButton = (CustomRadio) findViewById(i);
            radioButton.setChecked(true);
            colorButton.setBackgroundColor(radioButton.getColor());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup) findViewById(R.id.radio_grp1);
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        colorButton = (Button) findViewById(R.id.color_button);


    }
}
