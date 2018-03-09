package com.example.juanlu.comic;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanlu on 9/03/18.
 */

public class getComicCharacterJsonData extends GetRawData {
    private static final String LOG_TAG = getComicCharacterJsonData.class.getSimpleName();

    private List<ComicCharacter> mComicCharacters;
    private Uri mDestinationUri;

    public getComicCharacterJsonData() {
        super(null);
        createAndUpdateUri();
        mComicCharacters = new ArrayList<>();
    }

    public List<ComicCharacter> getmComicCharacters() {
        return mComicCharacters;
    }

    public void setmComicCharacters(List<ComicCharacter> mComicCharacters) {
        this.mComicCharacters = mComicCharacters;
    }

    private boolean createAndUpdateUri() {
        final String COMIC_CHARACTER_API_URL = "http://gateway.marvel.com/v1/public/characters";
        final String API_KEY = "apikey";
        final String TIMESTAMP = "ts";
        final String HASH = "hash";

        mDestinationUri = Uri.parse(COMIC_CHARACTER_API_URL).buildUpon()
                .appendQueryParameter(TIMESTAMP, "1")
                .appendQueryParameter(API_KEY, "8522cdaa29595d1d39341a8775395119")
                .appendQueryParameter(HASH, "99b75f4350fe82711327d1a29cba3b56")
                .build();

        return mDestinationUri != null;
    }


    private void processResult() {
        final String COMIC_CHARACTER_DATA = "data";
        final String COMIC_CHARACTER_RESULTS = "results";
        final String COMIC_CHARACTER_ID = "id";
        final String COMIC_CHARACTER_NAME = "name";
        final String COMIC_CHARACTER_DESCRIPTION = "description";
        final String COMIC_CHARACTER_THUMBNAIL = "thumbnail";
        final String COMIC_CHARACTER_PATH = "path";
        final String COMIC_CHARACTER_EXTENSION = "extension";

        if (getDownloadStatus() != DownloadStatus.OK) {
            Log.e(LOG_TAG, "No se ha descargado el JSON");
            return;
        }


        try {
            JSONObject jsonData = new JSONObject(getData());
            JSONObject data = jsonData.getJSONObject(COMIC_CHARACTER_DATA);

            JSONArray resultsArray = data.getJSONArray(COMIC_CHARACTER_RESULTS);

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject jsonComicCharacter = resultsArray.getJSONObject(i);
                String id = jsonComicCharacter.getString(COMIC_CHARACTER_ID);
                String name = jsonComicCharacter.getString(COMIC_CHARACTER_NAME);
                String description = jsonComicCharacter.getString(COMIC_CHARACTER_DESCRIPTION);
                JSONObject thumbnail = jsonComicCharacter.getJSONObject(COMIC_CHARACTER_THUMBNAIL);
                String path = thumbnail.getString(COMIC_CHARACTER_PATH);
                String extension = thumbnail.getString(COMIC_CHARACTER_EXTENSION);

                String image = path+"."+extension;

                ComicCharacter comicCharacter = new ComicCharacter(id,name,description,image);

                mComicCharacters.add(comicCharacter);
            }

            for (ComicCharacter comicCharacter: mComicCharacters){
                Log.d(LOG_TAG, "Comic Character " + comicCharacter.toString());
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "No se puede crear el objeto JSON");
            e.printStackTrace();
        }
    }

    public void execute(){
        DownloadJsonData downloadJsonData = new DownloadJsonData();
        Log.v(LOG_TAG, "Build Uri: " + mDestinationUri.toString());
        downloadJsonData.execute(mDestinationUri.toString());
    }

    public class DownloadJsonData extends DownloadRawData{

        @Override
        protected void onPostExecute(String webData) {
            super.onPostExecute(webData);
            processResult();
        }

        @Override
        protected String doInBackground(String... params) {
            String [] par = { mDestinationUri.toString() };

            return super.doInBackground(par);
        }
    }
}
