import java.util.*;

public class Nombres {

    /**
     * map recevant la combinaison des 6 nombres et de la solution attendu
     */
    private static Map<List<Integer>, Integer> nombres;

    /**
     * map représentant tous les nombres pouvant être choisis aléatoirement
     */
    private static final List<Integer> valeurs = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 25, 50, 75, 100);

    /**
     * constructeur vide
     */
    public Nombres() {
        nombres = new HashMap<>();
    }

    /**
     * méthode créant 6 nombres aléatoires entre 1 et 100 et un nombre aléatoire entre 101 et 1000
     * afin de faire tourner tourner CompteEstBon sur ce chiffre
     */
    public void ajouterProbleme() {
        List<Integer> valeursChoisies = new ArrayList<>();
        int nombreAleatoire;
        for (int i = 0; i < 7; i++) {
            nombreAleatoire = (int) (Math.random() * ((13) + 1));
            valeursChoisies.add(valeurs.get(nombreAleatoire));
        }
        int nombreFinal = 101 + (int) (Math.random() * ((999 - 101) + 1));
        nombres.put(valeursChoisies, nombreFinal);
    }

    /**
     * Méthode affichant à l'utilisateur la combianison de nombre et le résultat attendu
     */
    public void toStringNombres() {
        for (List<Integer> list : nombres.keySet()) {
            System.out.println("Nombres: " + list + "\nRésultat souhaité: " + nombres.get(list) + "\n");
        }
    }
}
