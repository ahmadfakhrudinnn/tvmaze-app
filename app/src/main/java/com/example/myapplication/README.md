# TVMaze App

Aplikasi Android native untuk menampilkan daftar TV Show menggunakan data dari TVMaze API.

## Deskripsi

TVMaze App merupakan aplikasi Android yang dibuat menggunakan Kotlin dan Jetpack Compose. Aplikasi mengambil data TV Show dari TVMaze API, kemudian menampilkannya dalam bentuk daftar. Pengguna dapat memilih salah satu TV Show untuk melihat informasi detail dan membagikan informasi tersebut.

## Fitur

* Menampilkan daftar TV Show dari TVMaze API
* Menampilkan poster, nama, rating, dan genre
* Menampilkan halaman detail TV Show
* Menampilkan rating, genre, dan sinopsis
* Fitur share untuk membagikan informasi TV Show
* Loading state saat mengambil data
* Error state ketika terjadi kegagalan pengambilan data
* Tombol retry untuk mencoba mengambil data kembali
* Unit testing untuk kondisi berhasil dan gagal

## Teknologi

* Kotlin
* Jetpack Compose
* MVVM Architecture
* Repository Pattern
* Retrofit
* Gson Converter
* Coil
* StateFlow
* Kotlin Coroutines
* JUnit

## API

Data aplikasi diperoleh dari:

TVMaze API
https://api.tvmaze.com/

Endpoint yang digunakan:

`GET /shows?page=1`

## Arsitektur

Aplikasi menggunakan pendekatan **MVVM (Model-View-ViewModel)** dengan Repository.

### Model

Berisi struktur data TV Show seperti:

* ID
* Nama
* Rating
* Genre
* Summary
* Image

### Repository

Bertugas mengambil data dari TVMaze API melalui Retrofit.

### ViewModel

Mengelola proses pengambilan data dan state aplikasi menggunakan StateFlow.

State yang digunakan:

* Loading
* Success
* Error

### UI

Dibangun menggunakan Jetpack Compose untuk menampilkan daftar dan detail TV Show.

## Testing

Aplikasi memiliki unit test untuk:

1. Memastikan ViewModel menghasilkan state `Success` ketika data berhasil diperoleh.
2. Memastikan ViewModel menghasilkan state `Error` ketika terjadi kegagalan pengambilan data.

## Cara Menjalankan Project

1. Clone repository dari GitHub.
2. Buka project menggunakan Android Studio.
3. Tunggu proses Gradle Sync selesai.
4. Jalankan aplikasi menggunakan emulator atau perangkat Android.
5. Pastikan perangkat memiliki koneksi internet karena data diperoleh dari TVMaze API.

## Struktur Project

```text
com.example.myapplication
├── MainActivity.kt
├── data
│   ├── model
│   │   └── TvShow.kt
│   ├── remote
│   │   ├── RetrofitInstance.kt
│   │   └── TvMazeApi.kt
│   └── repository
│       └── TvShowRepository.kt
├── viewmodel
│   └── TvShowViewModel.kt
└── test
    └── TvShowViewModelTest.kt
```

## Status

Project telah mengimplementasikan fitur utama sesuai kebutuhan aplikasi TV Show, termasuk pengambilan data API, list, detail, share, state loading/error, retry, dan unit testing.