package sae.infnet.al.edu.av1iagodasilva.activities.experience;

import android.os.Bundle;
import android.widget.EditText;

import sae.infnet.al.edu.av1iagodasilva.R;
import sae.infnet.al.edu.av1iagodasilva.activities.CurriculumStepActivity;
import sae.infnet.al.edu.av1iagodasilva.activities.courses.CourseRegisterActivity;
import sae.infnet.al.edu.av1iagodasilva.model.Experience;

public class ExperienceRegisterActivity extends CurriculumStepActivity {

    EditText position;
    EditText company;


    public ExperienceRegisterActivity() {
        super(CourseRegisterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_register);

        position = (EditText) findViewById(R.id.position_register_editText);
        company = (EditText) findViewById(R.id.company_register_editText);
    }

    @Override
    protected void agregateMessage() {

        Experience exp = new Experience();
        exp.setCompany(company.getText().toString());
        exp.setPosition(position.getText().toString());

        this.cv.setExperience(exp);
    }
}
