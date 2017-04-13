package hu.sokizoltan.rsstest.di;

import javax.inject.Singleton;

import dagger.Component;
import hu.sokizoltan.rsstest.JsonViewerView;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(JsonViewerView jsonViewerView);

}
