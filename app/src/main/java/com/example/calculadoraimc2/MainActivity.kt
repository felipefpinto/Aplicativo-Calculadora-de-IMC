package com.example.calculadoraimc2

import android.R
//import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc2.ui.theme.CalculadoraIMC2Theme
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraIMC2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculadoraIMC2Screen()
                }
            }
        }
    }
}

@Composable
fun CalculadoraIMC2Screen() {
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2B2D30))
    ){

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Dentro da Column inserir:
        Text(
            text = "Calculadora de IMC",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 50.dp),
            color = Color(0xFFC0C0C0)


        )
        OutlinedTextField(
            value = peso,
            onValueChange = { newValue ->
                // Permite apenas números e um ponto decimal
                if (newValue.matches(Regex("^\\d*\\.?\\d*\$"))) {
                    peso = newValue
                }
            },
            label = { Text("Peso (kg)") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedLabelColor = Color(0xFFC0C0C0),
                focusedLabelColor = Color(0xFFFF9800),
                focusedBorderColor =Color(0xFFFF9800),
                focusedTextColor = Color(0xFFC0C0C0),
                unfocusedTextColor = Color(0xFFC0C0C0),


            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            value = altura,
            onValueChange = { newValue ->
                // Permite apenas números e um ponto decimal
                if (newValue.matches(Regex("^\\d*\\.?\\d*\$"))) {
                    altura = newValue
                }
            },
            label = { Text("Altura (m)") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedLabelColor = Color(0xFFC0C0C0),
                focusedLabelColor = Color(0xFFFF9800),
                focusedBorderColor =Color(0xFFFF9800),
                focusedTextColor = Color(0xFFC0C0C0),
                unfocusedTextColor = Color(0xFFC0C0C0),


            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                if (peso.isNotEmpty() && altura.isNotEmpty()) {
                    val imc = peso.toFloat() / (altura.toFloat() * altura.toFloat())
                    resultado = "Seu IMC: %.2f \n%s".format(imc, classificarIMC(imc))
                } else {
                    resultado = "Preencha todos os campos!"
                }
            },
            modifier = Modifier.fillMaxWidth(),
          colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFFFF9800),

            )
        ) {
            Text("Calcular IMC" )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = resultado,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFFC0C0C0)


        )
    }}
}
// Observação descomentar o “resultado” do slide anterior
private fun classificarIMC(imc: Float): String {
    return when {
        imc < 18.5 -> "Abaixo do peso"
        imc < 24.9 -> "Peso normal"
        imc < 29.9 -> "Sobrepeso"
        imc < 34.9 -> "Obesidade Grau I"
        imc < 39.9 -> "Obesidade Grau II"
        else -> "Obesidade Grau III"
    }
}
@Preview(showBackground = true, showSystemUi = false, device = "id:pixel_8")
@Composable
fun PreviewCalculadoraIMC2() {
    CalculadoraIMC2Theme {
        CalculadoraIMC2Screen()
    }
}
