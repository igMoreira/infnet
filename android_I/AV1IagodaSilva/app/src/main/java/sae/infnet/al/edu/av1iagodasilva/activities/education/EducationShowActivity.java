package sae.infnet.al.edu.av1iagodasilva.activities.education;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import sae.infnet.al.edu.av1iagodasilva.MainActivity;
import sae.infnet.al.edu.av1iagodasilva.R;
import sae.infnet.al.edu.av1iagodasilva.model.Curriculum;
import sae.infnet.al.edu.av1iagodasilva.model.Education;

public class EducationShowActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_show);

        Intent intent = getIntent();
        Curriculum cv = intent.getParcelableExtra(MainActivity.CURRICULUM_HEADER);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        for (Education edu : cv.getEducationList()) {

            TextView institution = new TextView(this);
            institution.setText(edu.getInstitution());

            TextView startDate = new TextView(this);
            startDate.setText(df.format(edu.getStart()));

            TextView endDate = new TextView(this);
            endDate.setText(df.format(edu.getStart()));

            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(institution);
            layout.addView(startDate);
            layout.addView(endDate);
        }
    }
}
