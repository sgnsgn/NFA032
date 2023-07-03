package sources;

import java.util.*;
import java.util.Map.Entry;

import interfaceGraphique.InterfaceKlondike;

/**
 * Classe qui representante le jeu de 52 cartes ou "paquet distributeur" <br>
 * Elle construit un paquet complet qui garantit la validite et l’unicite de
 * chaque carte d’un jeu.<br>
 * Elle comporte deux methodes :
 * <ul>
 * <li>une methode pour melanger les cartes du paquet</li>
 * </ul>
 * 
 * @author 
 *
 */
public class Jeu extends Paquet {

	private Paquet[] tabPaquet = new Paquet[14];
	private InterfaceKlondike itp;
	private Map<String, Integer> correspondance;
	private String[] choixPossibles1 = { "p", "1", "2", "3", "4", "5", "6", "7" };
	private String[] choixPossiblesPieux = { "a", "b", "c", "d" };

	public Jeu() {

		// creation de l'interface graphique
		Scanner scan = new Scanner(System.in);
		this.itp = new InterfaceKlondike();

		// creation de toutes les cartes
		final String couleur[] = { "pique", "coeur", "carreau", "trefle" };
		for (int i = 0; i < couleur.length; i++) {
			for (int j = 1; j <= 13; j++) {
				getPaquet().add(new Carte(j, couleur[i]));
				this.consulterCarteSommet().retournerCarte();
			}
		}
		// on melange
		this.melangerPaquet(1000);

		// instanciation des paquets
		Pioche pioche = new Pioche();
		Defausse Defausse = new Defausse();
		Pioche piocheGhost = new Pioche();
		Pieux pieuxA = new Pieux();
		Pieux pieuxB = new Pieux();
		Pieux pieuxC = new Pieux();
		Pieux pieuxD = new Pieux();
		Colonne colonne1 = new Colonne();
		Colonne colonne2 = new Colonne();
		Colonne colonne3 = new Colonne();
		Colonne colonne4 = new Colonne();
		Colonne colonne5 = new Colonne();
		Colonne colonne6 = new Colonne();
		Colonne colonne7 = new Colonne();
		// on crée un tableau avec tous les paquets
		Paquet[] tabPaquet = { pioche, Defausse, piocheGhost, pieuxA, pieuxB, pieuxC, pieuxD, colonne1, colonne2,
				colonne3, colonne4, colonne5, colonne6, colonne7 };
		this.setTabPaquet(tabPaquet);

		// creation du tableau associatif
		Map<String, Integer> correspondance = new HashMap<>();
		correspondance.put("p", 1);
		correspondance.put("a", 3);
		correspondance.put("b", 4);
		correspondance.put("c", 5);
		correspondance.put("d", 6);
		correspondance.put("1", 7);
		correspondance.put("2", 8);
		correspondance.put("3", 9);
		correspondance.put("4", 10);
		correspondance.put("5", 11);
		correspondance.put("6", 12);
		correspondance.put("7", 13);
		this.setCorrespondance(correspondance);

		// remplissage des paquets
		for (int i = 0; i < 52; i++) {
			if (i == 0) {
				this.consulterCarteSommet().retournerCarte();
				itp.ajouterUneCarte(this.consulterCarteSommet(), 7);
				colonne1.ajouterCarteSommetInitialisationPartie(this.retirerCarteSommet());
			} else if (i < 3) {
				itp.ajouterUneCarte(this.consulterCarteSommet(), 8);
				colonne2.ajouterCarteSommetInitialisationPartie(this.retirerCarteSommet());
				if (i == 1) {
					this.consulterCarteSommet().retournerCarte();
				}
			} else if (i < 6) {
				itp.ajouterUneCarte(this.consulterCarteSommet(), 9);
				colonne3.ajouterCarteSommetInitialisationPartie(this.retirerCarteSommet());
				if (i == 4) {
					this.consulterCarteSommet().retournerCarte();
				}
			} else if (i < 10) {
				itp.ajouterUneCarte(this.consulterCarteSommet(), 10);
				colonne4.ajouterCarteSommetInitialisationPartie(this.retirerCarteSommet());
				if (i == 8) {
					this.consulterCarteSommet().retournerCarte();
				}
			} else if (i < 15) {
				itp.ajouterUneCarte(this.consulterCarteSommet(), 11);
				colonne5.ajouterCarteSommetInitialisationPartie(this.retirerCarteSommet());
				if (i == 13) {
					this.consulterCarteSommet().retournerCarte();
				}
			} else if (i < 21) {
				itp.ajouterUneCarte(this.consulterCarteSommet(), 12);
				colonne6.ajouterCarteSommetInitialisationPartie(this.retirerCarteSommet());
				if (i == 19) {
					this.consulterCarteSommet().retournerCarte();
				}
			} else if (i < 28) {
				itp.ajouterUneCarte(this.consulterCarteSommet(), 13);
				colonne7.ajouterCarteSommetInitialisationPartie(this.retirerCarteSommet());
				if (i == 26) {
					this.consulterCarteSommet().retournerCarte();
				}
			} else {
				itp.ajouterUneCarte(this.consulterCarteSommet(), 0);
				pioche.ajouterCarteSommet(this.retirerCarteSommet());
			}
		}

		boolean check = true;
		do {
			System.out.println("Saississez un des choix ci-dessous :");
			System.out.println("d pour déplacer une carte");
			System.out.println("m pour monter une carte dans un des pieux");
			System.out.println("p pour piocher une carte");
			System.out.println("a pour abandonner");
			String choix = scan.nextLine();
			if (choix.equalsIgnoreCase("d")) {
				int i = validationSaisieDeplacer1(scan, this.getChoixPossibles1(), this.getChoixPossiblesPieux());
				int j = validationSaisieDeplacer2(scan, this.getChoixPossibles1(), this.getChoixPossiblesPieux());
				deplacerCarte(i, j);
				scan.nextLine();
			} else if (choix.equalsIgnoreCase("m")) {
				monterCarte(scan);
				scan.nextLine();
			} else if (choix.equalsIgnoreCase("p")) {
				if (this.tabPaquet[0].nombreCartesPaquet() != 0) {
					piocherCarte();
				} else {
					for (int i = 0; i < tabPaquet[1].nombreCartesPaquet(); i++) {
						this.tabPaquet[1].consulterCarteSommet().retournerCarte();
						this.tabPaquet[0].ajouterCarteSommet(tabPaquet[1].retirerCarteSommet());
					}
					itp.vider(1);
				}
			} else if (choix.equalsIgnoreCase("a")) {
				itp.ferme();
				check = false;
			} else {
				System.out.println("Votre saisie est incorrecte, veuillez réessayer" + "\n");
			}
		} while (check);
		System.out.println("Merci d'avoir utilisé mon programme");
	}

	public Paquet[] getTabPaquet() {
		return tabPaquet;
	}

	public void setTabPaquet(Paquet[] tabPaquet) {
		this.tabPaquet = tabPaquet;
	}

	public Map<String, Integer> getCorrespondance() {
		return correspondance;
	}

	public void setCorrespondance(Map<String, Integer> correspondance) {
		this.correspondance = correspondance;
	}

	public String[] getChoixPossibles1() {
		return choixPossibles1;
	}

	public String[] getChoixPossiblesPieux() {
		return choixPossiblesPieux;
	}

	public void melangerPaquet(int nbSwap) {
		for (int i = 0; i < nbSwap; i++) {
			Carte carteTemporaire = new Carte(1, "coeur");
			Random random = new Random();
			int rdm = random.nextInt(52);
			carteTemporaire = getPaquet().get(rdm);
			getPaquet().remove(rdm);
			getPaquet().add(0, carteTemporaire);
		}
	}

	public void deplacerCarte(int i, int k) {
		try {
			Carte carte = this.tabPaquet[i].consulterCarteSommet();
			if (this.tabPaquet[k].ajouterCarteSommet(carte)) {
				this.tabPaquet[i].retirerCarteSommet();
				itp.retirerDesCartes(1, i);
				itp.ajouterUneCarte(this.tabPaquet[k].consulterCarteSommet(), k);
				if (this.tabPaquet[i].nombreCartesPaquet() != 0) {
					if (i > 2 && i < 7) {
						itp.retirerDesCartes(1, i);
						itp.definirCarte(this.tabPaquet[i].consulterCarteSommet(), i);
					} else if (this.tabPaquet[i].consulterCarteSommet().getNomDeFichierPNG().equals("dos.png")) {
						this.tabPaquet[i].consulterCarteSommet().retournerCarte();
						itp.retirerDesCartes(1, i);
						itp.ajouterUneCarte(this.tabPaquet[i].consulterCarteSommet(), i);
					}
				}
			} else {
				System.out.println("Attention vous ne pouvez pas déplacer cette carte");
				System.out.println();
			}
		} catch (RuntimeException e) {
			System.out.println();
		}
	}

	public void monterCarte(Scanner scan) {
		boolean check = false;
		do {
			System.out.println("Merci de saisir la référence du paquet de la carte à monter");
			String saisie = scan.next();
			if (saisie.length() > 1)
				System.out.println("Vous avez saisi trop de lettres");
			else {
				boolean checkbis = false;
				for (String ListeHorsPieux : choixPossibles1) {
					if (saisie.equalsIgnoreCase(ListeHorsPieux)) {
						for (Entry<String, Integer> entry : getCorrespondance().entrySet()) {
							String cle = entry.getKey();
							Integer valeur = entry.getValue();
							if (cle.equalsIgnoreCase(saisie)) {
								try {
									if (this.tabPaquet[valeur] == null) {
										System.out.println("Aucune carte n'est présente dans ce paquet");
									} else {
										Carte carte = this.tabPaquet[valeur].consulterCarteSommet();
										for (int i = 3; i < choixPossiblesPieux.length + 3; i++) {
											if (this.tabPaquet[i].ajouterCarteSommet(
													this.tabPaquet[valeur].consulterCarteSommet())) {
												this.tabPaquet[valeur].retirerCarteSommet();
												itp.retirerDesCartes(1, valeur);
												itp.ajouterUneCarte(this.tabPaquet[i].consulterCarteSommet(), i);
												if (this.tabPaquet[valeur].nombreCartesPaquet() != 0) {
													if (this.tabPaquet[valeur].consulterCarteSommet()
															.getNomDeFichierPNG().equals("dos.png")) {
														this.tabPaquet[valeur].consulterCarteSommet().retournerCarte();
														itp.retirerDesCartes(1, valeur);
														itp.ajouterUneCarte(
																this.tabPaquet[valeur].consulterCarteSommet(), valeur);
													}
												}
												check = true;
												checkbis = true;
												break;
											}
										}
										if (!checkbis) {
											System.out.println("Il n'est pas possible de monter cette carte");
											System.out.println();
											check = true;
										}
									}
								} catch (NullPointerException e) {
									System.out.println("Aucune carte n'est présente dans ce paquet");
									System.out.println();
								} catch (IndexOutOfBoundsException e) {
									System.out.println();
								}
							}
						}
					}
				}
			}
		} while (!check);
	}

	public void piocherCarte() {
		this.tabPaquet[0].consulterCarteSommet().retournerCarte();
		if (this.tabPaquet[0].consulterCarteSommet().getValeur() == 1) {
			for (int i = 3; i < choixPossiblesPieux.length + 3; i++) {
				if (this.tabPaquet[i].ajouterCarteSommet(this.tabPaquet[0].consulterCarteSommet())) {
					this.tabPaquet[0].retirerCarteSommet();
					itp.vider(1);
					itp.ajouterUneCarte(this.tabPaquet[i].consulterCarteSommet(), i);
					break;
				}
			}
		} else {
			this.tabPaquet[1].ajouterCarteSommet(this.tabPaquet[0].retirerCarteSommet());
			itp.definirCarte(this.tabPaquet[1].consulterCarteSommet(), 1);
		}
	}

	public int validationSaisieDeplacer1(Scanner scan, String[] choixPossible1, String[] choixPossible2) {
		boolean check = false;
		int indiceDansIG = -1;
		do {
			System.out.println("Merci de saisie la référence du paquet de la carte à déplacer");
			String saisie = scan.next();
			if (saisie.length() > 1)
				System.out.println("Vous avez saisi trop de lettres");
			else {
				boolean checkbis = false;
				for (String ListeHorsPieux : choixPossible1) {
					if (saisie.equalsIgnoreCase(ListeHorsPieux)) {
						for (Entry<String, Integer> entry : getCorrespondance().entrySet()) {
							String cle = entry.getKey();
							Integer valeur = entry.getValue();
							if (cle.equalsIgnoreCase(saisie)) {
								check = true;
								checkbis = true;
								indiceDansIG = valeur;
							}
						}
					}
				}
				for (String ListePieux : choixPossible2) {
					if (saisie.equalsIgnoreCase(ListePieux)) {
						for (Entry<String, Integer> entry : getCorrespondance().entrySet()) {
							String cle = entry.getKey();
							Integer valeur = entry.getValue();
							if (cle.equalsIgnoreCase(saisie)) {
								check = true;
								checkbis = true;
								indiceDansIG = valeur;
							}
						}
					}
				}
				if (!checkbis) {
					System.out.println("Ce paquet n'existe pas !! ");
				}
			}
		} while (!check);
		return indiceDansIG;
	}

	public int validationSaisieDeplacer2(Scanner scan, String[] choixPossible1, String[] choixPossible2) {
		boolean check = false;
		int indiceDansIG = -1;
		do {
			System.out.println("Merci de choisir le paquet de destination");
			String saisie = scan.next();
			if (saisie.length() > 1)
				System.out.println("Vous avez saisi trop de lettres");
			else if (saisie.equalsIgnoreCase("p")) {
				System.out.println("Vous ne pouvez pas déplacer une carte sur la pioche");
				System.out.println();
			} 
			else {
				boolean checkbis = false;
				for (String ListeHorsPieux : choixPossible1) {
					if (saisie.equalsIgnoreCase(ListeHorsPieux)) {
						for (Entry<String, Integer> entry : getCorrespondance().entrySet()) {
							String cle = entry.getKey();
							Integer valeur = entry.getValue();
							if (cle.equalsIgnoreCase(saisie)) {
								check = true;
								checkbis = true;
								indiceDansIG = valeur;
							}
						}
					}
				}
				for (String ListePieux : choixPossible2) {
					if (saisie.equalsIgnoreCase(ListePieux)) {
						for (Entry<String, Integer> entry : getCorrespondance().entrySet()) {
							String cle = entry.getKey();
							Integer valeur = entry.getValue();
							if (cle.equalsIgnoreCase(saisie)) {
								check = true;
								checkbis = true;
								indiceDansIG = valeur;
							}
						}
					}
				}
				if (!checkbis) {
					System.out.println("Ce paquet n'existe pas !! ");
				}
			}
		} while (!check);
		return indiceDansIG;
	}
}
