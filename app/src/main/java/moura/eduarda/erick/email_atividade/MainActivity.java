package moura.eduarda.erick.email_atividade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Define o layout da atividade como o layout 'activity_main'

        // Obtém o botão com id btnEnviar
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);

        // Define a ação do clique do botão
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtém os dados digitados pelo usuário nos campos de email
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();

                // Obtém os dados digitados pelo usuário nos campos de assunto
                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                // Obtém os dados digitados pelo usuário nos campos de texto
                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                // Cria uma nova intenção para enviar um e-mail
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:")); // Define o tipo de ação como envio de email

                // Define os dados do e-mail a serem enviados
                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL, emails); // Define o destinatário do email
                i.putExtra(Intent.EXTRA_SUBJECT, assunto); // Define o assunto do email
                i.putExtra(Intent.EXTRA_TEXT, texto); // Define o corpo do email

                try{
                    // Inicia uma atividade para enviar o email, permitindo ao usuário escolher o aplicativo
                    startActivity(Intent.createChooser(i,"Escolha o App"));
                }
                catch (ActivityNotFoundException e) {
                    // Exibe uma mensagem caso não haja nenhum aplicativo capaz de lidar com a ação de envio de email
                    Toast.makeText(MainActivity.this, "Não há nenhum app que possa realizar essa operação", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
