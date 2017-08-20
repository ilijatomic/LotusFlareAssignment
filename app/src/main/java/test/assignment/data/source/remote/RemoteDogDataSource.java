package test.assignment.data.source.remote;

import java.util.List;

import rx.Observable;
import test.assignment.data.BreedModel;

public interface RemoteDogDataSource {
    Observable<List<BreedModel>> getAllBreeds();
    Observable<String> getSubBreedImage(String breed, String subbreed);
}
