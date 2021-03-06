package io.github.mohamedisoliman.androiddev.data.remote;

import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mohamed Ibrahim on 7/31/18.
 */
public class RemotesDataFactory {

  private RemotesDataFactory() {
  }

  private static final String BASE_URL = "https://www.reddit.com";

  public static RedditApi newRedditApi() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
        .client(getOkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(getGsonFactory())
        .build();
    return retrofit.create(RedditApi.class);
  }

  @NonNull
  private static GsonConverterFactory getGsonFactory() {
    Gson gson = new GsonBuilder().create();
    return GsonConverterFactory.create(gson);
  }

  private static OkHttpClient getOkHttpClient() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    final OkHttpClient.Builder clientBuilder =
        new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
    return clientBuilder.build();
  }
}
