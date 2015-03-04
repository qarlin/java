package net.carlosu.wildfly.boundary;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import net.carlosu.wildfly.control.NoSuchSeatException;
import net.carlosu.wildfly.control.NotEnoughMoneyException;
import net.carlosu.wildfly.control.SeatBookedException;
import net.carlosu.wildfly.control.TheatreBox;

/**
 * Session Bean implementation class TheatreBooker
 */
@Stateful
@Remote(TheatreBookerRemote.class)
@AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
public class TheatreBooker implements TheatreBookerRemote{

    @EJB
    private TheatreBox theatreBox;
    private int money;
    
    @PostConstruct
    public void createCustomer(){
    	this.money = 100;
    }
    
    @Override
    public int getAccountBalance(){
    	return money;
    }
    
    @Override
    public String bookSeat(int seatId) throws NotEnoughMoneyException, NoSuchSeatException, SeatBookedException{
    	final int seatPrice = theatreBox.getSeatPrice(seatId);
    	if (seatPrice > money)
    		throw new NotEnoughMoneyException("You don't have enough money to buy this " + seatId + " seat!");
    	theatreBox.buyTicket(seatId);
    	money = money - seatPrice;
    	
    	return "Seat Booked.";
    }

}
