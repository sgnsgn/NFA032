package jouer;
	
import sources.Jeu;

public class Main {
	
	public static void main(String[] args) {
		boolean check = false;
		do {
		try {
			Jeu test = new Jeu();
			check=true;
		} catch (RuntimeException ex) {
			System.out.println(ex.getMessage());
		}
		} while (!check);		
}
}



