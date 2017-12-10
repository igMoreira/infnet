package sae.infnet.al.edu.av1iagodasilva.workers;

import android.os.AsyncTask;
import android.util.Log;

import sae.infnet.al.edu.av1iagodasilva.model.Curriculum;
import sae.infnet.al.edu.av1iagodasilva.persistence.db.dao.CurriculumDAO;

/**
 * Created by igMoreira on 10/12/17.
 */

public class DbWorker extends AsyncTask<Curriculum, Integer, Integer> {

    private static final String TAG = DbWorker.class.getSimpleName();


    private CurriculumDAO dao;

    public DbWorker(CurriculumDAO dao) {
        this.dao = dao;
    }

    @Override
    protected Integer doInBackground(Curriculum... params) {
        int totalSaved = 0;
        for(Curriculum cv : params) {
            Log.d(TAG, "Saving in DB | UID: " + cv.getId());
            dao.upsert(cv);
            totalSaved++;
            Log.d(TAG, "Saved in DB Successfully | UID: " + cv.getId());
            if (isCancelled()) break;
        }
        return totalSaved;
    }
}
