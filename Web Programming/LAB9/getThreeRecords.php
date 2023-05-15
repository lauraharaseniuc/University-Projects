<?php
    header('Access-Control-Allow-Origin: *');
    $pdo = new PDO('sqlite:laburipw.db');
    $current_rec_count = $_GET['rec_no'];
    $stop_index = $current_rec_count+3;

    $statement = $pdo->prepare("SELECT * FROM users LIMIT ?,3"); 

    $statement->bindValue(1,$current_rec_count);
    $statement->execute();
    $rows = $statement->fetchAll(PDO::FETCH_ASSOC);
    foreach($rows as $row) {
        echo ' '.$row['first_name'].' '.$row['last_name'].' [email: ] '.$row['email'].'<br>';
    }
?>