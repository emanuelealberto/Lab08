package it.polito.tdp.borders.model;

public class Country {
 private String stateAbb;
// private String name;
 private int code;
public Country(String stateAbb, int code) {
	super();
	this.stateAbb = stateAbb;
	//this.name = name;
	this.code=code;
}
/**
 * @return the stateAbb
 */
public String getStateAbb() {
	return stateAbb;
}
/**
 * @param stateAbb the stateAbb to set
 */
public void setStateAbb(String stateAbb) {
	this.stateAbb = stateAbb;
}
/**
 * @return the name
 */
/*public String getName() {
	return name;
}*/
/**
 * @param name the name to set
 */
/*public void setName(String name) {
	this.name = name;
}*/
 public String toString(){
	 return stateAbb+" "+code;
 }
/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + code;
	result = prime * result + ((stateAbb == null) ? 0 : stateAbb.hashCode());
	return result;
}
/* (non-Javadoc)
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
	Country other = (Country) obj;
	if (code != other.code)
		return false;
	if (stateAbb == null) {
		if (other.stateAbb != null)
			return false;
	} else if (!stateAbb.equals(other.stateAbb))
		return false;
	return true;
}
 
}
