<?php

    function find_index($arr, $value) {
        for ($i=0; $i<count($arr); $i++) {
            if ($arr[$i]==$value) {
                return $i;
            }
        }
        return -1;
    }

    header('Access-Control-Allow-Origin: *');
    $human = $_GET['human'];
    $human = explode(",",$human);
    $machine = $_GET['machine'];
    $machine = explode(",",$machine);

    if (find_index($human, 0) != -1) {
        if (find_index($human, 1) != -1 && find_index($human, 2) != -1) {echo 1; return;}
        if (find_index($human, 6) != -1 && find_index($human, 3) != -1) {echo 1; return;}
        if (find_index($human, 4) != -1 && find_index($human, 8) != -1) {echo 1; return;}
    }
    
    echo -1; return;
?>