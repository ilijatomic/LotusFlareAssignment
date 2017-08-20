package test.assignment.data.source.remote;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import test.assignment.commons.ioc.scopes.ApplicationScope;
import test.assignment.data.source.remote.beans.DogBreedsResponse;
import test.assignment.data.source.remote.beans.DogSubBreedImageResponse;

@Module
public class RetrofitRestApiModule {

    public interface DogService {

        @GET("/api/breeds/list/all")
        Observable<DogBreedsResponse> getAllBreeds();

        @GET("/api/breed/{breedname}/{subbreedname}/images/random")
        Observable<DogSubBreedImageResponse> getSubBreedImage(@Path("breedname") String breedname, @Path("subbreedname") String subbreedname);
    }

    @Provides
    @ApplicationScope
    public DogService provideDogService(Retrofit retrofit) {
        return retrofit.create(DogService.class);
    }
}
