package sources;

import interfaceGraphique.CarteAffichable;

/**
 * 
 * @author
 *
 */
public class Carte implements CarteAffichable{
	/**
	 * <ul>
	 * <li>11 representera un Valet</li>
	 * <li>12 representera une Reine</li>
	 * <li>13 representera un Roi</li>
	 * </ul>
	 */
	private int valeur;
	private String couleur;
	private String nomfich;

	public Carte(int value, String color) {
		if (value < 1 || value > 13)
			throw new IllegalArgumentException("La valeur doit etre comprise entre 1 et 13");
		if (color != "pique" && color != "coeur" && color != "carreau" && color != "trefle")
			throw new IllegalArgumentException(
					"La couleur saisie n'est pas acceptée, il faut saisir : pique, coeur, carreau ou trefle");
		this.valeur = value;
		this.couleur = color;
		nomfich = this.toString()+".png";
	}

	public int getValeur() {
		return valeur;	
	}

	public String getCouleur() {
		return couleur;
	}

	/**
	 * Methode compareTo
	 * <ul>
	 * <li>Si la carte comparee (<i>l'objet sur lequel on appelle la methode</i>)
	 * est plus grande que la carte avec laquelle on la compare, la methode retourne
	 * +1</li>
	 * <li>Si la carte comparee est plus petite la methode retourne -1</li>
	 * <li>Si la carte comparee est plus grande la methode retourne 0</li>
	 * </ul>
	 * 
	 * @param carteAcomparer
	 */
	public int compareTo(Carte carteAComparer) {
		if (this.valeur > carteAComparer.valeur) {
			return +1;
		} else if (this.valeur < carteAComparer.valeur) {
			return -1;
		} else
			return 0;
	}

	/**
	 * Cette methode verifiera que la carte passee en parametre
	 * est bien la suivante de celle sur laquelle la methode est appelee.
	 * @param carte
	 * @return
	 */
	public boolean precedeMemeCouleur(Carte carte) {
		if (this.getValeur() != (carte.getValeur() - 1)) {
			return false;
		}
		else if (this.getCouleur() == carte.couleur)
			return true;
		return false;
	}
	
	
	public boolean couleurAlternee(Carte carte) {
		if (this.getValeur() != (carte.getValeur() + 1)) {
			return false;
		}
		switch (carte.getCouleur()) {
		case "pique":
			if (this.getCouleur() == "coeur" || this.getCouleur() == "carreau") {
				return true;
			} else
				break;
		case "trefle":
			if (this.getCouleur() == "coeur" || this.getCouleur() == "carreau") {
				return true;
			} else
				break;
		case "coeur":
			if (this.getCouleur() == "pique" || this.getCouleur() == "trefle") {
				return true;
			} else
				break;
		case "carreau":
			if (this.getCouleur() == "pique" || this.getCouleur() == "trefle") {
				return true;
			} else
				break;
		}
		return false;
	}
		
	
	@Override
	public String getNomDeFichierPNG() {
		return nomfich;
	}
	
	public void retournerCarte() {
		if (this != null){
		if (nomfich.equals("dos.png")){
			nomfich = toString()+".png";
		}
		else nomfich = "dos.png";
		}
	}
	
	public String toString() {
		if (this.valeur == 1) {
			return "as" + "_" + "de" + "_" + this.couleur;
		}
		// 11 valet
		if (this.valeur == 11) {
			return "valet" + "_" + "de" + "_" + this.couleur;
		}
		// 12 reine
		if (this.valeur == 12) {
			return "dame" + "_" + "de" + "_" + this.couleur;
		}
		// 13 roi
		if (this.valeur == 13) {
			return "roi" + "_" + "de" + "_" + this.couleur;
		}
		else
			return this.valeur + "_" + "de" + "_" + this.couleur;
		}
			
}
