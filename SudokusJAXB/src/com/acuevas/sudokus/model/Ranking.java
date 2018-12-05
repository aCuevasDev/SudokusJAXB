package com.acuevas.sudokus.model;

import com.acuevas.sudokus.model.users.Users.User;

public class Ranking implements Comparable<Ranking> {

	User user;
	Double meanTime;

	public Ranking(User user) {
		this.user = user;

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
		// TODO Auto-generated method stub
		return 0;
	}

}
