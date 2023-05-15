<?php
    header('Access-Control-Allow-Origin: *');
    $positions = $_GET['positions'];
    $positions = explode(",",$positions);
    $lg = $_GET['length'];
    $lg = $lg - 1;
    $poz = random_int(0, $lg);
    echo $positions[$poz];
?>