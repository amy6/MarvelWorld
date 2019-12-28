package com.example.marvelworld.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.marvelworld.BuildConfig;
import com.example.marvelworld.model.MarvelApiResponse;
import com.example.marvelworld.model.MarvelCharacter;
import com.example.marvelworld.network.MarvelApiClient;
import com.example.marvelworld.network.MarvelApiService;
import com.example.marvelworld.utils.DigestUtils;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.marvelworld.ui.MarvelListActivity.TAG;

public class MarvelDataRepository {

    public static final String MARVEL_API_KEY_PRIVATE = BuildConfig.MARVEL_API_KEY_PRIVATE;
    private final MutableLiveData<List<MarvelCharacter>> marvelCharacters;
    private String apiKey;
    private MarvelApiService marvelApiService;

    public MarvelDataRepository(String apiKey) {
        this.marvelCharacters = new MutableLiveData<>();
        this.apiKey = apiKey;
        this.marvelApiService = MarvelApiClient.getClient().create(MarvelApiService.class);
    }

    public LiveData<List<MarvelCharacter>> getMarvelCharacters() {

        long ts = new Date().getTime();
        String hash = DigestUtils.getMd5(ts, MARVEL_API_KEY_PRIVATE, apiKey);

        marvelApiService.getMarvelCharacters(ts, apiKey, hash).enqueue(new Callback<MarvelApiResponse>() {
            @Override
            public void onResponse(Call<MarvelApiResponse> call, Response<MarvelApiResponse> response) {
                Log.d(TAG, "API call successful.");
                if (response.body() != null && response.body().getMarvelApiData().getMarvelCharacters() != null && response.body().getMarvelApiData().getMarvelCharacters().size() > 0) {
                    Log.d(TAG, "Characters fetched : " + response.body().getMarvelApiData().getMarvelCharacters().size());
                    marvelCharacters.postValue(response.body().getMarvelApiData().getMarvelCharacters());
                }
            }

            @Override
            public void onFailure(Call<MarvelApiResponse> call, Throwable t) {
                Log.e(TAG, "API call failed with error : " + t.getMessage());
            }
        });
        return marvelCharacters;
    }

    public LiveData<MarvelCharacter> getMarvelCharacter(int id) {

        long ts = new Date().getTime();
        String hash = DigestUtils.getMd5(ts, MARVEL_API_KEY_PRIVATE, apiKey);

        final MutableLiveData<MarvelCharacter> marvelCharacter = new MutableLiveData<>();
        marvelApiService.getMarvelCharacter(id, ts, apiKey, hash).enqueue(new Callback<MarvelApiResponse>() {
            @Override
            public void onResponse(Call<MarvelApiResponse> call, Response<MarvelApiResponse> response) {
                Log.d(TAG, "API call successful.");
                if (response.body() != null) {
                    MarvelCharacter character = response.body().getMarvelApiData().getMarvelCharacters().get(0);
                    Log.d(TAG, "Details obtained for character : " + character.getName());
                    marvelCharacter.setValue(character);
                }
            }

            @Override
            public void onFailure(Call<MarvelApiResponse> call, Throwable t) {
                Log.e(TAG, "API call failed with error : " + t.getMessage());
            }
        });
        return marvelCharacter;
    }
}
