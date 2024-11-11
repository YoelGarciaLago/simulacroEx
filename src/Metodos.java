import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Metodos {
    private final String driver = "jdbc:postgresql:";
    private final String host = "//localhost:"; // tamen poderia ser una ip como "192.168.1.14"
    private final String porto = "5432";
    private final String sid = "postgres";
    private final String usuario = "postgres";
    private final String password = "postgres";
    private final String url = driver + host+ porto + "/" + sid;
    private Connection conn;
    private ArrayList<Integer> listaUnidades = new ArrayList<>();
    private ArrayList<Datos> listaDatos = new ArrayList<>();

    private Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url,usuario,password);
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void listarValores(){
        String command = "SELECT * from adeptasororitas ORDER BY puntos DESC";
        ResultSet rs = null;
        try {
            Connection conn = conectar();
            rs = conn.prepareStatement(command).executeQuery();
            while (rs.next()) {
                //System.out.println("Unidade --> " + rs.getString(2) + " Puntos --> " + rs.getInt(3));
               // int cod = rs.getInt(1);
               // String nom = rs.getString(2);
              //  int pts = rs.getInt(3);
                listaDatos.add(new Datos(rs.getInt(1),rs.getString(2),rs.getInt(3)));
            }
        }catch (SQLException e) {
            System.out.println("Error al mirar la tabla");
        }
        finally {
            try {
                cerrarConexiones(rs);
                conn.close();
            }
            catch (SQLException e) {
                System.out.println("Error al cerrar el ResultSet");
            }
        }
    }

    public void escribirFichero(String ruta){
        int index = 0;
        try {
            FileWriter fw = new FileWriter(ruta, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            while (index < listaDatos.size()){
                pw.write(listaDatos.get(index).getCod() + " ");
                pw.write(listaDatos.get(index).getNome() + " ");
                pw.write(listaDatos.get(index).getPuntos() + "\n");
                index++;
            }
            pw.close();
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error --> " + e.getMessage());
        }

    }

    public void leerFicheroTXT(String ruta){
        try {
            FileReader fileReader = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fileReader);
            String lectura =  br.readLine();
            while (lectura != null){
                System.out.println(lectura);
                lectura = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void cerrarConexiones(ResultSet rs) throws SQLException {
        Objects.requireNonNull(rs).close();
        conn.close();
    }

}
