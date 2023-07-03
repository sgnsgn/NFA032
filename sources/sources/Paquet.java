package sources;

import java.util.ArrayList;

/**
 * Classe mere de la hierarchie des paquets de cartes.<br>
 * Elle comporte un attribut ArrayList de Carte.<br>
 * Son constructeur cree un paquet vide.<br>
 * Comporte les methodes suivantes :
 * <ul>
 * <li>Une methode pour ajouter une carte au sommet du paquet.</li>
 * <li>Une methode pour retirer la carte situee au sommet du paquet.</li>
 * <li>Une methode pour consulter la carte situee au sommet du paquet sans la
 * retirer.</li>
 * <li>une methode qui renvoie le nombre de cartes du paquet.</li>
 * <li>une methode toString.</li>
 * </ul>
 * 
 * @author
 *
 */
public class Paquet {

	private ArrayList<Carte> paquet = new ArrayList<Carte>();
	private String nom;

	public Paquet() {
	}

	public String getNom() {
		return nom;
	}

	public ArrayList<Carte> getPaquet() {
		return paquet;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	// methode pour ajouter une carte au sommet du paquet.
	public boolean ajouterCarteSommet(Carte carte) {
		return this.getPaquet().add(carte);
	}

	// methode pour retirer la carte situee au sommet du paquet.
	public Carte retirerCarteSommet() {
		if (!this.getPaquet().isEmpty()) {
			return this.getPaquet().remove(this.getPaquet().size() - 1);
		}
		// trouver une exception plus adequate;
		else
			throw new RuntimeException("Attention il n'est pas possible d'enlever une carte d'un paquet vide");
	}

	// methode pour consulter la carte situee au sommet du paquet sans la retirer.
	public Carte consulterCarteSommet() {
		try {
			this.getPaquet().get(this.getPaquet().size() - 1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Attention le paquet " + this.getNom() + " est vide !");
			this.getNom();
		} catch (RuntimeException e) {
			System.out.println("ERREUR consulterCarteSommet classe paquet de type : " + e);
		}
		return this.getPaquet().get(this.getPaquet().size() - 1);
	}

	// methode qui renvoie le nombre de cartes du paquet.
	public int nombreCartesPaquet() {
		return this.getPaquet().size();
	}

	// methode toString.
	@Override
	public String toString() {
		if (this.nombreCartesPaquet() == 0) {
			return "Le paquet " + this.nom + " est vide";
		} else if (this.nombreCartesPaquet() == 1) {
			return "Le paquet " + this.nom + " est compose de : " + this.nombreCartesPaquet() + " carte"
					+ " qui est la suivante " + this.getPaquet();
		}
		return "Le paquet " + this.nom + " est compose de : " + this.nombreCartesPaquet() + " cartes"
				+ " qui sont les suivantes " + this.getPaquet();
	}

}
