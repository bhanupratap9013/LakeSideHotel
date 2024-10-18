package com.hotel.lakesidehotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;

@NoArgsConstructor
@Data
public class RoomDTO {
    private Long id;
    private BigDecimal roomPrice;
    private String roomType;
    private boolean isBooked;
    private String photo;
    private List<BookedRoomDTO> bookings;

    public RoomDTO(Long id, BigDecimal roomPrice, String roomType) {
        this.id = id;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
    }

    public RoomDTO(Long id, BigDecimal roomPrice, String roomType, boolean isBooked, byte[] photo, List<BookedRoomDTO> bookings) {
        this.id = id;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        this.isBooked = isBooked;
        this.bookings = bookings;

        if (photo != null) {
            this.photo = Base64.getEncoder().encodeToString(photo);
        } else {
            this.photo = null;
        }
    }
}