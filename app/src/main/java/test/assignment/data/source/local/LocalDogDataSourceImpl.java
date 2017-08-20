package test.assignment.data.source.local;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import rx.Observable;
import test.assignment.data.BreedModel;
import test.assignment.data.SubBreedModel;

public class LocalDogDataSourceImpl implements LocalDogDataSource {

    public LocalDogDataSourceImpl(SharedPreferences sharedPreferences, Application application) {

    }

    @Override
    public Observable<List<BreedModel>> getAllBreeds() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        List<BreedModel> result = realm.copyFromRealm(realm.where(BreedModel.class).findAll());
        realm.cancelTransaction();
        realm.close();

        return Observable.just(result);
    }

    @Override
    public void saveBreeds(List<BreedModel> breedModels) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(breedModels);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public Observable<List<SubBreedModel>> getSubBreeds(String breed) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        List<SubBreedModel> result = new ArrayList<>();
        BreedModel breedModel = realm.where(BreedModel.class).equalTo("name", breed).findFirst();
        if (breedModel != null) {
            result = (realm.copyFromRealm(breedModel.getSubBreeds().where().findAll()));
        }

        realm.cancelTransaction();
        realm.close();

        return Observable.just(result);
    }
}
