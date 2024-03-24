# JANJI

Saya Syifa Azzahra NIM 2207308 mengerjakan soal Tugas Praktikum 2 dalam mata kuliah
Desain Pemrograman Berorientasi Objek untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah
dispesifikasikan. Aamiin.

# DESKRIPSI TUGAS

Lanjutkan program LP5 yang sudah kamu buat, lalu tambahkan koneksi dengan database MySQL. Ubah program dengan spesifikasi sebagai berikut:

1. Hubungkan semua proses CRUD dengan database.
2. Tampilkan dialog/prompt error jika masih ada input yang kosong saat insert/update.
3. Tampilkan dialog/prompt error jika sudah ada NIM yang sama saat insert.

# DESAIN KODE PROGRAM

Program ini menggunakan tiga kelas yaitu kelas Mahasiswa, kelas Menu dan kelas Database.

1. Kelas Mahasiswa
    
    Kelas ini memiliki empat atribut, yaitu:
    
    - nim: menyimpan nim dalam bentuk string
    - nama: menyimpan nama dalam bentuk string
    - jenisKelamin: menyimpan jenis kelamin dalam bentuk string
    - fakultas: menyimpan fakultas dalam bentuk string
    
    Masing-masing atribut memiliki setter dan getter.
    
2. Kelas Menu
    
    Kelas ini memiliki tujuh method, yaitu:
    
    - setTable(): mengatur tabel yang ditampilkan (public final DefaultTableModel)
    - insertData(): menambah data mahasiswa (public void)
    - updateData(): mengubah data mahasiswa yang dipilih user (public void)
    - deleteData(): menghapus data mahasiswa yang dipilih user (public void)
    - clearForm(): mengosongkan input pada form (public void)
    - fetchDataFromDatabase(): mengambil data mahasiswa dari database dan menambahkannya ke dalam listMahasiswa (private void)
    - populateList(): memperbarui data dari database (private void)
3. Kelas Database
    
    Kelas ini memiliki empat method, yaitu:
    
    - Database(): membuat objek ‘Database’ dan menginisialisasi koneksi ke database (public)
    - selectQuery(String sql): menjalankan query SELECT (public ReslutSet)
    - insertUpdateDeleteQuery(Stringsql): menjalankan query INSERT, UPDATE atau DELETE (public int)
    - getStatement(): mendapatkan objek ‘Statement’ yang digunakan untuk mengeksekusi SQL (public statement)

# ALUR PROGRAM

Program ini memiliki component NIM dan Nama berupa JTextField serta component Jenis Kelamin dan Fakultas berupa JComboBox. Selain itu ada empat button yaitu Add, Update, Delete dan Cancel. Alur programnya adalah sebagai berikut:

- User dapat menambahkan data dengan mengisi NIM dan nama serta memilih jenis kelamin dan fakultas, lalu klik Add. Data otomatis masuk ke dalam tabel. Data harus diisi semua, tidak boleh ada yang kosong (jika ada yang kosong maka akan ada error). Jika data yang akan ditambahkan memiliki NIM yang sama dengan NIM yang sudah terdata di database, maka data tidak bisa ditambahkan (ada error).
- User dapat mengubah data dengan memilih data yang ingin diubah. Setelah ubah data, klik Update. Data yang dipilih tadi otomatis berubah. Data harus diisi semua, tidak boleh ada yang kosong (jika ada yang kosong maka akan ada error).
- User dapat menghapus data dengan memilih data yang ingin dihapus, lalu klik Delete. Akan ada tampilan berupa konfirmasi apakah user benar-benar ingin menghapus data tersebut, jika iya maka klik ‘Yes’. Data yang dipilih tadi otomatis terhapus.

# DOKUMENTASI
## ALUR PROGRAM
https://github.com/archieffa/TP2DPBO2024C1/assets/121290445/ef057d15-acb8-4b94-83d2-4b5cf0c3632c

## ADD
![addForm](https://github.com/archieffa/TP2DPBO2024C1/assets/121290445/5d59cdc9-b24d-4bb2-8729-e75acb4a5fc3)
![addDatabase](https://github.com/archieffa/TP2DPBO2024C1/assets/121290445/dd47fe6c-d4b2-4d67-8103-b3e1a1da25f0)

## UPDATE
![updateForm](https://github.com/archieffa/TP2DPBO2024C1/assets/121290445/2783acbd-6cfc-402f-a123-c24501a5a3f1)
![updateDatabase](https://github.com/archieffa/TP2DPBO2024C1/assets/121290445/56406c81-e84f-4f78-bf7a-fb11571dfb82)

## DELETE
![deleteForm1](https://github.com/archieffa/TP2DPBO2024C1/assets/121290445/f846ca5a-3bdd-4129-9c3a-46c2e98ce45f)
![deleteForm2](https://github.com/archieffa/TP2DPBO2024C1/assets/121290445/d585365d-c64e-48e6-8539-cf98fc417cf7)
![deleteDatabase](https://github.com/archieffa/TP2DPBO2024C1/assets/121290445/d1ed772b-6a3a-4a35-a283-f13116582edb)

## SPESIFIKASI TAMBAHAN
### Ada input yang kosong saat insert/update
![dataKosongInsert](https://github.com/archieffa/TP2DPBO2024C1/assets/121290445/802a2ce2-5162-4cbc-a090-ced1524b90d4)
![dataKosongUpdate](https://github.com/archieffa/TP2DPBO2024C1/assets/121290445/e2691728-4f6d-4dfa-a22c-9b399f0f29da)
### Sudah ada NIM yang sama saat insert
![NIMterdata](https://github.com/archieffa/TP2DPBO2024C1/assets/121290445/9bc83346-1c12-4418-8b06-ac5dfc5c20e1)
