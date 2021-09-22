import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class CompteEstBon {

    public static void main(String[] args) {
        List<Integer> nombres = List.of(9, 1, 6, 8, 2, 3);
        int attendu = 845;
        List<BinaryOperator<Integer>> operations = List.of(
                (integer, integer2) -> integer + integer2,
                (integer, integer2) -> integer * integer2,
                (integer, integer2) -> integer / integer2,
                (integer, integer2) -> integer - integer2
        );
        System.out.println(nombres);
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
            while (infructueux && indiceNombres < possibilites.size()) {
                Pair<Integer, Integer> possibilite = possibilites.get(indiceNombres);
                int nombre1 = possibilite.getFirst();
                int nombre2 = possibilite.getSecound();
                BinaryOperator<Integer> operation = operations.get(indiceOperation);
                int resultat = operation.apply(nombre1, nombre2);
                if(resultat >= 0) {
                    List<Integer> newNombres = new ArrayList<>(nombres);
                    newNombres.remove(nombre1);
                    newNombres.remove(nombre2);
                    newNombres.add(0, resultat);
                    infructueux = rechercherSolution(newNombres, operations, attendu);
                    if(infructueux) {
                        if(indiceOperation < operations.size()) indiceOperation++;
                        else {
                            indiceOperation = 0;
                            indiceNombres++;
                        }
                    }
                }
            }
        }
        return infructueux;
    }

    private static List<Pair<Integer, Integer>> genererPossibilites(List<Integer> nombres) {
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        return pairs;
    }

}
