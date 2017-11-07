package sae.infnet.al.edu.av1iagodasilva.activities.experience;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import sae.infnet.al.edu.av1iagodasilva.MainActivity;
import sae.infnet.al.edu.av1iagodasilva.R;
import sae.infnet.al.edu.av1iagodasilva.model.Curriculum;
import sae.infnet.al.edu.av1iagodasilva.model.Education;
import sae.infnet.al.edu.av1iagodasilva.model.Experience;

public class ExperienceShowActivity extends AppCompatActivity {

    TextView positionField;
    TextView companyField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_show);

        positionField = (TextView) findViewById(R.id.position_show_text);
        companyField = (TextView) findViewById(R.id.company_show_text);

        Intent intent = getIntent();
        Curriculum cv = intent.getParcelableExtra(MainActivity.CURRICULUM_HEADER);

        Experience exp = cv.getExperience();

        positionField.setText(exp.getPosition());
        companyField.setText(exp.getCompany());
    }
}
