package hu.sokizoltan.rsstest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.sokizoltan.rsstest.apitest.GetApiTestDataUseCase;
import hu.sokizoltan.rsstest.common.MyView;

public class JsonViewerView extends Fragment implements MyView {

    @BindView(R.id.json_viewer_textview)
    TextView jsonTextView;

    @Inject
    GetApiTestDataUseCase getApiTestDataUseCase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RssTestApplication) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_json_viewer, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getApiTestDataUseCase.setView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getApiTestDataUseCase.execute();
    }

    public void addResponse(String imageUrl) {
        jsonTextView.append(imageUrl);
    }

    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
