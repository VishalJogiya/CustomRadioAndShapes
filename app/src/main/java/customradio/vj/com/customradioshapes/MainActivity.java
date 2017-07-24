package customradio.vj.com.customradioshapes;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    CustomRadio radioButton;
    Button colorButton;

    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            radioButton = (CustomRadio) findViewById(i);
            radioButton.setChecked(true);
            colorButton.setBackgroundColor(radioButton.getPieColor());
        }
    };
    private RadioGroup myLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup) findViewById(R.id.radio_grp1);
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        colorButton = (Button) findViewById(R.id.color_button);

        myLL = (RadioGroup) findViewById(R.id.my_ll);


        RadioGroup.LayoutParams rlp = new RadioGroup.LayoutParams(40, 40);
        rlp.setMargins(10,10,10,10);
        CustomRadio radio;

        for (int i = 0; i < 5; i++) {
            radio = new CustomRadio(this, CustomView.SIMPLE_CIRCLE2);
//            radio.setChecked(true);
            radio.setPieColor(Color.RED);
            radio.setLayoutParams(rlp);
            myLL.addView(radio);
        }


    }
}
