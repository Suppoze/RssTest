package hu.sokizoltan.rsstest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.sokizoltan.rsstest.apitest.ApiTestResponse;
import hu.sokizoltan.rsstest.apitest.GetApiTestDataUseCase;
import hu.sokizoltan.rsstest.common.MyView;

public class ApiTestListView extends Fragment implements MyView {

    @BindView(R.id.apitest_list_recyler_view)
    RecyclerView recyclerView;

    @Inject
    GetApiTestDataUseCase getApiTestDataUseCase;

    private ApiTestListAdapter apiTestListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RssTestApplication) getActivity().getApplication()).getAppComponent().inject(this);

        apiTestListAdapter = new ApiTestListAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_apitest_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getApiTestDataUseCase.setView(this);
        getApiTestDataUseCase.execute();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(apiTestListAdapter);
    }

    public void addToList(ApiTestResponse data) {
        apiTestListAdapter.insertData(data);

    }

    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
