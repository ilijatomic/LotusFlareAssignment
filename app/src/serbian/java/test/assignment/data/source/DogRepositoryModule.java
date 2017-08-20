package test.assignment.data.source;

import android.app.Application;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;
import test.assignment.commons.ioc.scopes.ApplicationScope;
import test.assignment.data.source.local.LocalDogDataSource;
import test.assignment.data.source.local.LocalDogDataSourceImpl;
import test.assignment.data.source.remote.RemoteDogDataSource;
import test.assignment.data.source.remote.RemoteDogMockDataSource;

@Module
public class DogRepositoryModule {

    @ApplicationScope
    @Provides
    LocalDogDataSource provideLocalDataSource(SharedPreferences sharedPreferences, Application application) {
        return new LocalDogDataSourceImpl(sharedPreferences, application);
    }

    @ApplicationScope
    @Provides
    RemoteDogDataSource provideRemoteDataSource() {
        return new RemoteDogMockDataSource();
    }
}
