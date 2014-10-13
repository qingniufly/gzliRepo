package com.simon.synchronize;

public class Cinema {

	private long vacanciesCinema1, vacanciesCinema2;
	private final Object ctrlCinema1, ctrlCinema2;

	public Cinema() {
		vacanciesCinema1 = 20;
		vacanciesCinema2 = 20;
		ctrlCinema1 = new Object();
		ctrlCinema2 = new Object();
	}

	public boolean sellTicket1(int number) {
		synchronized (ctrlCinema1) {
			if (number < vacanciesCinema1) {
				vacanciesCinema1 -= number;
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean sellTicket2(int number) {
		synchronized (ctrlCinema2) {
			if (number < vacanciesCinema2) {
				vacanciesCinema2 -= number;
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean returnTicket1(int number) {
		synchronized (ctrlCinema1) {
			vacanciesCinema1 += number;
			return true;
		}
	}

	public boolean returnTicket2(int number) {
		synchronized (ctrlCinema2) {
			vacanciesCinema2 += number;
			return true;
		}
	}

	public long getVacanciesCinema1() {
		return vacanciesCinema1;
	}

	public long getVacanciesCinema2() {
		return vacanciesCinema2;
	}

}
