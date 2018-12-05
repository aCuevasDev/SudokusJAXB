package com.acuevas.sudokus.model;

import com.acuevas.sudokus.model.records.Records;
import com.acuevas.sudokus.model.records.Records.Record;
import com.acuevas.sudokus.model.users.Users.User;

/**
 * Class which relates a User and his Record(s), gets the mean time of all the
 * Record(s) and saves it to compare it with others.
 * 
 * @author Alex
 *
 */
public class Ranking implements Comparable<Ranking> {

	private User user;
	private Double meanTime;

	/**
	 * Constructs a Ranking of the given user and its meanTime from saved Records
	 * 
	 * @param user    The given User from whom create a new Ranking.
	 * @param records Records where to find his games.
	 */
	public Ranking(User user, Records records) {
		this.user = user;
		meanTime = getMeanTime(user, records);
	}

	/**
	 * Gets the mean time from the Record(s) of a specified User
	 * 
	 * @param user    The user you want the mean from.
	 * @param records The Records from where to read the data.
	 * @return Double with either the mean value or null if it's empty.
	 */
	public static Double getMeanTime(User user, Records records) {
		Double mean = records.getRecords().stream().filter(record -> record.getUsername().equals(user.getUsername()))
				.mapToDouble(Record::getTime).average().orElse(mean = Double.NaN);
		return mean.isNaN() ? null : mean;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((meanTime == null) ? 0 : meanTime.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ranking other = (Ranking) obj;
		if (meanTime == null) {
			if (other.meanTime != null)
				return false;
		} else if (!meanTime.equals(other.meanTime))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	/**
	 * toString of Ranking
	 *
	 * @return A string with the values of user and meanTime
	 */
	@Override
	public String toString() {
		return "User: " + user + " Mean time: " + meanTime;
	}

	/**
	 * Override of compareTo to sort all rankings based on their meanTime
	 *
	 * @param a Ranking to compare with
	 * @return an int with where the Ranking has to move (-1,0,1)
	 */
	@Override
	public int compareTo(Ranking ranking) {
		return this.meanTime.compareTo(ranking.meanTime);
	}

	/**
	 * @return the meanTime of this instance
	 */
	public Double getMeanTimeFromInstance() {
		return meanTime;
	}

	/**
	 * @param meanTime the meanTime to set
	 */
	public void setMeanTime(Double meanTime) {
		this.meanTime = meanTime;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
