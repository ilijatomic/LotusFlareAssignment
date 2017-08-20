package test.assignment.data.source;

import android.app.Application;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import test.assignment.commons.ioc.components.BaseComponent;
import test.assignment.commons.ioc.scopes.ApplicationScope;
import test.assignment.data.source.remote.RetrofitRestApiModule;

@ApplicationScope
@Component(dependencies = {BaseComponent.class}, modules = {RetrofitRestApiModule.class, DogRepositoryModule.class})
public interface DogRepositoryComponent {

    Application getApplication();
    DogRepository getDogRepository();
    OkHttpClient getOkHttpClient();
    Retrofit getRetrofitApi();
}

