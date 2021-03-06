package com.aws.JavaG1;

public class Timeslot {
	private int timeSlotID;
	private String timeStart;
	private int movieID;
	private int cinemaID;

	public Timeslot() {
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public int getCinemaID() {
		return cinemaID;
	}

	public void setCinemaID(int cinemaID) {
		this.cinemaID = cinemaID;
	}



	public Timeslot(int timeSlotID, String timeStart) {
		this.timeSlotID = timeSlotID;
		this.timeStart = timeStart;
	}
	
	public Timeslot(int timeSlotID, String timeStart, int m_id, int cine_id) {
		this.timeSlotID = timeSlotID;
		this.timeStart = timeStart;
		this.movieID = m_id;
		this.cinemaID = cine_id;
	}

	public int getTimeSlotID() {
		return timeSlotID;
	}

	public void setTimeSlotID(int timeSlotID) {
		this.timeSlotID = timeSlotID;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	@Override
	public String toString() {
		return "Timeslot{" +
				"timeSlotID=" + timeSlotID +
				", timeStart='" + timeStart + '\'' +
				", movieID=" + movieID +
				", cinemaID=" + cinemaID +
				'}';
	}
}
