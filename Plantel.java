import java.util.ArrayList;

public class Plantel {
    private ArrayList<Atleta> atletas;

    public Plantel(){
        atletas = new ArrayList<Atleta>();
    }

    public boolean cadastraAtleta(Atleta a){
        if(consultaAtleta(a.getNumero()) == null){
            return atletas.add(a);
        }
        return false;
    }

    public Atleta consultaAtleta(String nome){
        for(Atleta a : atletas){
            if(a.getNome().equalsIgnoreCase(nome)){
                return a;
            }
        }
        return null;
    }

    public Atleta consultaAtleta(int numero){
        for(Atleta a : atletas){
            if(a.getNumero() == numero){
                return a;
            }
        }
        return null;
    }

    //Métodos novos

    public ArrayList<Atleta> getAtletas() {
        return atletas;
    }

    public ArrayList<String> getPaises(){
        ArrayList<String> todosPaises = new ArrayList<>();
        for(Atleta a : atletas){
            if(!todosPaises.contains(a.getPais())){
                todosPaises.add(a.getPais());
            }
        }
        return todosPaises;
    }

}
