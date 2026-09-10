# Code Review

## Overview

Aplikasi TVMaze App telah menggunakan struktur sederhana berbasis MVVM dan Repository Pattern. Pemisahan antara data, ViewModel, dan UI digunakan untuk menjaga struktur kode agar lebih mudah dipahami dan dikembangkan.

## Struktur Kode

### Data Model

File `TvShow.kt` digunakan untuk merepresentasikan data TV Show yang diperoleh dari API, seperti nama, rating, genre, summary, dan image.

### Remote API

File `TvMazeApi.kt` digunakan untuk mendefinisikan endpoint TVMaze API menggunakan Retrofit.

File `RetrofitInstance.kt` digunakan untuk membuat instance Retrofit dan menghubungkannya dengan TVMaze API.

### Repository

`TvShowRepository.kt` bertugas sebagai perantara antara sumber data API dan ViewModel.

### ViewModel

`TvShowViewModel.kt` bertugas mengelola proses pengambilan data dan state UI menggunakan `StateFlow`.

State yang digunakan terdiri dari:

* `Loading`
* `Success`
* `Error`

### UI

`MainActivity.kt` digunakan untuk menampilkan antarmuka aplikasi menggunakan Jetpack Compose.

UI terdiri dari:

* Halaman daftar TV Show
* TV Show card
* Halaman detail
* Loading screen
* Error screen
* Fitur retry
* Fitur share

## Testing

Unit test telah dibuat untuk menguji dua kondisi utama pada ViewModel:

1. Kondisi ketika data berhasil diperoleh.
2. Kondisi ketika terjadi error saat mengambil data.

Kedua test berhasil dijalankan.

## Kelebihan

* Struktur kode sederhana dan mudah dipahami.
* Menggunakan MVVM dan Repository Pattern.
* Pengambilan data menggunakan Retrofit.
* State UI ditangani menggunakan StateFlow.
* UI menggunakan Jetpack Compose.
* Terdapat unit testing untuk kondisi success dan error.
* Terdapat error handling dan retry.

## Kekurangan

* Aplikasi saat ini mengambil data dari satu halaman endpoint TVMaze.
* Belum terdapat pagination.
* Belum terdapat fitur pencarian.
* Belum terdapat fitur tambahan seperti season, episode, dan cast.
* Belum terdapat UI testing.

## Saran Pengembangan

Pengembangan selanjutnya dapat menambahkan:

* Pagination untuk mengambil lebih banyak data.
* Search TV Show.
* Informasi season dan episode.
* Informasi cast.
* UI testing.
* Peningkatan desain dan navigasi aplikasi.

## Kesimpulan

Secara keseluruhan, aplikasi telah memenuhi fitur utama yang dibutuhkan, yaitu pengambilan data dari API, menampilkan daftar TV Show, menampilkan detail, fitur share, loading dan error handling, serta unit testing.