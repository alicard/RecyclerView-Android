<?php

require_once 'connect.php';

$type = $_GET['item_type']; // memeriksa data yang dikirim melalui GET

if ($type == 'bengkel') { // jika $_GET['item_type'] yaitu users, maka akan menjalankan kode dibawahnya

    $query = "SELECT * FROM bengkel";
    $result = mysqli_query($conn, $query);

    $response = array();

    while( $row = mysqli_fetch_assoc($result) ){ // perulangan dari fetching asosiativ

        array_push($response,
        array(
            'id'=>$row['id_bengkel'], 
            'name'=>$row['nama_bengkel'], 
            'desc'=>$row['diskripsi_bengkel'],
            'latlong'=>$row['latlong'],
            'lat'=>$row['lat'],
            'lng'=>$row['lng'])
        );
    }

    echo json_encode($response);  // enchoding kedalam JSON dari array
}

mysqli_close($conn); // menutup koneksi mysql

?>
