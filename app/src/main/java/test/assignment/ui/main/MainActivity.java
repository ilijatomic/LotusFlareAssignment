package test.assignment.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
import test.assignment.data.BreedModel;
import test.assignment.ui.subbreed.SubBreedActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View, BreedNameAdapter.OnItemClickListener {

    public static final int COLUMN_COUNT = 2;

    private static final String TAG = "MainActivity";

    @BindView(R.id.recyclerViewBreeds)
    RecyclerView recyclerViewBreeds;

    @BindView(R.id.progressLayout)
    View progressLayout;

    @Inject
    MainPresenter mainPresenter;

    BreedNameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

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

        recyclerViewBreeds.setLayoutManager(layoutManager);

        DaggerMainPresenterComponent.builder()
                .dogRepositoryComponent(((AssignmentApplication) getApplication()).getDogRepositoryComponent())
                .mainPresenterModule(new MainPresenterModule(this))
                .build().inject(this);

        mainPresenter.loadAllBreeds(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                mainPresenter.loadAllBreeds(true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAllBreedsStartsLoading() {
        showProgresss();
    }

    @Override
    public void onAllBreedsLoaded(List<BreedModel> breedModelList) {
        hideProgresss();
        List<String> names = CommonUtils.getBreedModelsNames(breedModelList);
        if (adapter != null) {
            adapter.setItems(names);
        } else {
            adapter = new BreedNameAdapter(this);
            adapter.setItems(names);
            recyclerViewBreeds.setAdapter(adapter);
        }
    }

    private void hideProgresss() {
        progressLayout.setVisibility(View.GONE);
        recyclerViewBreeds.setVisibility(View.VISIBLE);
    }

    private void showProgresss() {
        progressLayout.setVisibility(View.VISIBLE);
        recyclerViewBreeds.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onAllBreedsLoadError() {
        hideProgresss();
        Toast.makeText(this, R.string.ooops, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClicked(String item) {
        Log.d(TAG, "onBreedNameClicked: " + item);
        Intent intent = new Intent(this, SubBreedActivity.class);
        intent.putExtra(SubBreedActivity.EXTRA_BREED_NAME, item);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.unsubscribe();
    }
}
