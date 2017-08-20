package test.assignment.ui.subbreed;

import dagger.Component;
import test.assignment.commons.ioc.scopes.ActivityScope;
import test.assignment.data.source.DogRepositoryComponent;



@ActivityScope
@Component(dependencies = {DogRepositoryComponent.class},  modules = { SubBreedPresenterModule.class} )
public interface SubBreedPresenterComponent {

    void inject(SubBreedActivity activity);
}
