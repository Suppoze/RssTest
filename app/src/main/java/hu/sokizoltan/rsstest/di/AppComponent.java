package hu.sokizoltan.rsstest.di;

import javax.inject.Singleton;

import dagger.Component;
import hu.sokizoltan.rsstest.apitest.view.ApiTestListView;
import hu.sokizoltan.rsstest.json.view.JsonViewerView;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(JsonViewerView jsonViewerView);

    void inject(ApiTestListView apiTestListView);

}
