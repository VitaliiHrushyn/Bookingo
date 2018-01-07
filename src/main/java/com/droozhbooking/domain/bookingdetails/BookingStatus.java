package com.droozhbooking.domain.bookingdetails;

/** 
 * Перечисление типов состояния объекта Booking.
 * @version 1.1-snapshot
 * @author fabulous ML expert Roman Usyk as the honorable DroozhbakeeCoders teammate :)
 * @ Содежит список состояний, в котором может пребывать объект Booking.
 */

public enum BookingStatus {

    /**
     * состояние объекта Booking, когда постоялец нажал кнопку "забронировать", 
     * но оно еще не подтверждено отелем (администратором отеля вручную или автоматизированной системой)   
     */
    CREATED(1),
    
    /**
     * состояние объекта, когда бронирование подтверждено отелем.
     */
    CONFIRMED(2),
    
    /**
     * состояние объекта после окончания срока пребывания постояльца в отеле согласно этого бронирования.
     */
    FINISHED(3),
    
    /**
     * состояние объекта после отмены бронирования, отмена осуществляется как одной из сторон
     * (отелем или постояльцем), так и автоматически по истечении срока подтверждения после CREATED.
     */
    CANCELED(4),

    /**
     * Шо-то связанное с выборкой из БД, Рома знает.
     */
    ALL(0);

    private final int value;

    BookingStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
