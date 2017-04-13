package hu.sokizoltan.rsstest.apitest;

import javax.inject.Inject;

import hu.sokizoltan.rsstest.JsonViewerView;
import hu.sokizoltan.rsstest.common.UseCase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GetApiTestDataUseCase implements UseCase<JsonViewerView> {

    @Inject
    ApiTestRepository apiTestRepository;

    private JsonViewerView jsonViewerView;

    @Inject
    public GetApiTestDataUseCase() {
    }

    @Override
    public void setView(JsonViewerView view) {
        jsonViewerView = view;
    }

    @Override
    public void execute() {
        apiTestRepository.getApiTestData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNext, this::onError, this::onCompleted);
    }

    private void onNext(ApiTestResponse apiTestResponse) {
        jsonViewerView.addResponse(apiTestResponse.getImageUrl());
    }

    private void onError(Throwable throwable) {
        jsonViewerView.showToast(throwable.getMessage());
        throwable.printStackTrace();
    }

    private void onCompleted() {
    }
}

