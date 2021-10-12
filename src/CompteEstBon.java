import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

public class CompteEstBon {

    /**
     * représente l'addition
     */
    private static final BinaryOperator<Integer> PLUS = (integer, integer2) -> integer + integer2;
    /**
     * représente a soustraction
     */
    private static final BinaryOperator<Integer> MINUS = (integer, integer2) -> integer - integer2;
    /**
     * représente la multiplcation
     */
    private static final BinaryOperator<Integer> MUL = (integer, integer2) -> integer * integer2;
    /**
     * représente la division
     */
    private static final BinaryOperator<Integer> DIV = (integer, integer2) -> integer / integer2;
    /**
     * liste de toutes les opérations
     */
    public static final List<BinaryOperator<Integer>> LIST_OPERATIONS = List.of(MUL, DIV, PLUS, MINUS);

    /**
     * liste représentant les calculs fait
     */
    private static List<String> calculs = new ArrayList<>();

    /**
     * variable représentant le nombre d'appel à rechercherSolution
     */
    private static int NB_APPEL = 0;

    public static void main(String[] args) {
        List<Integer> nombres = new ArrayList<>(List.of(1, 3, 5, 1, 10, 3, 2));
        int attendu = 973;
        boolean infructueux = true;
        //variable oscillant entre -1 et 1
        int compteur = -1;
        //variable représentant le nombre d'essaie
        int nbEssaie = 0;
        while (infructueux) {
            infructueux = rechercherSolution(nombres, LIST_OPERATIONS, attendu + nbEssaie * compteur)._1();
            if(compteur == -1) compteur = 1;
            else {
                compteur = -1;
                nbEssaie++;
            }
            if(infructueux) {
                System.out.println("Pas de solutions exacte.");
                System.out.println("Calcul avec " + (attendu + nbEssaie * compteur));
            }
        }
        System.out.println("Le compte est bon!!");
        System.out.println("Calcul :");
        calculs.forEach(System.out::println);
        System.out.println("Nb appel: " + NB_APPEL);
        System.out.println("Commencement test");
        //Nombres.faireTourner(2000);
    }

    public static Pair<Boolean, Integer> rechercherSolution(List<Integer> nombres, List<BinaryOperator<Integer>> operations, int attendu) {
        NB_APPEL++;
        boolean infructueux;
        if(nombres.contains(attendu)) {
            infructueux = false;
        }
        else {
            //tri par ordre croissant
            //nombres.sort((o1, o2) -> Integer.compare(o2, o1));
            infructueux = true;
            int indiceNombres = 0;
            int indiceOperation = 0;
            List<Pair<Integer, Integer>> possibilites = genererPossibilites(nombres);
            while (infructueux && indiceNombres < possibilites.size() - 1) {
                Pair<Integer, Integer> possibilite = possibilites.get(indiceNombres);
                int nombre1 = possibilite._1();
                int nombre2 = possibilite._2();
                BinaryOperator<Integer> operation = operations.get(indiceOperation);
                if(acceptable(nombre1, nombre2, operation)) {
                    int resultat = operation.apply(nombre1, nombre2);
                    List<Integer> newNombres = new ArrayList<>(nombres);
                    newNombres.remove(newNombres.indexOf(nombre1));
                    newNombres.remove(newNombres.indexOf(nombre2));
                    newNombres.add(0, resultat);
                    infructueux = rechercherSolution(newNombres, operations, attendu)._1();
                    if(infructueux) {
                        //si on a pas essayé toutes les opérations on essaie la suivante
                        if(indiceOperation < operations.size() - 1) indiceOperation++;
                        //sinon on reset et on change de nombre
                        else {
                            indiceOperation = 0;
                            indiceNombres++;
                        }
                    } else {
                        //ajout à l'indice 0 afin d'avoir le bon ordre
                        calculs.add(0, nombre1 + afficherOperation(operation) + nombre2 + " = " + resultat);
                    }
                } else {
                    //si on a pas essayé toutes les opérations on essaie la suivante
                    if(indiceOperation < operations.size() - 1) indiceOperation++;
                    //sinon on reset et on change de nombre
                    else {
                        indiceOperation = 0;
                        indiceNombres++;
                    }
                }
            }
        }
        return new Pair<>(infructueux, NB_APPEL);
    }

    public static void reset() {
        NB_APPEL = 0;
    }

    private static void afficherSolution(boolean b){
        if(!b) {
            System.out.println("Le compte est bon!!");
            System.out.println("Calcul :");
            calculs.forEach(System.out::println);
        }
        System.out.println("Nb appel: " + NB_APPEL);
    }

    private static boolean acceptable(int nombre1, int nombre2, BinaryOperator<Integer> operation) {
        if(nombre2 == 0 && operation.equals(DIV)) return false;
        if(((nombre1 == 1) || (nombre2 == 1)) && (operation.equals(DIV) || operation.equals(MUL))) return false;
        if(operation == DIV && nombre1 % nombre2 != 0) return false;
        int resultat = operation.apply(nombre1, nombre2);
        if(resultat < 0) return false;
        return true;
    }

    private static String afficherOperation(BinaryOperator<Integer> operator) {
        if (PLUS.equals(operator)) {
            return "+";
        } else if (MINUS.equals(operator)) {
            return "-";
        } else if (MUL.equals(operator)) {
            return "*";
        } else if (DIV.equals(operator)) {
            return "/";
        }
        return "erreur";
    }

    private static List<Pair<Integer, Integer>> genererPossibilites(List<Integer> nombres) {
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        for(int i=0; i < nombres.size(); i++){
            for(int j=0; j <nombres.size(); j++){
                if(i!=j){
                    pairs.add(new Pair<>(nombres.get(i),nombres.get(j)));
                }
            }
        }
        return pairs;
    }
}