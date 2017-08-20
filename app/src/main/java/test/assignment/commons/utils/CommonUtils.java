package test.assignment.commons.utils;

import java.util.ArrayList;
import java.util.List;

import test.assignment.data.BreedModel;
import test.assignment.data.SubBreedModel;

public class CommonUtils {

    public static List<String> getBreedModelsNames(List<BreedModel> breedModels) {
        List<String> result = new ArrayList<>();
        for (BreedModel breedModel: breedModels) {
            result.add(breedModel.getName());
        }
        return result;
    }

    public static List<String> getSubBreedModelsNames(List<SubBreedModel> subBreedModels) {
        List<String> result = new ArrayList<>();
        for (SubBreedModel subBreedModel: subBreedModels) {
            result.add(subBreedModel.getName());
        }
        return result;
    }
}
