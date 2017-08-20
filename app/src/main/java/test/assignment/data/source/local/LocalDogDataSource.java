package test.assignment.data.source.local;

import java.util.List;

import rx.Observable;
import test.assignment.data.BreedModel;
import test.assignment.data.SubBreedModel;

public interface LocalDogDataSource {
    Observable<List<BreedModel>> getAllBreeds();
    void saveBreeds(List<BreedModel> breedModels);
    Observable<List<SubBreedModel>> getSubBreeds(String breed);
}
