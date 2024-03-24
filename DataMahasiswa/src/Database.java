import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {
    private Connection connection;
    private Statement statement;

    // inisialisasi koneksi ke database
    public Database() {
        try {
            // buat objek Properties
            Properties props = new Properties();
            props.put("user", "root");  // Menambahkan nama pengguna ke properti
            props.put("password", "");   // Menambahkan kata sandi ke properti

            // buat koneksi dengan menggunakan URL database dan properti
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mahasiswa", props);
            // buat objek Statement untuk menjalankan query SQL
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // menjalankan query SELECT dan mengembalikan ResultSet
    public ResultSet selectQuery(String sql){
        try{
            // jalankan query SELECT
            statement.executeQuery(sql);
            // kembalikan hasil query dalam bentuk ResultSet
            return statement.getResultSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // menjalankan query INSERT, UPDATE, atau DELETE
    public int insertUpdateDeleteQuery(String sql) {
        try {
            // jalankan query INSERT, UPDATE, atau DELETE
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // mendapatkan objek statement
    public Statement getStatement() {
        return statement;
    }
}