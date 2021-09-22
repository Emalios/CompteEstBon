import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nombres {

    private List<Map<List<Integer>,Integer>> nombres;
    private final List<Integer> valeurs = List.of(1,2,3,4,5,6,7,8,9,10,25,50,75,100);

    public Nombres(){
        nombres = new ArrayList<>();
    }

    public void ajouterProbleme(){
        List<Integer> valeursChoisies = new ArrayList<>();
        for(int i=0;i<=6;i++){
            int nombreAleatoire = (int)(Math.random() * ((14) + 1));
            valeursChoisies.add(valeurs.get(nombreAleatoire));
        }
        int nombreFinal =101 + (int)(Math.random() * ((999 - 101) + 1));
        Map map = new HashMap();
        map.put(valeursChoisies,nombreFinal);
        nombres.add(map);
    }
}
