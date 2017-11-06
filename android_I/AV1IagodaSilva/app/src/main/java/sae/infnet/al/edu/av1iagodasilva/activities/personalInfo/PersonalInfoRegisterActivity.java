package sae.infnet.al.edu.av1iagodasilva.activities.personalInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import sae.infnet.al.edu.av1iagodasilva.MainActivity;
import sae.infnet.al.edu.av1iagodasilva.R;
import sae.infnet.al.edu.av1iagodasilva.model.Curriculum;
import sae.infnet.al.edu.av1iagodasilva.model.PersonalInfo;

public class PersonalInfoRegisterActivity extends AppCompatActivity {

    private static final String TAG = PersonalInfoRegisterActivity.class.getSimpleName();
    public static final String CV_PERSONALINFO_RESPONSE = "PersonalInfo_Registration_Header";

    private EditText nameField;
    private EditText addressField;
    private EditText stateField;
    private EditText cityField;
    private EditText phoneField;
    private EditText emailField;
    private Curriculum cv;

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

        Intent intent = getIntent();
        cv = intent.getParcelableExtra(MainActivity.CURRICULUM_HEADER);

        Log.d(TAG, "Starting Personal Information registration step...");
    }

    public void registerPersonalInfo(View view) {
        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setName(nameField.getText().toString());
        personalInfo.setAddress(addressField.getText().toString());
        personalInfo.setState(stateField.getText().toString());
        personalInfo.setCity(cityField.getText().toString());
        personalInfo.setPhone(phoneField.getText().toString());
        personalInfo.setEmail(emailField.getText().toString());

        this.cv.setPersonalInfo(personalInfo);

        Intent response = new Intent();
        response.putExtra(CV_PERSONALINFO_RESPONSE, this.cv);

        Log.d(TAG, "Personal Information registration step. DONE");
        Log.d(TAG, this.cv.toString());

        setResult(RESULT_OK, response);
        finish();
    }
}
