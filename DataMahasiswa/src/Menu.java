import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class Menu extends JFrame{
    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(480, 560);
        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);
        // isi window
        window.setContentPane(window.mainPanel);
        // ubah warna background
        window.getContentPane().setBackground(Color.white);
        // tampilkan window
        window.setVisible(true);
        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;
    private Database database;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JComboBox fakultasComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JLabel fakultasLabel;

    public Menu() {
        // inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        // buat objek database
        database = new Database();

        // isi listMahasiswa
        populateList();

        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box jenis kelamin
        String[] jenisKelaminData = {"", "Laki-laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        // atur isi combo box fakultas
        String[] fakultasData = {"", "FPOK", "FIP", "FPTK", "FPBS", "FPEB", "FPSD", "FPMIPA", "FPIPS"};
        fakultasComboBox.setModel(new DefaultComboBoxModel(fakultasData));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    deleteData();
                }
            }
        });

        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saat tombol
                clearForm();
            }
        });

        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // simpan value textfield dan combo box
                String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                String selectedFakultas = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString();

                // ubah isi textfield dan combo box
                nimField.setText(selectedNim);
                namaField.setText(selectedNama);
                jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
                fakultasComboBox.setSelectedItem(selectedFakultas);

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");
                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    // mengatur tabel yang ditampilkan
    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Fakultas"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);

        // isi tabel dengan listMahasiswa
        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");

            int i = 0;
            while (resultSet.next()) {
                Object[] row = new Object[5];

                row[0] = i + 1;
                row[1] = resultSet.getString("nim");
                row[2] = resultSet.getString("nama");
                row[3] = resultSet.getString("jenis_kelamin");
                row[4] = resultSet.getString("fakultas");

                temp.addRow(row);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return temp;
    }

    // menambah data mahasiswa
    public void insertData() {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String fakultas = fakultasComboBox.getSelectedItem().toString();

        // periksa apakah ada input yang kosong
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || fakultas.isEmpty()) {
            // tampilkan dialog/prompt error
            JOptionPane.showMessageDialog(null, "Semua data harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return; // hentikan operasi jika ada input yang kosong
        }

        // periksa apakah NIM sudah ada dalam database
        try {
            ResultSet resultSet = database.selectQuery("SELECT COUNT(*) AS count FROM mahasiswa WHERE nim = '" + nim + "'");
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                if (count > 0) {
                    // jika NIM sudah ada, tampilkan dialog/prompt error
                    JOptionPane.showMessageDialog(null, "NIM sudah terdata!", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // hentikan operasi jika NIM sudah ada
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // tambahkan data ke dalam database
        String sql = "INSERT INTO mahasiswa VALUES (null, '" + nim + "', '" + nama + "', '" + jenisKelamin + "', '" + fakultas + "');";
        // jalankan perintah SQL UPDATE
        database.insertUpdateDeleteQuery(sql);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Insert berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }

    // mengubah data mahasiswa yang dipilih user
    public void updateData() {
        // ambil data dari form
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String fakultas = fakultasComboBox.getSelectedItem().toString();

        // periksa apakah ada input yang kosong
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || fakultas.isEmpty()) {
            // tampilkan dialog/prompt error
            JOptionPane.showMessageDialog(null, "Semua data harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return; // hentikan operasi jika ada input yang kosong
        }

        // update data yang ada di database
        String sql = "UPDATE mahasiswa SET nama = '" + nama + "', jenis_kelamin = '" + jenisKelamin + "', fakultas = '" + fakultas + "' WHERE nim = '" + nim + "'";
        // jalankan perintah SQL UPDATE
        database.insertUpdateDeleteQuery(sql);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Update Berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
    }

    // menghapus data mahasiswa yang dipilih user
    public void deleteData() {
        // tampilkan dialog konfirmasi untuk menghapus data yang dipilih
        int confirmation = JOptionPane.showConfirmDialog(null, "Hapus data ini?", "Message", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            // ambil NIM mahasiswa yang dipilih
            String nim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();

            // jalankan query DELETE untuk menghapus data dari database
            String sql = "DELETE FROM mahasiswa WHERE nim = '" + nim + "'";
            database.insertUpdateDeleteQuery(sql);

            // hapus data mahasiswa dari listMahasiswa berdasarkan NIM
            for (int i = 0; i < listMahasiswa.size(); i++) {
                if (listMahasiswa.get(i).getNim().equals(nim)) {
                    listMahasiswa.remove(i);
                    break; // keluar dari loop setelah menghapus data
                }
            }

            // perbarui selectedIndex
            selectedIndex = -1;

            // update tabel
            mahasiswaTable.setModel(setTable());

            // bersihkan form
            clearForm();

            // feedback
            System.out.println("Delete berhasil!");
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        }
    }

    // mengosongkan input pada form setelah melakukan insert/update/delete
    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        fakultasComboBox.setSelectedItem("");

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }

    // ambil data mahasiswa dari database dan tambahkan ke dalam listMahasiswa
    private void fetchDataFromDatabase() {
        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");
            while (resultSet.next()) {
                String nim = resultSet.getString("nim");
                String nama = resultSet.getString("nama");
                String jenisKelamin = resultSet.getString("jenis_kelamin");
                String fakultas = resultSet.getString("fakultas");

                // tambahkan data mahasiswa ke dalam listMahasiswa
                listMahasiswa.add(new Mahasiswa(nim, nama, jenisKelamin, fakultas));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // handle exception
        }
    }

    // perbarui data dari database
    private void populateList() {
        // kosongkan listMahasiswa sebelum mengambil data dari database
        listMahasiswa.clear();
        // panggil method untuk mengambil data dari database
        fetchDataFromDatabase();
    }
}