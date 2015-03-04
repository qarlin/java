package net.carlosu.wildfly.control;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class TheatreBox {
	private Map<Integer, Seat> seats;
	
	@PostConstruct
	public void setupTheatre(){
		seats = new HashMap<>();
		int id = 0;
		for (int i = 0; i < 5; i++) {
			addSeat(new Seat(++id, "Stalls", 40));
			addSeat(new Seat(++id, "Circle", 20));
			addSeat(new Seat(++id, "Balcony", 10));
		}
	}
	
	private void addSeat(Seat seat){
		seats.put(seat.getId(), seat);
	}
	
	public Collection<Seat> getSeats(){
		return Collections.unmodifiableCollection(seats.values());
	}
	
	public int getSeatPrice(int seatId) throws NoSuchSeatException{
		return getSeat(seatId).getPrice();
	}
	
	public synchronized void buyTicket(int seatId) throws SeatBookedException, NoSuchSeatException{
		final Seat seat = getSeat(seatId);
		if (seat.isBooked()) {
			throw new SeatBookedException("Seat " + seatId + " already booked!");
		}
		addSeat(seat.getBookedSeat());
	}
	
	private Seat getSeat(int seatId) throws NoSuchSeatException{
		final Seat seat = seats.get(seatId);
		if (seat == null)
			throw new NoSuchSeatException("Seat " + seatId + " doesn't exist!");
		return seat;
	}
}
