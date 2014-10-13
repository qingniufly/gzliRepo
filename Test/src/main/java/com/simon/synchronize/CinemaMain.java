package com.simon.synchronize;

public class CinemaMain {

	public static void main(String[] args) {
		Cinema cinema = new Cinema();
		TicketOffice1 office1 = new TicketOffice1(cinema);
		TicketOffice2 office2 = new TicketOffice2(cinema);
		Thread t1 = new Thread(office1, "TicketOffice1");
		Thread t2 = new Thread(office2, "TicketOffice2");
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Room1 Vacancies : %d\n", cinema.getVacanciesCinema1());
		System.out.printf("Room2 Vacancies : %d\n", cinema.getVacanciesCinema2());
	}

}
