package pl.levil.model;

/**
 * Created by ${levil} on 2017-03-30.
 */
public class Room {
    private int id;
    private String name;

    public Room(String name) {
        this.name = name;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
