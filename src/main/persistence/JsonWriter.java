package persistence;

import model.Dealership;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Based on the class JsonWriter in the supplied Workroom example for CPSC 210:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String target;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String target) {
        this.target = target;
    }

    // MODIFIES: this
    // EFFECTS: opens writer or throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void openWriter() throws FileNotFoundException {
        writer = new PrintWriter(new File(target));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of dealership to file
    public void writeFile(Dealership ds) {
        JSONObject json = ds.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void closeWriter() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
