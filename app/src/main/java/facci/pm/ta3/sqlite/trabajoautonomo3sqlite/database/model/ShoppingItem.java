package facci.pm.ta3.sqlite.trabajoautonomo3sqlite.database.model;

public class ShoppingItem {

    private long id;
    private String name;

    public ShoppingItem(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
