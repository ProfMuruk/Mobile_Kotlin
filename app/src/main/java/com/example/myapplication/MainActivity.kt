package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.dao.ProdutoDAO
import com.example.myapplication.models.Produto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        setContentView(R.layout.activity_main)

        val dao = ProdutoDAO(this);

        /*val nome = findViewById<TextView>(R.id.nome)
        nome.setText("Ração para cachorro")

        val descricao = findViewById<TextView>(R.id.descricao)
        descricao.setText("raças pequenas")

        val valor = findViewById<TextView>(R.id.valor)
        valor.setText("45.99")*/


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ListarProdutosAdapter(
            produtos = dao.buscarTudo()
            /*produtos = listOf(
            Produto(nome = "Teste1", descricao = "desc1", valor = BigDecimal(19.99)),
            Produto(nome = "Teste2", descricao = "desc2", valor = BigDecimal(29.99)),
            Produto(nome = "Teste3", descricao = "desc3", valor = BigDecimal(39.99)),
            Produto(nome = "Teste4", descricao = "desc4", valor = BigDecimal(49.99))
        )*/)

        recyclerView.layoutManager = (LinearLayoutManager(this))

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener{
            val intent = Intent(this, FormularioProdutoActivity::class.java)

            startActivity(intent)}
    }
}