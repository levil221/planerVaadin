package pl.levil.view;

import com.vaadin.ui.*;
import pl.levil.model.Months;
import pl.levil.model.Reservation;
import pl.levil.model.ReservationStatus;
import pl.levil.model.Room;
import pl.levil.model.util.DataProvaider;
import pl.levil.view.components.calendar.CalendarFiled;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * Created by ${levil} on 2017-03-31.
 */

//TODO: dodac obsluge lat przestepnych


public class CalendarComponent extends CustomComponent {

    //region fields
    private Calendar calendar = Calendar.getInstance();
    private Months currentMonth;
    private List<Room> rooms;
    //endregion

    //region components
    private VerticalLayout panel;
    private HorizontalLayout controllLayout;
    private GridLayout calendarGrid = new GridLayout();
    //endregion

    public CalendarComponent() {
        currentMonth = Months.values()[LocalDate.now().getMonth().getValue()];
        calendar.set(LocalDate.now().getYear(), currentMonth.getIndex(), 1);

        panel = new VerticalLayout();
        controllLayout = new HorizontalLayout();
        panel.addComponent(controllLayout);
        panel.addComponent(calendarGrid);
        makeCalendarGrid(currentMonth);
        setCompositionRoot(panel);
    }

    private void makeCalendarGrid(Months month) {
        GridLayout newCalendarGrid = new GridLayout(7, 6);
        List<CalendarFiled> toFill = new ArrayList<>();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int row = 0;
        for (int d = 1; d <= month.getDays(); d++) {
            calendar.set(Calendar.DAY_OF_MONTH,d);
            CalendarFiled field = new CalendarFiled(String.valueOf(d), calendar.getTime());
            newCalendarGrid.addComponent(field);
            toFill.add(field);
            dayOfWeek++;
            if (dayOfWeek > 6) {
                dayOfWeek = 0;
                row++;
            }
        }

        panel.replaceComponent(calendarGrid,newCalendarGrid);
        calendarGrid = newCalendarGrid;
    }

    public void changeMonth(String month) {
        if (month == "next") {
            if (currentMonth.getIndex() == 12) {
                currentMonth = Months.styczeń;
                calendar.set(calendar.get(Calendar.YEAR) - 1900 + 1, currentMonth.getIndex(), 1);
            } else
                currentMonth = Months.values()[currentMonth.getIndex() + 1];

            makeCalendarGrid(currentMonth);
        } else {
            if (currentMonth.getIndex() == 1) {
                currentMonth = Months.grudzień;
                calendar.set(calendar.get(Calendar.YEAR - 1900 + 1), currentMonth.getIndex(), 1);
            } else
                currentMonth = Months.values()[currentMonth.getIndex() - 1];

            makeCalendarGrid(currentMonth);

        }
    }


    private void getReservationsFromServer(Months month) {
        CompletableFuture.supplyAsync(()-> DataProvaider.get().getAllReservations(month))
    }

}
