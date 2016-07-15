package ext.android.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DrawablesClickTextView textView = (DrawablesClickTextView) findViewById(R.id.drawables_click_text_view);
        textView.setOnDrawablesClickListener(new DrawablesClickTextView.OnDrawablesClickListener() {
            @Override
            public void onDrawablesClick(int drawable) {
                switch (drawable) {
                    case DrawablesClickTextView.DRAWABLE_LEFT:
                        Toast.makeText(MainActivity.this, "left drawable", Toast.LENGTH_LONG).show();
                        break;
                    case DrawablesClickTextView.DRAWABLE_RIGHT:
                        Toast.makeText(MainActivity.this, "right drawable", Toast.LENGTH_LONG).show();
                        break;
                    case DrawablesClickTextView.DRAWABLE_TOP:
                        Toast.makeText(MainActivity.this, "top drawable", Toast.LENGTH_LONG).show();
                        break;
                    case DrawablesClickTextView.DRAWABLE_BOTTOM:
                        Toast.makeText(MainActivity.this, "bottom drawable", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }
}
