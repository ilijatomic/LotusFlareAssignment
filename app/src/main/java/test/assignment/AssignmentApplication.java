package test.assignment;

import android.app.Application;

import io.realm.Realm;
import test.assignment.commons.ioc.components.BaseComponent;
import test.assignment.commons.ioc.components.DaggerBaseComponent;
import test.assignment.commons.ioc.modules.ApplicationModule;
import test.assignment.data.source.DaggerDogRepositoryComponent;
import test.assignment.data.source.DogRepositoryComponent;

public class AssignmentApplication extends Application {

    private DogRepositoryComponent dogRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        BaseComponent baseComponent = DaggerBaseComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        dogRepositoryComponent = DaggerDogRepositoryComponent.builder()
                .baseComponent(baseComponent)
                .build();

        Realm.init(this);
    }

    public DogRepositoryComponent getDogRepositoryComponent() {
        return dogRepositoryComponent;
    }
}
