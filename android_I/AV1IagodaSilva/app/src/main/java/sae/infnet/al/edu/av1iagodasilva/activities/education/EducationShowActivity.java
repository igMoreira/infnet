package sae.infnet.al.edu.av1iagodasilva.activities.education;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import sae.infnet.al.edu.av1iagodasilva.MainActivity;
import sae.infnet.al.edu.av1iagodasilva.R;
import sae.infnet.al.edu.av1iagodasilva.model.Curriculum;
import sae.infnet.al.edu.av1iagodasilva.model.Education;

public class EducationShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_show);

        Intent intent = getIntent();
        Curriculum cv = intent.getParcelableExtra(MainActivity.CURRICULUM_HEADER);

        Education edu = cv.getEducation();

        TextView institution = (TextView) findViewById(R.id.education_show_text);
        institution.setText(edu.getInstitution());
    }
}
