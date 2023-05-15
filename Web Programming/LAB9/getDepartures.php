<?php
    header('Access-Control-Allow-Origin: *');
    $pdo = new PDO('sqlite:laburipw.db');
    $statement = $pdo->query("SELECT * FROM routes GROUP BY departure"); 
    $rows = $statement->fetchAll(PDO::FETCH_ASSOC);
    foreach($rows as $row) {
        echo '<option>'.$row['departure'].'</option>';
    }
?>