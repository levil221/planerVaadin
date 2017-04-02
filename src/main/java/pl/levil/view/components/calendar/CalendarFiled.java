package pl.levil.view.components.calendar;

import com.vaadin.ui.*;
import pl.levil.model.Reservation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ${levil} on 2017-03-31.
 */
public class CalendarFiled extends CustomComponent {
    private Date date;

    private VerticalLayout layout = new VerticalLayout();
    private Label dayNumber = new Label();

    public CalendarFiled(String info, Date date) {
        this.date = date;
        dayNumber.setCaption(info);
        layout.addComponent(dayNumber);
        setCompositionRoot(layout);
    }

    public VerticalLayout getLayout() {
        return layout;
    }

    public void setLayout(VerticalLayout layout) {
        this.layout = layout;
    }

    public Label getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Label dayNumber) {
        this.dayNumber = dayNumber;
    }

    public  void addEvents(List<Reservation> reservations){
        for(Reservation res : reservations){
            CalendarEventButton but =  new CalendarEventButton(res.getName());
            but.addClickListener(e-> new ResvationDetailsWindow(
                    ((CalendarEventButton)e.getButton()).reservation.getName(),((CalendarEventButton)e.getButton()).reservation
            ));
            layout.addComponent(but);
        }
    }

    public Date getDate() {
        return date;
    }
}
