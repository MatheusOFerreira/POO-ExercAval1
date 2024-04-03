import java.util.ArrayList;

public class Atleta {
    private int numero;
    private String nome;
    private String pais;
    private ArrayList<Medalha> medalhas;

    public Atleta(int numero, String nome, String pais) {
        this.numero = numero;
        this.nome = nome;
        this.pais = pais;
        this.medalhas = new ArrayList<Medalha>();
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public String getPais() {
        return pais;
    }

    @Override
    public String toString() {
        return "Atleta{" +
                "numero=" + numero +
                ", nome='" + nome + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }

    public void adicionaMedalha(Medalha m){
        this.medalhas.add(m);
    }

    public int consultaQuantidadedeMedalhas(){
        return this.medalhas.size();
    }

    //Métodos novos

    public int getTipoMedalha(int tipo) {
        int quantidade = 0;
        for (Medalha medalha : medalhas) {
            if (medalha.getTipo() == tipo) {
                quantidade++;
            }
        }
        return quantidade;
    }

    public boolean temMedalhaTipo(int tipo){
        for(Medalha m : medalhas){
            if(m.getTipo() == tipo){
                return true;
            }
        }
        return false;
    }

}
