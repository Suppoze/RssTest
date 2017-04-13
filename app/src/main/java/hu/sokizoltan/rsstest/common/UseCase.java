package hu.sokizoltan.rsstest.common;

import hu.sokizoltan.rsstest.ApiTestListView;

public interface UseCase<T extends MyView> {

    void setView(ApiTestListView view);

    void execute();

}
