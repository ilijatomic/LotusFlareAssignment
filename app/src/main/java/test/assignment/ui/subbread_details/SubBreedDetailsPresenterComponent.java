package test.assignment.ui.subbread_details;

import dagger.Component;
import test.assignment.commons.ioc.scopes.ActivityScope;
import test.assignment.data.source.DogRepositoryComponent;



@ActivityScope
@Component(dependencies = {DogRepositoryComponent.class},  modules = { SubBreedDetailsPresenterModule.class} )
public interface SubBreedDetailsPresenterComponent {

    void inject(SubBreedDetailsActivity activity);
}
