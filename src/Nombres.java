import java.util.*;

public class Nombres {

    private static Map<List<Integer>,Integer> nombres;
    private final List<Integer> valeurs = List.of(1,2,3,4,5,6,7,8,9,10,25,50,75,100);

    public Nombres(){
        nombres = new HashMap<>();
    }

    public void ajouterProbleme(){
        List<Integer> valeursChoisies = new ArrayList<>();
        int nombreAleatoire;
        for(int i=0;i<6;i++){
            nombreAleatoire = (int)(Math.random() * ((13) + 1));
            valeursChoisies.add(valeurs.get(nombreAleatoire));
        }
        int nombreFinal =101 + (int)(Math.random() * ((999 - 101) + 1));
        nombres.put(valeursChoisies,nombreFinal);
    }

    public void toStringNombres(){
        for (List list: nombres.keySet()) {
            System.out.println("Nombres: "+list+"\nRésulat souhaité: "+nombres.get(list)+"\n");
        }
    }

}
