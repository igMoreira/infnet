package sae.infnet.al.edu.av1iagodasilva.activities.education;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import sae.infnet.al.edu.av1iagodasilva.MainActivity;
import sae.infnet.al.edu.av1iagodasilva.R;
import sae.infnet.al.edu.av1iagodasilva.activities.CurriculumStepActivity;
import sae.infnet.al.edu.av1iagodasilva.activities.experience.ExperienceRegisterActivity;
import sae.infnet.al.edu.av1iagodasilva.model.Education;

public class EducationRegisterActivity extends CurriculumStepActivity {

    EditText institutionField;

    public EducationRegisterActivity() {
        super(ExperienceRegisterActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_register);

        institutionField = (EditText) findViewById(R.id.institution_register_editText);
    }


    @Override
    protected void agregateMessage() {
        Education education = new Education();
        education.setInstitution(institutionField.getText().toString());

        this.cv.setEducation(education);
    }
}
