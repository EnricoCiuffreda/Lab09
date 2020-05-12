package it.polito.tdp.borders.model;

public class Country {
private String StateAbb;
private Integer CCode;
private String StateName;
private int collegamentifino;
/**
 * @param stateAbb
 * @param cCode
 * @param stateName
 */
public Country(String stateAbb, Integer cCode, String stateName) {
	super();
	StateAbb = stateAbb;
	CCode = cCode;
	StateName = stateName;
}
public String getStateAbb() {
	return StateAbb;
}
public void setStateAbb(String stateAbb) {
	StateAbb = stateAbb;
}
public Integer getCCode() {
	return CCode;
}
public void setCCode(Integer cCode) {
	CCode = cCode;
}
public String getStateName() {
	return StateName;
}
public void setStateName(String stateName) {
	StateName = stateName;
}




public int getCollegamentifino() {
	return collegamentifino;
}
public void setCollegamentifino(int collegamentifino) {
	this.collegamentifino = collegamentifino;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((CCode == null) ? 0 : CCode.hashCode());
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
	Country other = (Country) obj;
	if (CCode == null) {
		if (other.CCode != null)
			return false;
	} else if (!CCode.equals(other.CCode))
		return false;
	return true;
}
@Override
public String toString() {
	return "Country [StateName=" + StateName + "]";
}




}
