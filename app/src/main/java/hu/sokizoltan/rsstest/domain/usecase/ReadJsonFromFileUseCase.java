package hu.sokizoltan.rsstest.domain.usecase;

import javax.inject.Inject;
import javax.inject.Singleton;

import hu.sokizoltan.rsstest.json.data.JsonFileManager;
import hu.sokizoltan.rsstest.json.view.JsonViewerView;

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
