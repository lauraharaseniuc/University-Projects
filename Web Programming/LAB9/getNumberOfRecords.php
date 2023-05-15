<?php
    header('Access-Control-Allow-Origin: *');
    $pdo = new PDO('sqlite:laburipw.db');

    $statement = $pdo->prepare("SELECT COUNT(*) FROM users"); 
    $statement->execute();

    echo $statement->fetchColumn();
?>