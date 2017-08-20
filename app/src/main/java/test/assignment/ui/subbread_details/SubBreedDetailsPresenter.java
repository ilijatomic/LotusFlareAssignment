package test.assignment.ui.subbread_details;

import android.util.Log;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import test.assignment.data.source.DogRepository;



public class SubBreedDetailsPresenter implements SubBreedDetailsContract.Presenter {

    private static final String TAG = "SubBreedDetailsPresente";

    private final DogRepository dogRepository;
    private final SubBreedDetailsContract.View subbreedDetailsView;

    private CompositeSubscription mSubscriptions;

    @Inject
    public SubBreedDetailsPresenter(DogRepository dogRepository, SubBreedDetailsContract.View view) {
        this.dogRepository = dogRepository;
        this.subbreedDetailsView = view;

        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void getSubbreedImage(String breed, String subbread) {
        subbreedDetailsView.onImgRetrievalInProgress();
        mSubscriptions.add(dogRepository.getSubBreedImage(breed, subbread)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subbreedDetailsView::onImgRetrieved, error -> {
                    Log.e(TAG, "Couldn't get url: " + error);
                    subbreedDetailsView.onImgRetrieveError();
                }));
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
