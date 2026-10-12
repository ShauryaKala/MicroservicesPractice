package com.zosh.services.service;

import com.zosh.common_lib.exception.ResourceNotFoundException;
import com.zosh.services.model.Booking;
import com.zosh.services.model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> generateTicketsForBooking(Booking booking);

    Ticket getTicketByNumber(String ticketNumber) throws ResourceNotFoundException;

    List<Ticket> getTicketsByBooking(Long bookingId);

    List<Ticket> getTicketsByPassenger(Long passengerId);

    Ticket cancelTicket(Long ticketId) throws ResourceNotFoundException;

    Ticket markTicketAsUsed(Long ticketId) throws ResourceNotFoundException;

    Ticket refundTicket(Long ticketId) throws ResourceNotFoundException;
}
