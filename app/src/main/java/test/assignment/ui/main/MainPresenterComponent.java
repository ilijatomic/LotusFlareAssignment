package test.assignment.ui.main;

import dagger.Component;
import test.assignment.commons.ioc.scopes.ActivityScope;
import test.assignment.data.source.DogRepositoryComponent;



@ActivityScope
@Component(dependencies = {DogRepositoryComponent.class},  modules = { MainPresenterModule.class} )
public interface MainPresenterComponent {

    void inject(MainActivity activity);
}
