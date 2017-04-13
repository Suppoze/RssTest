package hu.sokizoltan.rsstest.apitest.view;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import hu.sokizoltan.rsstest.R;
import hu.sokizoltan.rsstest.RssTestApplication;
import hu.sokizoltan.rsstest.apitest.model.ApiTestResponse;
import hu.sokizoltan.rsstest.domain.usecase.GetApiTestDataUseCase;
import hu.sokizoltan.rsstest.domain.view.MyView;

public class ApiTestListView extends Fragment implements MyView {

    @BindView(R.id.apitest_list_recyler_view)
    RecyclerView recyclerView;

    @BindView(R.id.apitest_list_swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindDrawable(R.drawable.item_divider)
    Drawable itemDivider;

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

        setupSwipeRefreshLayout();
        setupRecyclerView();
    }

    private void setupSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            apiTestListAdapter.clearData();
            getApiTestDataUseCase.execute();
        });
    }

    private void setupRecyclerView() {
        recyclerView.addItemDecoration(new ItemDividerDecoration(itemDivider));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(apiTestListAdapter);
    }

    public void addToList(ApiTestResponse data) {
        apiTestListAdapter.insertData(data);

    }

    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showLoading(boolean isLoading) {
        swipeRefreshLayout.setRefreshing(isLoading);
    }

    private class ItemDividerDecoration extends RecyclerView.ItemDecoration {

        private Drawable divider;

        public ItemDividerDecoration(Drawable divider) {
            this.divider = divider;
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + divider.getIntrinsicHeight();

                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }
}
