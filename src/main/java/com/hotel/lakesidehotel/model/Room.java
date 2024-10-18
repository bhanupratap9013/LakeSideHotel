package com.hotel.lakesidehotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal roomPrice;
    private String roomType;

    @Lob
    @Column(name = "photo")
    private Blob photo;

    @Column(name = "is_booked")
    private boolean isBooked = false;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BookedRoom> bookings = new ArrayList<>();

    public void addBooking(BookedRoom bookedRoom) {
        boolean isRoomAvailable = isRoomAvailableForBooking(bookedRoom.getCheckInDate(), bookedRoom.getCheckOutDate());
        if (!isRoomAvailable) {
            throw new IllegalArgumentException("Room is already booked for the selected dates");
        }

        bookedRoom.setRoom(this);
        this.bookings.add(bookedRoom);
        this.isBooked = true;
    }

    private boolean isRoomAvailableForBooking(LocalDate checkIn, LocalDate checkOut) {
        for (BookedRoom booking : this.bookings) {
            if (!(checkOut.isBefore(booking.getCheckInDate()) || checkIn.isAfter(booking.getCheckOutDate()))) {
                return false;
            }
        }
        return true;
    }
}