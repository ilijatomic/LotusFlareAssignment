package test.assignment.ui.subbreed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.assignment.AssignmentApplication;
import test.assignment.BuildConfig;
import test.assignment.R;
import test.assignment.commons.ui.BreedNameAdapter;
import test.assignment.commons.utils.CommonUtils;
import test.assignment.data.SubBreedModel;
import test.assignment.ui.subbread_details.SubBreedDetailsActivity;

public class SubBreedActivity extends AppCompatActivity implements SubBreedContract.View, BreedNameAdapter.OnItemClickListener {

    public static final int COLUMN_COUNT = 2;

    private static final String TAG = "SubBreedActivity";
    public static final String EXTRA_BREED_NAME = "extraBreedName";

    @BindView(R.id.recyclerViewSubBreeds)
    RecyclerView recyclerViewSubBreeds;

    @BindView(R.id.txtEmptySubBreeds)
    TextView txtEmptySubBreeds;

    @BindView(R.id.progressLayout)
    View progressLayout;

    @Inject
    SubBreedPresenter subBreedPresenter;

    BreedNameAdapter adapter;

    private String breedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subbreed);

        ButterKnife.bind(this);

        Log.d(TAG, "onCreate called");

        RecyclerView.LayoutManager layoutManager;
        switch (BuildConfig.LIST_TYPE) {
            case VERTICAL_LIST:
                layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                break;
            case GRID:
                layoutManager = new GridLayoutManager(this, COLUMN_COUNT);
                break;
            default:
                layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                break;
        }

        recyclerViewSubBreeds.setLayoutManager(layoutManager);

        DaggerSubBreedPresenterComponent.builder()
                .dogRepositoryComponent(((AssignmentApplication) getApplication()).getDogRepositoryComponent())
                .subBreedPresenterModule(new SubBreedPresenterModule(this))
                .build().inject(this);

        breedName = getIntent().getStringExtra(EXTRA_BREED_NAME);
        if (!TextUtils.isEmpty(breedName)) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(breedName);
            }
            txtEmptySubBreeds.setText(getString(R.string.no_subbreeds, breedName));
            subBreedPresenter.loadSubBreeds(breedName);
        } else {
            finish();
        }
    }

    @Override
    public void onSubBreedsStartsLoading() {
        showProgresss();
    }

    @Override
    public void onSubBreedsLoaded(List<SubBreedModel> subbreedModelList) {
        hideProgresss();
        List<String> names = CommonUtils.getSubBreedModelsNames(subbreedModelList);
        if (!names.isEmpty()) {
            if (adapter != null) {
                adapter.setItems(names);
            } else {
                adapter = new BreedNameAdapter(this);
                adapter.setItems(names);
                recyclerViewSubBreeds.setAdapter(adapter);
            }
        } else {
            txtEmptySubBreeds.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgresss() {
        progressLayout.setVisibility(View.GONE);
    }

    private void showProgresss() {
        progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSubBreedsLoadError() {
        hideProgresss();
        Toast.makeText(this, R.string.ooops, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClicked(String subBreedName) {
        Log.d(TAG, "onSubBreedNameClicked: " + subBreedName);
        Intent intent = new Intent(this, SubBreedDetailsActivity.class);
        intent.putExtra(SubBreedDetailsActivity.EXTRA_BREED_NAME, breedName);
        intent.putExtra(SubBreedDetailsActivity.EXTRA_SUBBREED_NAME, subBreedName);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subBreedPresenter.unsubscribe();
    }
}
