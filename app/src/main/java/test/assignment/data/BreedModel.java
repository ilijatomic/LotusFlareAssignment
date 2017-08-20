package test.assignment.data;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BreedModel extends RealmObject {
    @PrimaryKey
    String name;

    RealmList<SubBreedModel> subBreeds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<SubBreedModel> getSubBreeds() {
        return subBreeds;
    }

    public void setSubBreeds(RealmList<SubBreedModel> subBreeds) {
        this.subBreeds = subBreeds;
    }

    @Override
    public String toString() {
        return "BreedModel{" +
                "name='" + name + '\'' +
                ", subBreeds=" + subBreeds +
                '}';
    }
}
