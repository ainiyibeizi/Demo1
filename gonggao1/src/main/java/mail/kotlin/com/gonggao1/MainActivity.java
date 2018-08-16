package mail.kotlin.com.gonggao1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TipView tipView;
    private static final String TIP_PREFIX = "this is tip No.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipView = (TipView) findViewById(R.id.tip_view);
        tipView.setTipList(generateTips());
    }

    private List<String> generateTips() {
        List<String> tips = new ArrayList<>();
        for (int i = 100; i < 120; i++) {
            tips.add(TIP_PREFIX + i);
        }
        return tips;
    }

}
