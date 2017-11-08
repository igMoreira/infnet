package sae.infnet.al.edu.av1iagodasilva.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import sae.infnet.al.edu.av1iagodasilva.MainActivity;
import sae.infnet.al.edu.av1iagodasilva.R;
import sae.infnet.al.edu.av1iagodasilva.activities.education.EducationRegisterActivity;
import sae.infnet.al.edu.av1iagodasilva.model.Curriculum;

/**
 * Created by igMoreira on 06/11/17.
 */

public abstract class CurriculumStepActivity extends AppCompatActivity {

    protected static String TAG;
    protected Curriculum cv;
    protected final Class NEXT_STEP;

    protected CurriculumStepActivity(Class next_step) {
        NEXT_STEP = next_step;
    }


    protected abstract void agregateMessage();

    public void executeStep(View view)
    {
        loadMessage();
        agregateMessage();
        if (NEXT_STEP != null) {
            nextStep(NEXT_STEP);
        } else {
            sendResponse();
            finish();
        }
    }

    protected void loadMessage() {
        Log.d("IAGO 1", this.cv != null ? "CV not null" : "null");
        Intent intent = getIntent();
        Curriculum receivedCv = intent.getParcelableExtra(MainActivity.CURRICULUM_HEADER);
        Log.d("IAGO 2", receivedCv != null ? "received not null" : "null");
        this.cv = (receivedCv != null ) ? receivedCv : this.cv;
        Log.d("IAGO 3", this.cv != null ? "CV NOT null" : "null");
    }

    protected void nextStep(Class<EducationRegisterActivity> nextStep) {
        Intent requestNext = new Intent(this, nextStep);
        requestNext.putExtra(MainActivity.CURRICULUM_HEADER, this.cv);
        startActivityForResult(requestNext, MainActivity.CV_REQUEST);
    }

    protected void sendResponse() {
        Intent response = new Intent();
        response.putExtra(MainActivity.CV_RESPONSE, this.cv);

        setResult(RESULT_OK, response);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainActivity.CV_REQUEST) {
            if (resultCode == RESULT_OK) {
                this.cv = data.getParcelableExtra(MainActivity.CV_RESPONSE);
                sendResponse();

                Log.d(TAG, this.cv.toString());

                finish();
            }
        }
    }
}
