package test.assignment.commons.ioc.components;

import android.app.Application;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import test.assignment.commons.ioc.modules.ApplicationModule;
import test.assignment.commons.ioc.modules.LocalModule;
import test.assignment.commons.ioc.modules.NetworkModule;

@Singleton
@Component(modules = {ApplicationModule.class, LocalModule.class, NetworkModule.class})
public interface BaseComponent {

    Application getApplication();
    SharedPreferences getSharedPreferences();
    Retrofit getRetrofitApi();
    OkHttpClient getOkHttpClient();

}
