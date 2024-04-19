import java.sql.*;

public class JDBCExample {
    // Configurações de conexão com o banco de dados
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Banco_Java";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Registrar o driver JDBC
            Class.forName(JDBC_DRIVER);

            // Abrir uma conexão
            System.out.println("Conectando ao banco de dados...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Criar uma declaração
            System.out.println("Criando declaração...");
            stmt = conn.createStatement();

            // Inserir um registro
            System.out.println("Inserindo registro...");
            String sql = "INSERT INTO Tipo_fruta1 (coluna1, coluna2, coluna3, coluna4) VALUES ('Maçã', 'Fruta', '2.50', '2024-04-17')";
            stmt.executeUpdate(sql);

            // Atualizar um registro
            System.out.println("Atualizando registro...");
            sql = "UPDATE Tipo_fruta2 SET coluna1 = 'Banana' WHERE id = 1";
            stmt.executeUpdate(sql);

            // Excluir um registro
            System.out.println("Excluindo registro...");
            sql = "DELETE FROM Tipo_fruta2 WHERE id = 1";
            stmt.executeUpdate(sql);

            // Consultar registros
            System.out.println("Consultando registros...");
            sql = "SELECT * FROM Tipo_fruta1";
            ResultSet rs = stmt.executeQuery(sql);

            // Exibir resultados
            while (rs.next()) {
                // Processar o registro
                int id = rs.getInt("id");
                String coluna1 = rs.getString("coluna1");
                String coluna2 = rs.getString("coluna2");
                String coluna3 = rs.getString("coluna3");
                String coluna4 = rs.getString("coluna4");
                System.out.println("ID: " + id + ", Coluna1: " + coluna1 + ", Coluna2: " + coluna2 + ", Coluna3: " + coluna3 + ", Coluna4: " + coluna4);
            }

            // Fechar recursos
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Lidar com erros para a JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Lidar com erros para Class.forName
            e.printStackTrace();
        } finally {
            // Fechar recursos
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nada a fazer
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Fim.");
    }
}
