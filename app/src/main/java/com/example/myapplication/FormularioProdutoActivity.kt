package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.dao.ProdutoDAO
import com.example.myapplication.models.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.formulario_produto_activity)

        /*adicionado*/ val produtoDAO = ProdutoDAO(this)

        val salvarProduto = findViewById<Button>(R.id.salvar_produto)

        salvarProduto.setOnClickListener {
            val inputNome = findViewById<EditText>(R.id.nome)
            val nome = inputNome.text.toString()

            val inputDescricao = findViewById<EditText>(R.id.descricao)
            val descricao = inputDescricao.text.toString()

            val inputValor = findViewById<EditText>(R.id.valor)
            val valorTexto = inputValor.text.toString()

            val valor = if (valorTexto.isBlank()) {
                BigDecimal.ZERO
            } else {
                BigDecimal(valorTexto)
            }

            val novoProduto = Produto(
                nome = nome,
                descricao = descricao,
                valor = valor
            )

            produtoDAO.adicionar(novoProduto)
            Log.i("Formulario", "produto adicionado: $novoProduto")

            /*retirar esses 2*/
            /*
            val dao = ProdutoDAO()
            dao.adicionar(novoProduto)
             */



            Log.i("Formulario", "O que veio do dao: ${produtoDAO.buscarTudo()}")
        }
    }
}
