package interfaceGraphique;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Font;
public class InterfaceKlondike{
	
	private static final long serialVersionUID = 1L;
	private InterfaceCarte ic = new InterfaceCarte(7,2);
	private UnTas[] composant = new UnTas[14];
	//public Font font;
	
	public InterfaceKlondike() {
		char[] idents = {' ','P',' ','A','B','C','D','1','2','3',
				'4','5','6','7'};
		char car = '1';
		for (int i=0; i<7; i++) {
			composant[i]=new UnTas(ic,idents[i]);
			ic.addComponent(composant[i]);
		}
		for (int i=7; i<14; i++) {
			composant[i]=new UneColonne(ic,idents[i]);
			ic.addComponent(composant[i]);
		}
		ic.makeGrid();
		ic.pack();
		ic.setVisible(true);
		//changeFont();
	}
	public boolean estIndexPieu(int idx) {
		return (idx>=3 && idx <=6);
	}
	public boolean estIndexIG(int idx) {
		return (idx>=0 && idx <= composant.length);
	}
	public boolean estIndexIGColonne(int idx) {
		return (idx>=7 && idx <= composant.length-1);
	}
	/**
	 * Si {@code num} est compris entre 0 et 6, vide le tas d'indice {@code num} si {@code carte} est {@code null}, sinon fixe {@code carte} comme l'unique carte du tas d'indice {@code num}.
	 * Si {@code num} est compris entre 1 et 7, ajoute {@code carte} si non nulle dans ce tas. Re-affiche la fenêtre.
	 * @param carte carte à ajouter dans
	 * @num indice du tas (emplacement) d'ajout
	 */
	public void ajouterUneCarte(CarteAffichable carte, int num) {
		composant[num].ajouterUneCarte(carte);
	}
	/**
	 * 
	 * @param tab
	 * @param num
	 */
	public void definirLesCartes(CarteAffichable[] tab, int num) {
		composant[num].definirLesCartes(tab);
	}
	public void retirerDesCartes(int nb, int num) {
		composant[num].retirer(nb);
	}
	public void vider(int num) {
		composant[num].vider();
	}
	public void definirCarte(CarteAffichable carte, int num) {
		composant[num].definirCarte(carte);
	}
	
	public void ferme() {
		ic.dispose();
	}
	public void ajouterLesCartes(CarteAffichable[] tab, int num) {
		composant[num].ajouterLesCartes(tab);
	}
}
