package com.droozhbooking.service;

import com.droozhbooking.domain.bookingdetails.BookingStatus;
import com.droozhbooking.domain.core.Booking;
import com.droozhbooking.domain.core.Hotel;
import com.droozhbooking.domain.core.Room;

import java.util.List;

/**
 * Created by Roman Usyk on 11.09.17.
 */
public interface ClientService {

    Hotel createHotel(Long clientID, Hotel hotel);

    Room createRoom(Long clientID, Room room);

    List<Booking> getAllBookings(Long clientID, BookingStatus bookingStatus);

    List<Booking> getAllHotelBookings(Long clientID, Long hotelID, BookingStatus bookingStatus);

    List<Booking> getAllRoomBookings(Long clientID, Long RoomID, BookingStatus bookingStatus);

    Booking setBookingStatus(Long bookingID, BookingStatus newBookingStatus);

}
