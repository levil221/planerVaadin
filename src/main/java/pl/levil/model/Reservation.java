package pl.levil.model;

import java.util.Date;

/**
 * Created by ${levil} on 2017-03-30.
 */
public class Reservation {

    private int id;
    private Date date;
    private int duration;
    private ReservationStatus status;
    private Room room;
    private String name;
    private String desctiption;

    public Reservation(Date date, int duration, ReservationStatus status, Room room, String name, String desctiption) {
        this.date = date;
        this.duration = duration;
        this.status = status;
        this.room = room;
        this.name = name;
        this.desctiption = desctiption;
    }

    public Reservation() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }
}

