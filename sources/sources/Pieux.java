package sources;

/**
 * Classe des pieux<br>
 * Un pieux est initialement vide.<br>
 * On ajoute les cartes une à une en commençant par l’As<br>
 * @author
 *
 */
public class Pieux extends Paquet {
	
	private static int num = 1;

	public Pieux() {
		this.setNom("pieux n° " + this.getNum());
		Pieux.num++;
	}

	public int getNum() {
		return num;
	}
		
	@Override
	public boolean ajouterCarteSommet(Carte carte) {
		if (this.getPaquet().isEmpty() && carte.getValeur() == 1) {
			this.getPaquet().add(carte);
			return true;
		} else if (!this.getPaquet().isEmpty() && consulterCarteSommet().precedeMemeCouleur(carte)) {
			this.getPaquet().add(carte);
			return true;
		}
		return false;
	}

}
