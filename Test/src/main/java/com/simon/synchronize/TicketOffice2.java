package com.simon.synchronize;

public class TicketOffice2 implements Runnable {

	private Cinema cinema;

	public TicketOffice2(Cinema cinema) {
		this.cinema = cinema;
	}

	@Override
	public void run() {
		cinema.sellTicket2(3);
		cinema.sellTicket2(2);
		cinema.sellTicket2(2);
		cinema.sellTicket2(1);
		cinema.returnTicket2(3);
		cinema.sellTicket2(6);
	}

}
