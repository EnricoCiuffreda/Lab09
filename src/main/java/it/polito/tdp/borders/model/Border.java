package it.polito.tdp.borders.model;

public class Border {
	private Integer dyad;
	private Country p1;
	private Country p2;
	private Integer year;
	private Integer conttype;
	private Integer version;
	
	
	
	/**
	 * @param dyad
	 * @param p1
	 * @param p2
	 * @param year
	 * @param conttype
	 * @param version
	 */
	public Border(Integer dyad, Country p1, Country p2, Integer year, Integer conttype, Integer version) {
		super();
		this.dyad = dyad;
		this.p1 = p1;
		this.p2 = p2;
		this.year = year;
		this.conttype = conttype;
		this.version = version;
	}
	public Integer getDyad() {
		return dyad;
	}
	public void setDyad(Integer dyad) {
		this.dyad = dyad;
	}
	public Country getP1() {
		return p1;
	}
	public void setP1(Country p1) {
		this.p1 = p1;
	}
	public Country getP2() {
		return p2;
	}
	public void setP2(Country p2) {
		this.p2 = p2;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getConttype() {
		return conttype;
	}
	public void setConttype(Integer conttype) {
		this.conttype = conttype;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		if (p1 == null) {
			if (other.p1 != null)
				return false;
		} else if (!p1.equals(other.p1))
			return false;
		if (p2 == null) {
			if (other.p2 != null)
				return false;
		} else if (!p2.equals(other.p2))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Border [p1=" + p1 + ", p2=" + p2 + ", year=" + year + "]";
	}
	
	

}
