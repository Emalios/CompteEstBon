import java.util.*;

public class Nombres {

    /**
     * map recevant la combinaison des 6 nombres et de la solution attendu
     */
    private static Map<List<Integer>, Integer> combinaisons = new HashMap<>();

    /**
     * map représentant tous les nombres pouvant être choisis aléatoirement
     */
    private static final List<Integer> valeurs = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 25, 50, 75, 100);

    /**
     * méthode créant 6 nombres aléatoires entre 1 et 100 et un nombre aléatoire entre 101 et 1000
     * afin de faire tourner tourner CompteEstBon sur ce chiffre
     */
    public static void ajouterProbleme() {
        List<Integer> valeursChoisies = new ArrayList<>();
        int nombreAleatoire;
        for (int i = 0; i < 7; i++) {
            nombreAleatoire = (int) (Math.random() * ((13) + 1));
            valeursChoisies.add(valeurs.get(nombreAleatoire));
        }
        int nombreFinal = 101 + (int) (Math.random() * ((999 - 101) + 1));
        combinaisons.put(valeursChoisies, nombreFinal);
    }

    /**
     * Méthode affichant à l'utilisateur la combianison de nombre et le résultat attendu
     */
    public static String toStringNombres(List<Integer> nombres, int solutionAttendu) {
        return "Combinaisons: " + nombres + " solutionAttendu: " + solutionAttendu;
    }

    public static void faireTourner(int nombreDeRepetition) {
        for(int i = 0; i < nombreDeRepetition; i++){
            ajouterProbleme();
        }
        long sommeIterations = 0;
        int i = 0;
        Set<List<Integer>> keySet = combinaisons.keySet();
        for (List<Integer> nombres : keySet) {
            CompteEstBon.reset();
            int attendu = combinaisons.get(nombres);
            Pair<Boolean, Integer> pair = CompteEstBon.rechercherSolution(nombres, CompteEstBon.LIST_OPERATIONS, attendu);
            if(pair._1()) {
                nombreDeRepetition--;
                System.out.println("[" + i++ + "] " + "Infructueux avec " + toStringNombres(nombres, attendu));
            } else {
                sommeIterations += pair._2();
                System.out.println("[" + i++ + "] " + "Réussi avec " + toStringNombres(nombres, attendu) + " : " + pair._2());
            }
        }
        System.out.println("nbRepet: " + nombreDeRepetition);
        System.out.println("Moyenne d'itération par combinaison réussi: " + sommeIterations / nombreDeRepetition);
    }
}
