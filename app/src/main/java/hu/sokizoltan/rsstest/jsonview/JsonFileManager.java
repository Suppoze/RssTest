package hu.sokizoltan.rsstest.jsonview;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class JsonFileManager {

    @Inject
    Context context;

    private File jsonFile;

    @Inject
    public JsonFileManager() {
    }

    private FileChangeListener fileChangeListener;

    public String saveToFile(String responseString) throws IOException {
        if (jsonFile == null) {
            createJsonFile();
        }
        writeToFile(responseString);

        return responseString;
    }

    public void registerFileChangeListener(FileChangeListener fileChangeListener) {
        this.fileChangeListener = fileChangeListener;
    }

    public void notifyFileChanged() {
        if (fileChangeListener != null) {
            String newJson = readFromFile();
            fileChangeListener.onFileChanged(newJson);
        }
    }

    private void createJsonFile() {
        File path = new File(context.getExternalFilesDir(null) + "/test");
        path.mkdirs();
        jsonFile = new File(path, "test.json");
    }

    private void writeToFile(String rawResponse) throws IOException {
        FileWriter out = new FileWriter(jsonFile);
        out.write(rawResponse);
        out.flush();
        out.close();
    }

    public String readFromFile() {
        if (jsonFile == null) {
            return "No jsonfile yet.";
        }

        int length = (int) jsonFile.length();
        byte[] bytes = new byte[length];

        try {
            FileInputStream in = new FileInputStream(jsonFile);
            in.read(bytes);
            in.close();
        } catch (IOException e) {
            // TODO: catch
            e.printStackTrace();
            return "Error during reading file.";
        }

        return new String(bytes);
    }

    interface FileChangeListener {
        void onFileChanged(String newJson);
    }
}
