package hu.sokizoltan.rsstest.json.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.sokizoltan.rsstest.R;
import hu.sokizoltan.rsstest.RssTestApplication;
import hu.sokizoltan.rsstest.domain.usecase.ReadJsonFromFileUseCase;
import hu.sokizoltan.rsstest.domain.view.MyView;

public class JsonViewerView extends Fragment implements MyView {

    @BindView(R.id.json_viewer_textview)
    TextView jsonTextView;

    @Inject
    ReadJsonFromFileUseCase readJsonFromFileUseCase;

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
        readJsonFromFileUseCase.setView(this);
        readJsonFromFileUseCase.execute();
    }

    public void refreshText(String newJson) {
        jsonTextView.setText(newJson);
    }
}
