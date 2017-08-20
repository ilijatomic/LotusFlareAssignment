package test.assignment.ui.main;

import dagger.Module;
import dagger.Provides;



@Module
public class MainPresenterModule {

    private final MainContract.View mView;

    public MainPresenterModule(MainContract.View view) {
        mView = view;
    }

    @Provides
    MainContract.View provideMainView() {
        return mView;
    }
}
