package sae.infnet.al.edu.av1iagodasilva.activities.publications;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import sae.infnet.al.edu.av1iagodasilva.MainActivity;
import sae.infnet.al.edu.av1iagodasilva.R;
import sae.infnet.al.edu.av1iagodasilva.model.Course;
import sae.infnet.al.edu.av1iagodasilva.model.Curriculum;
import sae.infnet.al.edu.av1iagodasilva.model.Publication;

public class PublicationShowActivity extends AppCompatActivity {

    TextView descriptionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_show);

        descriptionField = (TextView) findViewById(R.id.pubdescription_show_text);

        Intent intent = getIntent();
        Curriculum cv = intent.getParcelableExtra(MainActivity.CURRICULUM_HEADER);

        Publication publication = cv.getPublication();

        descriptionField.setText(publication.getDescription());
    }
}
