package pl.levil.model.util;

import pl.levil.model.Months;
import pl.levil.model.Reservation;
import pl.levil.model.ReservationStatus;
import pl.levil.model.Room;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import java.util.Date;
import java.util.List;

/**
 * Created by levil on 2017-03-30.
 */
public class DataProvaider {

    //region Singleton
    private static DataProvaider instance;
    public static DataProvaider get(){
        if(instance == null) instance = new DataProvaider();
        return instance;
    }

    private DataProvaider(){}

    //endregion

    private WebTarget webTarget = ClientBuilder.newClient().target("http://0.0.0.0:8082");
    //region Reservation API acces
    public List<Reservation> getAllReservations(){
        return webTarget.path("reservations/all").
                request().
                get(new GenericType<List<Reservation>>(){});
    }
    public List<Reservation> getAllReservations(Date date){
        return webTarget.path("reservations/"+date.toString()).
                request().
                get(new GenericType<List<Reservation>>(){});
    }
    public List<Reservation> getAllReservations(Months month) {
        return webTarget.path("reservations/"+month.getIndex()).
                request().
                get(new GenericType<List<Reservation>>(){});
    }

    public List<Reservation> getAllReservationsWithStatus(ReservationStatus status){
        return webTarget.path("reservations/"+status.name()).
                request().
                get(new GenericType<List<Reservation>>(){});
    }

    public void addReservation(Reservation reservation){
        webTarget.path("reservations/reseravtion/add").
                request(MediaType.APPLICATION_JSON_TYPE).
                post(Entity.entity(reservation,MediaType.APPLICATION_JSON_TYPE),Reservation.class);
    }

    public void updateReservation(Reservation reservation){
        webTarget.path("reservations/reservation/"+reservation.getId()).
                request(MediaType.APPLICATION_JSON_TYPE).
                post(Entity.entity(reservation,MediaType.APPLICATION_JSON_TYPE),Reservation.class);
    }

    public void removeReservation(Reservation reservation){
        webTarget.path("reservations/reservation/remove/"+reservation.getId()).
                request(MediaType.APPLICATION_JSON_TYPE).
                post(Entity.entity(reservation,MediaType.APPLICATION_JSON_TYPE),Reservation.class);
    }

    //endregion

    //region Room API acces
    public List<Room> getAllRooms(){
        return webTarget.path("rooms/all").request().get(new GenericType<List<Room>>(){});
    }


    //endregion
}
