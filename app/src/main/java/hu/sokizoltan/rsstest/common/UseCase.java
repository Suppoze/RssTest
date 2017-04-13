package hu.sokizoltan.rsstest.common;

public interface UseCase<T extends MyView> {

    void setView(T view);

    void execute();

}
