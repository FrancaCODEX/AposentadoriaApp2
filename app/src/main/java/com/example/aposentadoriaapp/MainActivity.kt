package com.example.aposentadoriaapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Vinculando os componentes do layout
        val etIdade = findViewById<EditText>(R.id.etIdade)
        val rgSexo = findViewById<RadioGroup>(R.id.rgSexo)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val textViewResultado = findViewById<TextView>(R.id.textViewResultado)

        // Definindo a lógica de cálculo
        btnCalcular.setOnClickListener {
            val idadeStr = etIdade.text.toString()

            if (idadeStr.isEmpty()) {
                Toast.makeText(this, "Preencha a idade", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val idade = idadeStr.toIntOrNull()

            if (idade == null) {
                Toast.makeText(this, "Digite uma idade válida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificando qual sexo foi selecionado
            val sexoSelecionado = when (rgSexo.checkedRadioButtonId) {
                R.id.rbMasculino -> "Masculino"
                R.id.rbFeminino -> "Feminino"
                else -> ""
            }

            if (sexoSelecionado.isEmpty()) {
                Toast.makeText(this, "Selecione o sexo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Definindo a idade de aposentadoria para cada sexo
            val idadeAposentadoria = if (sexoSelecionado == "Masculino") 65 else 62

            // Calculando os anos restantes
            val anosRestantes = if (idade >= idadeAposentadoria) {
                0
            } else {
                idadeAposentadoria - idade
            }

            // Exibindo o resultado
            val resultado = if (anosRestantes == 0) {
                "Você já pode se aposentar!"
            } else {
                "Faltam $anosRestantes anos para você se aposentar."
            }

            textViewResultado.text = resultado
        }
    }
}
