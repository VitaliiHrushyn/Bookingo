package com.droozhbooking.service;

import com.droozhbooking.domain.bookingdetails.BookingStatus;
import com.droozhbooking.domain.core.Booking;
import com.droozhbooking.domain.core.Room;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Roman Usyk on 11.09.17.
 */
public interface CustomerService {

    List<Room> getAllRooms();

    List<Room> getAllRoomsWithFilter(Filter filter);

    List<Room> getAllBookedRooms(Long customerID);

    Room getRoom(Long roomId);

    Booking bookRoom(Long customerID, Long roomID, List<RoomService> services, BookingStatus bookingStatus);

    Booking bookRoom(Long customerID, Booking booking, List<RoomService> additionalServices, BookingStatus newBookingStatus);

    /**
     * Dummy class
     */
    static class Filter {

    }

    /**
     * Dummy class
     */
    static interface RoomService {

        BigDecimal getPrice();
    }
}
