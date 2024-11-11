import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Metodos mtd = new Metodos();
//        mtd.listarValores();
//        mtd.escribirFichero("puntosOrdeados.txt");
        mtd.leerFicheroTXT("/home/yoi/Descargas/codigosUnidades.txt");

    }
}