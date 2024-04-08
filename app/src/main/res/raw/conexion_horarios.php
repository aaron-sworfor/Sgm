<?php
// Configuración de la conexión a la base de datos
$url = "mysql://d1mai6uvkb96rb2h:ndfhh698pcltihud@gx97kbnhgjzh3efb.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/ic4qzkc3sdnrv2wj";
$url_parts = parse_url($url);

$host = $url_parts['host'];
$port = $url_parts['port'];
$username = $url_parts['user'];
$password = $url_parts['pass'];
$database = ltrim($url_parts['path'], '/');

// Conexión a la base de datos
$mysqli = new mysqli($host, $username, $password, $database, $port);

// Verificar la conexión
if ($mysqli->connect_error) {
    die("Error de conexión: " . $mysqli->connect_error);
}

echo "Conexión exitosa";
?>
