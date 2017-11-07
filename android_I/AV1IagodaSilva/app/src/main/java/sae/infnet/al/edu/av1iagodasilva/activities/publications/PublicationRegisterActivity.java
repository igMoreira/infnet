package sae.infnet.al.edu.av1iagodasilva.activities.publications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import sae.infnet.al.edu.av1iagodasilva.R;
import sae.infnet.al.edu.av1iagodasilva.activities.CurriculumStepActivity;
import sae.infnet.al.edu.av1iagodasilva.model.Publication;

public class PublicationRegisterActivity extends CurriculumStepActivity {

    EditText description;

    public PublicationRegisterActivity() {
        super(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_register);

        description = (EditText) findViewById(R.id.publication_register_editText);
    }

    @Override
    protected void agregateMessage() {
        Publication publication = new Publication();
        publication.setDescription(description.getText().toString());

        this.cv.setPublication(publication);
    }
}
