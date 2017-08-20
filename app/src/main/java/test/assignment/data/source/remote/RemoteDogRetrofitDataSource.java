package test.assignment.data.source.remote;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.realm.RealmList;
import rx.Observable;
import test.assignment.data.BreedModel;
import test.assignment.data.SubBreedModel;
import test.assignment.data.source.remote.beans.Response;

public class RemoteDogRetrofitDataSource implements RemoteDogDataSource {

    private static final String TAG = "DogRetrofitDataSource";

    RetrofitRestApiModule.DogService dogService;

    @Inject
    public RemoteDogRetrofitDataSource(RetrofitRestApiModule.DogService dogService) {
        this.dogService = dogService;
    }


    @Override
    public Observable<List<BreedModel>> getAllBreeds() {
        return dogService.getAllBreeds().flatMap(dogBreedsResponse -> {
            Log.d(TAG, "dogBreedsResponse: " + dogBreedsResponse);
            if (dogBreedsResponse.getStatus().equals(Response.SUCCESS)) {
                List<BreedModel> result = new ArrayList<>();
                Map<String, List<String>> dogBreedsMap = dogBreedsResponse.getMessage();
                for (String breedName : dogBreedsMap.keySet()) {
                    BreedModel breedModel = new BreedModel();
                    breedModel.setName(breedName);
                    breedModel.setSubBreeds(new RealmList<>());

                    List<String> subbreeds = dogBreedsMap.get(breedName);
                    List<SubBreedModel> subBreedModels = new ArrayList<>();
                    for (String subbreed : subbreeds) {
                        SubBreedModel subBreedModel = new SubBreedModel();
                        subBreedModel.setName(subbreed);
                        subBreedModels.add(subBreedModel);
                    }

                    breedModel.getSubBreeds().addAll(subBreedModels);
                    result.add(breedModel);
                }
                return Observable.just(result);
            } else {
                return Observable.error(new Exception(dogBreedsResponse.getCode() + dogBreedsResponse.getMessage()));
            }
        });
    }

    @Override
    public Observable<String> getSubBreedImage(String breed, String subbreed) {
        return dogService.getSubBreedImage(breed, subbreed)
                .flatMap(dogSubBreedImageResponse -> {
                    if (dogSubBreedImageResponse.getStatus().equals(Response.SUCCESS)) {
                        return Observable.just(dogSubBreedImageResponse.getMessage());
                    } else {
                        return Observable.error(new Exception(dogSubBreedImageResponse.getCode() + dogSubBreedImageResponse.getMessage()));
                    }
                });
    }
}
