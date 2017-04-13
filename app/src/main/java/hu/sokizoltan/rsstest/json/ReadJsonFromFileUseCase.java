package hu.sokizoltan.rsstest.json;

import javax.inject.Inject;
import javax.inject.Singleton;

import hu.sokizoltan.rsstest.JsonViewerView;
import hu.sokizoltan.rsstest.common.UseCase;

@Singleton
public class ReadJsonFromFileUseCase implements UseCase<JsonViewerView> {

    @Inject
    JsonFileManager jsonFileManager;

    private JsonViewerView view;

    @Inject
    public ReadJsonFromFileUseCase() {
    }

    @Override
    public void setView(JsonViewerView view) {
        this.view = view;
    }

    @Override
    public void execute() {
        jsonFileManager.registerFileChangeListener(view::refreshText);
    }
}
