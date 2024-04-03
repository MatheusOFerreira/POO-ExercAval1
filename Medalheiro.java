import java.util.ArrayList;

public class Medalheiro {
    private ArrayList<Medalha> medalhas;

    public Medalheiro(){
        medalhas = new ArrayList<Medalha>();
    }

    public boolean cadastraMedalha (Medalha m){
        if(consultaMedalha(m.getCodigo()) !=  null){
            return false;
        }
        return medalhas.add(m);
    }

    public Medalha consultaMedalha(int codigo){
        for(Medalha m : medalhas){
            if(m.getCodigo() == codigo){
                return m;
            }
        }
        return null;
    }
    public ArrayList<Medalha> consultaMedalhas(String modalidade){
        ArrayList<Medalha> aux = new ArrayList<>();
        for(Medalha m : medalhas){
            if(m.getModalidade().equalsIgnoreCase(modalidade)){
                aux.add(m);
            }
        }
        if(aux.isEmpty()){
            return null;
        } else{
            return aux;
        }
    }

    //Métodos novos
    public ArrayList<Medalha> getMedalhas(){
        return medalhas;
    }
}
