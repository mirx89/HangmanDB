package models;

public class DatabaseData {
    private int id;
    private String word;
    private String category;

    public DatabaseData(int id, String word, String category) {
        this.id = id;
        this.word = word;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getCategory() {
        return category;
    }
}
