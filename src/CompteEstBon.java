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

    private static int solutionPlusProche;

    public static void main(String[] args) {
        List<Integer> nombres = new ArrayList<>(List.of(50, 5, 1, 6, 5, 6));
        int attendu = 686;
        boolean infructueux = true;
        infructueux = rechercherSolution(nombres, LIST_OPERATIONS, attendu)._1();
        if(infructueux) {
            System.out.println("Pas de solutions exacte pour " + attendu + ". Recherche avec " + solutionPlusProche);
            rechercherSolution(nombres, LIST_OPERATIONS, solutionPlusProche)._1();
        }
        System.out.println("Le compte est bon!!");
        System.out.println("Calcul :");
        calculs.forEach(System.out::println);
        System.out.println("Nb appel: " + NB_APPEL);
        System.out.println("Commencement test");
        Nombres.faireTourner(5000);
    }

    public static Pair<Boolean, Integer> rechercherSolution(List<Integer> nombres, List<BinaryOperator<Integer>> operations, int attendu) {
        NB_APPEL++;
        boolean infructueux = true;
        if(nombres.contains(attendu)) {
            infructueux = false;
        } else if(nombres.size() == 1) {
            int current = nombres.get(0);
            if(Math.abs(current - attendu) < Math.abs(solutionPlusProche - attendu)) solutionPlusProche = current;
        }
        else {
            //tri par ordre croissant
            //nombres.sort((o1, o2) -> Integer.compare(o2, o1));
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