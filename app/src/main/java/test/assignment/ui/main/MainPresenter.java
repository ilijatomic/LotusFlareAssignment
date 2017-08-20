package test.assignment.ui.main;

import android.util.Log;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import test.assignment.data.source.DogRepository;



public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";

    private final DogRepository dogRepository;
    private final MainContract.View mainView;

    private CompositeSubscription mSubscriptions;

    @Inject
    public MainPresenter(DogRepository dogRepository, MainContract.View view) {
        this.dogRepository = dogRepository;
        this.mainView = view;

        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void loadAllBreeds(boolean forceRefresh) {
        mainView.onAllBreedsStartsLoading();
        mSubscriptions.add((forceRefresh ? dogRepository.refreshAllBreeds() : dogRepository.getAllBreeds())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Log.d(TAG, "result: " + result);
                    mainView.onAllBreedsLoaded(result);
                }, error -> {
                    Log.e(TAG, "Couldn't load all breeds: " + error);
                    mainView.onAllBreedsLoadError();
                }));
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
