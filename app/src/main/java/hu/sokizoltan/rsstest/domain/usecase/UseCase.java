package hu.sokizoltan.rsstest.domain.usecase;

import hu.sokizoltan.rsstest.domain.view.MyView;

public interface UseCase<T extends MyView> {

    void setView(T view);

    void execute();

}
