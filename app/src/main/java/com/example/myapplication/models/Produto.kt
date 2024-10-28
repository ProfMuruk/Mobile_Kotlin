package com.example.myapplication.models

import java.math.BigDecimal

data class Produto(

    val nome : String,
    val descricao : String,
    val valor : BigDecimal
)/*{
    override fun toString(): String {
        return "Produto (nome = $nome, descrição: $descricao, valor: $valor"
    }
}*/