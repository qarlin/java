package net.carlosu.wildfly.boundary;

import net.carlosu.wildfly.control.NoSuchSeatException;
import net.carlosu.wildfly.control.NotEnoughMoneyException;
import net.carlosu.wildfly.control.SeatBookedException;

public interface TheatreBookerRemote {

	String bookSeat(int seatId) throws NotEnoughMoneyException, NoSuchSeatException, SeatBookedException;

	int getAccountBalance();

}
