package com.example.sgm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.Manifest;
import android.net.Uri;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;



import android.widget.Toast;


public class MainActivity3 extends AppCompatActivity {
    TextView tvregresar;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String TEXT_KEY_PREFIX = "savedText";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private static final int PERMISSION_REQUEST_CAMERA = 100;
    private static final int PERMISSION_REQUEST_STORAGE = 101;

    ImageView imageView;
    Button btnguardar;
    EditText etnombre,etapellidos,etcorreo,etmatricula,etcontrasena,etgrupo,etdia,etmes,etano;
    RadioButton rd1,rd2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        imageView =(ImageView) findViewById(R.id.imageView);
        etcontrasena =(EditText) findViewById(R.id.etcontrasena);
        etano =(EditText) findViewById(R.id.etano);
        etgrupo =(EditText) findViewById(R.id.etgrupo);
        etdia =(EditText) findViewById(R.id.etdia);
        etmes =(EditText) findViewById(R.id.etmes);
        etnombre =(EditText) findViewById(R.id.etnombre);
        etapellidos =(EditText) findViewById(R.id.etapellidos);
        etcorreo =(EditText) findViewById(R.id.etcorreo);
        etmatricula =(EditText) findViewById(R.id.etmatricula);
        tvregresar =(TextView) findViewById(R.id.tvregresar);
        btnguardar =(Button) findViewById(R.id.btnguardar);
        rd1 =(RadioButton) findViewById(R.id.rd1);
        rd2 =(RadioButton) findViewById(R.id.rd2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions();
            }
        });
        rd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rd2.getText().equals("No aplica")) {
                    rd2.setText("Aplica");
                    rd2.setChecked(true);
                    etgrupo.setEnabled(false);
                    etgrupo.setVisibility(View.INVISIBLE);
                }else if (rd2.getText().equals("Aplica")) {
                    rd2.setText("No aplica");
                    rd2.setChecked(false);
                    etgrupo.setEnabled(true);
                    etgrupo.setVisibility(View.VISIBLE);
                }
            }
        });
        rd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rd1.getText().equals("No aplica")) {
                    rd1.setText("Aplica");
                    rd1.setChecked(true);
                    etmatricula.setEnabled(false);
                    etmatricula.setVisibility(View.INVISIBLE);
                }else if (rd1.getText().equals("Aplica")) {
                    rd1.setText("No aplica");
                    rd1.setChecked(false);
                    etmatricula.setEnabled(true);
                    etmatricula.setVisibility(View.VISIBLE);
                }
            }
        });
        loadSavedText();
        tvregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etnombre.setText("");
                etapellidos.setText("");
                etcorreo.setText("");
                etmatricula.setText("");
                etcontrasena.setText("");
                etgrupo.setText("");
                etdia.setText("");etmes.setText("");
                etano.setText("");
                Intent intent =new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void loadSavedText() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        etnombre.setText(settings.getString(TEXT_KEY_PREFIX + "1", ""));
        etapellidos.setText(settings.getString(TEXT_KEY_PREFIX + "2", ""));
        etcorreo.setText(settings.getString(TEXT_KEY_PREFIX + "3", ""));
        etmatricula.setText(settings.getString(TEXT_KEY_PREFIX + "4", ""));
        etcontrasena.setText(settings.getString(TEXT_KEY_PREFIX + "4", ""));
        etgrupo.setText(settings.getString(TEXT_KEY_PREFIX + "6", ""));
        etdia.setText(settings.getString(TEXT_KEY_PREFIX + "7", ""));
        etmes.setText(settings.getString(TEXT_KEY_PREFIX + "8", ""));
        etano.setText(settings.getString(TEXT_KEY_PREFIX + "9", ""));
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(TEXT_KEY_PREFIX + "1", etnombre.getText().toString());
        editor.putString(TEXT_KEY_PREFIX + "2", etapellidos.getText().toString());
        editor.putString(TEXT_KEY_PREFIX + "3", etcorreo.getText().toString());
        editor.putString(TEXT_KEY_PREFIX + "4", etmatricula.getText().toString());
        editor.putString(TEXT_KEY_PREFIX + "5", etcontrasena.getText().toString());
        editor.putString(TEXT_KEY_PREFIX + "6", etgrupo.getText().toString());
        editor.putString(TEXT_KEY_PREFIX + "7", etdia.getText().toString());
        editor.putString(TEXT_KEY_PREFIX + "8", etmes.getText().toString());
        editor.putString(TEXT_KEY_PREFIX + "9", etano.getText().toString());
        editor.apply();
    }
    private void showOptions() {
        String[] options = {"Tomar Foto", "Seleccionar de la Galería"};

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Elige una opción");
        builder.setItems(options, (dialog, which) -> {
            switch (which) {
                case 0:
                    if (checkCameraPermission()) {
                        dispatchTakePictureIntent();
                    }
                    break;
                case 1:
                    if (checkStoragePermission()) {
                        dispatchPickPictureIntent();
                    }
                    break;
            }
        });
        builder.show();
    }

    private boolean checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            return false;
        }
        return true;
    }

    private boolean checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_STORAGE);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PERMISSION_REQUEST_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchPickPictureIntent();
            } else {
                Toast.makeText(this, "Permiso de almacenamiento denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void dispatchPickPictureIntent() {
        Intent pickPictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (pickPictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(pickPictureIntent, REQUEST_IMAGE_PICK);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                if (imageBitmap != null) {
                    imageView.setBackground(new BitmapDrawable(getResources(), imageBitmap));
                }
            }
        } else if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri selectedImageUri = data.getData();
                imageView.setBackground(new BitmapDrawable(getResources(), BitmapFactory.decodeFile(selectedImageUri.getPath())));
            }
        }

    }
    public void guardarUsuario(View view) {
        String nombre = etnombre.getText().toString();
        String apellidos = etapellidos.getText().toString();
        String correo = etcorreo.getText().toString();
        String matricula = etmatricula.getText().toString();
        String contrasena = etcontrasena.getText().toString();
        String fechaNacimiento = etdia.getText().toString();

        String imagenBase64 = convertirImagenABase64(bitmap);

        new EnviarUsuarioTask().execute(nombre, apellidos, correo, matricula, contrasena, fechaNacimiento, imagenBase64);
    }

    private class EnviarUsuarioTask extends Asyytrrrrrrrrrrrrr4rytry56rncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String nombre = params[0];
            String apellidos = params[1];
            String correo = params[2];
            String matricula = params[3];
            String contrasena = params[4];
            String fechaNacimiento = params[5];
            String imagenBase64 = params[6];

            // Crear una instancia de HttpHandler
            HttpHandler httpHandler = new HttpHandler();

            // Construir los datos del usuario y la imagen como una cadena
            String postData = "nombre=" + nombre + "&apellidos=" + apellidos + "&correo=" + correo +
                    "&matricula=" + matricula + "&contrasena=" + contrasena + "&fechaNacimiento=" + fechaNacimiento +
                    "&imagenBase64=" + imagenBase64;

            // URL de tu API PHP que maneja la inserción del usuario
            String url = "URL_DE_TU_API_PHP";

            // Enviar los datos del usuario y la imagen al servidor
            return httpHandler.sendPostRequest(url, postData);
        }

        @Override
        protected void onPostExecute(String result) {
            // Manejar la respuesta del servidor
            if (result != null) {
                Log.d("Respuesta del servidor", result);
                Toast.makeText(MainActivity.this, "Usuario guardado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Error al guardar el usuario", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String convertirImagenABase64(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
}