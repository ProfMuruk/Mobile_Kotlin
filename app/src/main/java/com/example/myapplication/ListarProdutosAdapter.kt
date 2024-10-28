package com.example.myapplication

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.Produto

class ListarProdutosAdapter(private val produtos: List<Produto>) :
    RecyclerView.Adapter<ListarProdutosAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nomeProduto: TextView = itemView.findViewById(R.id.nome)
        val descricaoProduto: TextView = itemView.findViewById(R.id.descricao)
        val valorProduto: TextView = itemView.findViewById(R.id.valor)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listar_produtos, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val formatador = DecimalFormat("#,##0.00")


        val produto = produtos[position]

        val valorFormatado = formatador.format(produto.valor)

        holder.nomeProduto.text = produto.nome
        holder.descricaoProduto.text = produto.descricao
        holder.valorProduto.text = valorFormatado
    }

    override fun getItemCount(): Int = produtos.size

}