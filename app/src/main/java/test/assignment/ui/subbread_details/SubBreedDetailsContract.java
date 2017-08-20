package test.assignment.ui.subbread_details;

import java.util.List;

import test.assignment.data.SubBreedModel;



public class SubBreedDetailsContract {

    public interface View  {
        void onImgRetrievalInProgress();
        void onImgRetrieved(String imgUrl);
        void onImgRetrieveError();
    }

    interface Presenter {
        void getSubbreedImage(String breed, String subbread);
        void unsubscribe();
    }
}
