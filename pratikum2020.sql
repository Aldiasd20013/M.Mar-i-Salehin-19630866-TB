-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 08 Jan 2022 pada 07.09
-- Versi server: 10.4.22-MariaDB
-- Versi PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pratikum2020`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `mstr_customer`
--

CREATE TABLE `mstr_customer` (
  `cust_id` varchar(32) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `telp` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mstr_customer`
--

INSERT INTO `mstr_customer` (`cust_id`, `nama`, `alamat`, `telp`) VALUES
('C1', 'Rinso', 'Jl. Veteran', '081264870489');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mstr_produk`
--

CREATE TABLE `mstr_produk` (
  `prd_id` varchar(32) NOT NULL,
  `nama` varchar(32) NOT NULL,
  `alamat` varchar(32) NOT NULL,
  `umur` double NOT NULL,
  `No_HP` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mstr_produk`
--

INSERT INTO `mstr_produk` (`prd_id`, `nama`, `alamat`, `umur`, `No_HP`) VALUES
('001', 'anisa', 'jln.merong', 15, 9121314),
('003', 'aldiasd', 'jln.diaksa', 23, 8123123),
('p006', 'jamrah', 'jln.raya angkasa', 23, 821312312),
('p007', 'aldi', 'jln merdeka', 17, 91233424),
('p012', 'rudi', 'jln.sumur buntu', 45, 812123123),
('p06', 'aldi', 'pramuka', 19, 814134134);

-- --------------------------------------------------------

--
-- Struktur dari tabel `mstr_user`
--

CREATE TABLE `mstr_user` (
  `user_id` varchar(32) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `tempat_lahir` varchar(50) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mstr_user`
--

INSERT INTO `mstr_user` (`user_id`, `nama`, `jenis_kelamin`, `tempat_lahir`, `tanggal_lahir`, `alamat`, `password`) VALUES
('001', 'Zulki', 'Laki-laki', 'Banjarmansin', '2001-11-15', 'Jl. HKSN', '12345'),
('002', 'aldi', 'Laki-laki', 'banjarmasin', '0025-05-23', 'jln.prmauka', 'mikael20013');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penjualan`
--

CREATE TABLE `penjualan` (
  `nofaktur` varchar(20) NOT NULL,
  `tanggal` varchar(20) NOT NULL,
  `id_cust` varchar(20) NOT NULL,
  `totalbeli` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `penjualanrinci`
--

CREATE TABLE `penjualanrinci` (
  `nofaktur` varchar(20) NOT NULL,
  `ID_barang` varchar(20) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `harga` decimal(10,0) NOT NULL,
  `total` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `mstr_customer`
--
ALTER TABLE `mstr_customer`
  ADD PRIMARY KEY (`cust_id`);

--
-- Indeks untuk tabel `mstr_produk`
--
ALTER TABLE `mstr_produk`
  ADD PRIMARY KEY (`prd_id`);

--
-- Indeks untuk tabel `mstr_user`
--
ALTER TABLE `mstr_user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indeks untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`nofaktur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
