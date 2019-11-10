package com.charan.roomwordssample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private WordDao wordDao;
    private LiveData<List<Word>> allWords;


    public WordRepository(Application application){

        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        allWords = wordDao.getAllWords();

    }

    LiveData<List<Word>> getAllWords(){

        return allWords;

    }

    public void insert(Word word){

        new insertAsyncTask(wordDao).execute(word);

    }


}
