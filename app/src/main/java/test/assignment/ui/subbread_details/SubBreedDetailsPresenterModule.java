package test.assignment.ui.subbread_details;

import dagger.Module;
import dagger.Provides;



@Module
public class SubBreedDetailsPresenterModule {

    private final SubBreedDetailsContract.View mView;

    public SubBreedDetailsPresenterModule(SubBreedDetailsContract.View view) {
        mView = view;
    }

    @Provides
    SubBreedDetailsContract.View provideSubBreedDetailsView() {
        return mView;
    }
}
