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

// Consulta SQL para seleccionar todos los registros de la tabla "usuarios"
$sql = "SELECT * FROM usuarios";

// Ejecutar la consulta
$result = $mysqli->query($sql);

// Verificar si la consulta tuvo éxito
if ($result) {
    // Iterar sobre los resultados y mostrarlos
    while ($row = $result->fetch_assoc()) {
        echo "ID: " . $row["id"] . " - Nombre: " . $row["nombre"] . " - Email: " . $row["email"] . "<br>";
    }
} else {
    echo "Error al ejecutar la consulta: " . $mysqli->error;
}

// Cerrar la conexión
$mysqli->close();
?>
