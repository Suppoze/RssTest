package hu.sokizoltan.rsstest.jsonview;

import javax.inject.Inject;

import hu.sokizoltan.rsstest.JsonViewerView;
import hu.sokizoltan.rsstest.common.UseCase;

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
