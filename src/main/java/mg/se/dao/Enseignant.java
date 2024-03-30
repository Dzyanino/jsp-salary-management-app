package mg.se.dao;

public class Enseignant {
	private int numEns;
	private String nom;
	private float nbHeure;
	private float tauxHoraire;
	
	public int getNumEns() {
		return numEns;
	}

	public void setNumEns(int numEns) {
		this.numEns = numEns;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Float getNbHeure() {
		return nbHeure;
	}

	public void setNbHeure(Float nbHeure) {
		this.nbHeure = nbHeure;
	}

	public Float getTauxHoraire() {
		return tauxHoraire;
	}

	public void setTauxHoraire(Float tauxHoraire) {
		this.tauxHoraire = tauxHoraire;
	}

	public Float getSalaire() {
		return nbHeure * tauxHoraire;
	}
}
