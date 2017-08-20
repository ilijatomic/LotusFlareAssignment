package test.assignment.data.source;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import test.assignment.data.BreedModel;
import test.assignment.data.SubBreedModel;
import test.assignment.data.source.local.LocalDogDataSource;
import test.assignment.data.source.remote.RemoteDogDataSource;


public class DogRepository implements RemoteDogDataSource, LocalDogDataSource {

    private static final String TAG = "DogRepository";

    private final RemoteDogDataSource remoteDogDataSource;
    private final LocalDogDataSource localDogDataSource;

    @Inject
    public DogRepository(LocalDogDataSource localDogDataSource, RemoteDogDataSource remoteDogDataSource) {
        this.localDogDataSource = localDogDataSource;
        this.remoteDogDataSource = remoteDogDataSource;
    }

    @Override
    public Observable<List<BreedModel>> getAllBreeds() {
        return localDogDataSource.getAllBreeds()
                .flatMap(result -> {
                    if (result.size() == 0) { //if we don't have anything stored locally retrieve from API
                        return remoteDogDataSource.getAllBreeds()
                                .doOnNext(this::saveBreeds);
                    } else {
                        return Observable.just(result);
                    }
                });
    }

    public Observable<List<BreedModel>> refreshAllBreeds() {
        return remoteDogDataSource.getAllBreeds()
                .doOnNext(this::saveBreeds);
    }

    @Override
    public void saveBreeds(List<BreedModel> breedModels) {
        localDogDataSource.saveBreeds(breedModels);
    }

    @Override
    public Observable<List<SubBreedModel>> getSubBreeds(String breed) {
        return localDogDataSource.getSubBreeds(breed);
    }

    @Override
    public Observable<String> getSubBreedImage(String breed, String subbreed) {
        return remoteDogDataSource.getSubBreedImage(breed, subbreed);
    }
}
