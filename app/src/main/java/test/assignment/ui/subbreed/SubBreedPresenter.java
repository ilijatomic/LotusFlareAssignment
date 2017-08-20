package test.assignment.ui.subbreed;

import android.util.Log;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import test.assignment.data.source.DogRepository;



public class SubBreedPresenter implements SubBreedContract.Presenter {

    private static final String TAG = "SubBreedPresenter";

    private final DogRepository dogRepository;
    private final SubBreedContract.View subbreedView;

    private CompositeSubscription mSubscriptions;

    @Inject
    public SubBreedPresenter(DogRepository dogRepository, SubBreedContract.View view) {
        this.dogRepository = dogRepository;
        this.subbreedView = view;

        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void loadSubBreeds(String breed) {
        Log.d(TAG, "loadSubBreeds called, breed: " + breed);
        subbreedView.onSubBreedsStartsLoading();
        mSubscriptions.add(dogRepository.getSubBreeds(breed)
                .doOnSubscribe(subbreedView::onSubBreedsStartsLoading)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Log.d(TAG, "result: " + result);
                    subbreedView.onSubBreedsLoaded(result);
                }, error -> {
                    Log.e(TAG, "Couldn't load all breeds: " + error);
                    subbreedView.onSubBreedsLoadError();
                }));
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
