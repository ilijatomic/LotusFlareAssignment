package test.assignment.data.source.remote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.RealmList;
import rx.Observable;
import test.assignment.data.BreedModel;
import test.assignment.data.SubBreedModel;

public class RemoteDogMockDataSource implements RemoteDogDataSource {
    @Override
    public Observable<List<BreedModel>> getAllBreeds() {
        List<BreedModel> breedModels = new ArrayList<>();

        BreedModel breedModel1 = new BreedModel();
        breedModel1.setName("Rasa 1");

        BreedModel breedModel2 = new BreedModel();
        breedModel2.setName("Rasa 2");

        List<SubBreedModel> subBreedModels = new ArrayList<>();
        SubBreedModel subBreedModel1 = new SubBreedModel();
        subBreedModel1.setName("Podrasa 22");
        SubBreedModel subBreedModel2 = new SubBreedModel();
        subBreedModel2.setName("Podrasa 23");
        subBreedModels.add(subBreedModel1);
        subBreedModels.add(subBreedModel2);

        RealmList<SubBreedModel> subBreedModelRealmList = new RealmList<>();
        subBreedModelRealmList.addAll(subBreedModels);
        breedModel2.setSubBreeds(subBreedModelRealmList);


        BreedModel breedModel3 = new BreedModel();
        breedModel3.setName("Rasa 3");

        BreedModel breedModel4 = new BreedModel();
        breedModel4.setName("Rasa 4");

        List<SubBreedModel> subBreedModels2 = new ArrayList<>();
        SubBreedModel subBreedModel3 = new SubBreedModel();
        subBreedModel3.setName("Podrasa 44");
        SubBreedModel subBreedModel4 = new SubBreedModel();
        subBreedModel4.setName("Podrasa 45");
        subBreedModels2.add(subBreedModel3);
        subBreedModels2.add(subBreedModel4);

        RealmList<SubBreedModel> subBreedModelRealmList2 = new RealmList<>();
        subBreedModelRealmList2.addAll(subBreedModels2);
        breedModel4.setSubBreeds(subBreedModelRealmList2);

        breedModels.add(breedModel1);
        breedModels.add(breedModel2);
        breedModels.add(breedModel3);
        breedModels.add(breedModel4);

        return Observable.just(breedModels);
    }

    @Override
    public Observable<String> getSubBreedImage(String breed, String subbreed) {
        Random random = new Random();
        return Observable.just("http://lorempixel.com/1080/1920/animals/" + random.nextInt(5));
    }
}
