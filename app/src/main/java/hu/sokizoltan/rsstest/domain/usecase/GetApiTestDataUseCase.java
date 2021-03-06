package hu.sokizoltan.rsstest.domain.usecase;

import javax.inject.Inject;
import javax.inject.Singleton;

import hu.sokizoltan.rsstest.apitest.model.ApiTestResponse;
import hu.sokizoltan.rsstest.apitest.view.ApiTestListView;
import hu.sokizoltan.rsstest.domain.ApiTestRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class GetApiTestDataUseCase implements UseCase<ApiTestListView> {

    @Inject
    ApiTestRepository apiTestRepository;

    private ApiTestListView apiTestListView;

    @Inject
    public GetApiTestDataUseCase() {
    }

    @Override
    public void setView(ApiTestListView view) {
        apiTestListView = view;
    }

    @Override
    public void execute() {
        apiTestListView.showLoading(true);
        apiTestRepository.getApiTestData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNext, this::onError, this::onCompleted);
    }

    private void onNext(ApiTestResponse apiTestResponse) {
        apiTestListView.addToList(apiTestResponse);
    }

    private void onError(Throwable throwable) {
        apiTestListView.showError(throwable.getMessage());
        apiTestListView.showLoading(false);
        throwable.printStackTrace();
    }

    private void onCompleted() {
        apiTestListView.showLoading(false);
    }
}

