public class Datos {
    int cod;
    String nome;
    int puntos;

    public Datos() {
    }

    public Datos(int cod, String nome, int puntos) {
        this.cod = cod;
        this.nome = nome;
        this.puntos = puntos;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public int getPuntos() {
        return puntos;
    }
}
