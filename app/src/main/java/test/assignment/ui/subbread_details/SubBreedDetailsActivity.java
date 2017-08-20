package test.assignment.ui.subbread_details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.assignment.AssignmentApplication;
import test.assignment.R;

public class SubBreedDetailsActivity extends AppCompatActivity implements SubBreedDetailsContract.View {

    private static final String TAG = "SubBreedDetailsActivity";
    public static final String EXTRA_BREED_NAME = "extraBreedName";
    public static final String EXTRA_SUBBREED_NAME = "extraSubBreedName";

    @BindView(R.id.imgSubBreed)
    ImageView imgSubBreed;

    @BindView(R.id.progressLayout)
    View progressLayout;

    @Inject
    SubBreedDetailsPresenter subBreedDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subbreed_details);

        ButterKnife.bind(this);

        Log.d(TAG, "onCreate called");

        DaggerSubBreedDetailsPresenterComponent.builder()
                .dogRepositoryComponent(((AssignmentApplication) getApplication()).getDogRepositoryComponent())
                .subBreedDetailsPresenterModule(new SubBreedDetailsPresenterModule(this))
                .build().inject(this);

        String breedName = getIntent().getStringExtra(EXTRA_BREED_NAME);
        String subbreedName = getIntent().getStringExtra(EXTRA_SUBBREED_NAME);
        if (!TextUtils.isEmpty(breedName) && !TextUtils.isEmpty(subbreedName)) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(String.format("%s %s", breedName, subbreedName));
            }
            subBreedDetailsPresenter.getSubbreedImage(breedName, subbreedName);
        } else {
            finish();
        }
    }

    @Override
    public void onImgRetrievalInProgress() {
        progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onImgRetrieved(String imgUrl) {
        Glide.with(this)
                .load(imgUrl)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        onImgRetrieveError();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressLayout.setVisibility(View.GONE);
                        return false;
                    }
                })
                .crossFade()
                .into(imgSubBreed);
    }

    @Override
    public void onImgRetrieveError() {
        progressLayout.setVisibility(View.GONE);
        Toast.makeText(this, R.string.ooops, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subBreedDetailsPresenter.unsubscribe();
    }
}
