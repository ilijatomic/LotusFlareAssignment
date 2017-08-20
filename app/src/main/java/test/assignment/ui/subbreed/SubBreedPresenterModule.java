package test.assignment.ui.subbreed;

import dagger.Module;
import dagger.Provides;



@Module
public class SubBreedPresenterModule {

    private final SubBreedContract.View mView;

    public SubBreedPresenterModule(SubBreedContract.View view) {
        mView = view;
    }

    @Provides
    SubBreedContract.View provideSubBreView() {
        return mView;
    }
}
