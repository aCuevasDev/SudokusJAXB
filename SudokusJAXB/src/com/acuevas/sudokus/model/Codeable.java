package com.acuevas.sudokus.model;

public abstract class Codeable {
	private String code;
	private long index;

	public Codeable() {
		code = this.getClass().getSimpleName().substring(0, 3).toUpperCase() + index++;
	}

	private void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
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
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		if (!(obj instanceof Codeable))
			return false;
		Codeable other = (Codeable) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
