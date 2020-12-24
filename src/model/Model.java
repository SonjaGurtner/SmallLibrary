package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public enum Model {
    //i replaced the Model class with an Enum, because it is easier to use than lots of static fields
    INSTANCE;

    public ObservableList<Book> courses = FXCollections.observableArrayList();
    private SortedSet<Integer> ids;
    private int curUID = 0;

    {
        ids = new TreeSet<>();
        ids.add(0);
    }

    public SortedSet<Integer> getIds() {
        return ids;
    }

    public void loadData(List<Book> data) {
        courses.addAll(data);
        for (Book b : courses) {
            ids.add(b.uidProperty().getValue());
        }
        curUID = ids.last() + 1;
    }

    public int newUID() {
        ids.add(curUID);
        return curUID++;
    }

    public int getCurUID() {
        return curUID;
    }
}