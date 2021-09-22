import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class CompteEstBon {

    private static BinaryOperator<Integer> PLUS = (integer, integer2) -> integer + integer2;
    private static BinaryOperator<Integer> MINUS = (integer, integer2) -> integer - integer2;
    private static BinaryOperator<Integer> MUL = (integer, integer2) -> integer * integer2;
    private static BinaryOperator<Integer> DIV = (integer, integer2) -> integer / integer2;

    private static int NB_APPEL = 0;

    public static void main(String[] args) {
        List<Integer> nombres = List.of(9, 1, 6, 8, 2, 3);
        int attendu = 845;
        List<BinaryOperator<Integer>> operations = List.of(PLUS, MINUS, MUL, DIV);
        rechercherSolution(nombres, operations, attendu);
        System.out.println("Nb appel: " + NB_APPEL);
    }

    public static boolean rechercherSolution(List<Integer> nombres, List<BinaryOperator<Integer>> operations, int attendu) {
        boolean infructueux;
        if(nombres.contains(attendu)) {
            infructueux = false;
        }
        else {
            infructueux = true;
            int indiceNombres = 0;
            int indiceOperation = 0;
            List<Pair<Integer, Integer>> possibilites = genererPossibilites(nombres);
            while (infructueux && indiceNombres < possibilites.size() - 1) {
                Pair<Integer, Integer> possibilite = possibilites.get(indiceNombres);
                int nombre1 = possibilite.getFirst();
                int nombre2 = possibilite.getSecound();
                BinaryOperator<Integer> operation = operations.get(indiceOperation);
                if(acceptable(nombre1, nombre2, operation)) {
                    int resultat = operation.apply(nombre1, nombre2);
                    List<Integer> newNombres = new ArrayList<>(nombres);
                    newNombres.remove(newNombres.indexOf(nombre1));
                    newNombres.remove(newNombres.indexOf(nombre2));
                    newNombres.add(0, resultat);
                    //System.out.println("Essaie avec: " + nombre1 + afficherOperation(operation) + nombre2 + "=" + resultat);
                    NB_APPEL++;
                    infructueux = rechercherSolution(newNombres, operations, attendu);
                    if(infructueux) {
                        //System.out.println("Infructueux avec: " + nombre1 + afficherOperation(operation) + nombre2 + "=" + resultat);
                        if(indiceOperation < operations.size() - 1) indiceOperation++;
                        else {
                            indiceOperation = 0;
                            indiceNombres++;
                        }
                    } else {
                        System.out.println(nombre1 + afficherOperation(operation) + nombre2 + "=" + resultat);
                    }
                } else {
                    //System.out.println("Pas accessible avec: " + nombre1 + afficherOperation(operation) + nombre2);
                    if(indiceOperation < operations.size() - 1) indiceOperation++;
                    else {
                        indiceOperation = 0;
                        indiceNombres++;
                    }
                }
            }
        }
        return infructueux;
    }

    private static boolean acceptable(int nombre1, int nombre2, BinaryOperator<Integer> operation) {
        if(nombre2 == 0 && operation.equals(DIV)) return false;
        //if(((nombre1 == 1) || (nombre2 == 1)) && (operation.equals(DIV) || operation.equals(MUL))) return false;
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
