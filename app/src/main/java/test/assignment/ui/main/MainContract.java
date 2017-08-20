package test.assignment.ui.main;

import java.util.List;

import test.assignment.data.BreedModel;



public class MainContract {

    public interface View  {
        void onAllBreedsStartsLoading();

        void onAllBreedsLoaded(List<BreedModel> breedModelList);
        void onAllBreedsLoadError();
    }

    interface Presenter {
        void loadAllBreeds(boolean forceRefresh);
        void unsubscribe();
    }
}
