package test.assignment.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SubBreedModel extends RealmObject {
    @PrimaryKey
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SubBreedModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
