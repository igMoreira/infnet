package sae.infnet.al.edu.av1iagodasilva.activities.personalInfo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import sae.infnet.al.edu.av1iagodasilva.MainActivity;
import sae.infnet.al.edu.av1iagodasilva.R;
import sae.infnet.al.edu.av1iagodasilva.activities.CurriculumStepActivity;
import sae.infnet.al.edu.av1iagodasilva.activities.education.EducationRegisterActivity;
import sae.infnet.al.edu.av1iagodasilva.model.Curriculum;
import sae.infnet.al.edu.av1iagodasilva.model.PersonalInfo;

public class PersonalInfoRegisterActivity extends CurriculumStepActivity {


    private EditText nameField;
    private EditText addressField;
    private EditText stateField;
    private EditText cityField;
    private EditText phoneField;
    private EditText emailField;

    public PersonalInfoRegisterActivity() {
        super(EducationRegisterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo_register);

        nameField = (EditText) findViewById(R.id.name_register_editText);
        addressField = (EditText) findViewById(R.id.address_register_editText);
        stateField = (EditText) findViewById(R.id.state_register_editText);
        cityField = (EditText) findViewById(R.id.city_register_editText);
        phoneField = (EditText) findViewById(R.id.phone_register_editText);
        emailField = (EditText) findViewById(R.id.email_register_editText);

        this.TAG = PersonalInfoRegisterActivity.class.getSimpleName();

        Log.d(TAG, "Starting Personal Information registration step...");
    }


    @Override
    protected void agregateMessage() {
        Log.d("IAGO 4", this.cv != null ? "CV not null" : "null");
        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setName(nameField.getText().toString());
        personalInfo.setAddress(addressField.getText().toString());
        personalInfo.setState(stateField.getText().toString());
        personalInfo.setCity(cityField.getText().toString());
        personalInfo.setPhone(phoneField.getText().toString());
        personalInfo.setEmail(emailField.getText().toString());

        this.cv.setPersonalInfo(personalInfo);
    }
}
