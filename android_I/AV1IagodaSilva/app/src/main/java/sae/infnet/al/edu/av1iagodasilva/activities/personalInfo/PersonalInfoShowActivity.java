package sae.infnet.al.edu.av1iagodasilva.activities.personalInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import sae.infnet.al.edu.av1iagodasilva.MainActivity;
import sae.infnet.al.edu.av1iagodasilva.R;
import sae.infnet.al.edu.av1iagodasilva.model.Curriculum;
import sae.infnet.al.edu.av1iagodasilva.model.PersonalInfo;

public class PersonalInfoShowActivity extends AppCompatActivity {

    private TextView nameField;
    private TextView addressField;
    private TextView stateField;
    private TextView cityField;
    private TextView phoneField;
    private TextView emailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinfo_show);

        nameField = (TextView) findViewById(R.id.name_personalinfo_show_text);
        addressField = (TextView) findViewById(R.id.address_personalinfo_show_text);
        stateField = (TextView) findViewById(R.id.state_personalinfo_show_text);
        cityField = (TextView) findViewById(R.id.city_personalinfo_show_text);
        phoneField = (TextView) findViewById(R.id.phone_personalinfo_show_text);
        emailField = (TextView) findViewById(R.id.email_personalinfo_show_text);

        Intent intent = getIntent();
        Curriculum cv = intent.getParcelableExtra(MainActivity.CURRICULUM_HEADER);

        PersonalInfo personalInfo = cv.getPersonalInfo();

        if (personalInfo != null) {
            nameField.setText(personalInfo.getName());
            addressField.setText(personalInfo.getAddress());
            stateField.setText(personalInfo.getState());
            cityField.setText(personalInfo.getCity());
            phoneField.setText(personalInfo.getPhone());
            emailField.setText(personalInfo.getEmail());
        }
    }
}
