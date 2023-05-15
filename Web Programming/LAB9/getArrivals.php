<?php
    header('Access-Control-Allow-Origin: *');
    $pdo = new PDO('sqlite:laburipw.db');
    $statement = $pdo->prepare("SELECT * FROM routes WHERE departure=?"); 
    $statement->bindValue(1,$_GET['depcity']);
    $statement->execute();
    $rows = $statement->fetchAll(PDO::FETCH_ASSOC);
    foreach($rows as $row) {
        echo '<option>'.$row['arrival'].'</option>';
    }
?>