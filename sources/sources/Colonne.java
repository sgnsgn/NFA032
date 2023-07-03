package sources;

public class Colonne extends Paquet {
	
	private static int num = 1;

	public Colonne() {
		this.setNom("colonne n° " + this.getNum());
		Colonne.num++;
	}
	
	
	public boolean ajouterCarteSommetInitialisationPartie(Carte carte) {
		return this.getPaquet().add(carte);
	}
	
	@Override
	public boolean ajouterCarteSommet(Carte carte) {
		if (this.getPaquet().isEmpty() && carte.getValeur() == 13) {
			this.getPaquet().add(carte);
			return true;
		} else if (!this.getPaquet().isEmpty() && consulterCarteSommet().couleurAlternee(carte)) {
			this.getPaquet().add(carte);
			return true;
		}
		return false;
	}

	public int getNum() {
		return num;
	}
	
}
