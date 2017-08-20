package test.assignment.ui.subbreed;

import java.util.List;

import test.assignment.data.BreedModel;
import test.assignment.data.SubBreedModel;



public class SubBreedContract {

    public interface View  {
        void onSubBreedsStartsLoading();

        void onSubBreedsLoaded(List<SubBreedModel> subBreedModelList);
        void onSubBreedsLoadError();
    }

    interface Presenter {
        void loadSubBreeds(String breed);

        void unsubscribe();
    }
}
