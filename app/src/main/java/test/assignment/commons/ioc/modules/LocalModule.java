package test.assignment.commons.ioc.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalModule {

    @Singleton
    @Provides
    SharedPreferences providesUserSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

}
