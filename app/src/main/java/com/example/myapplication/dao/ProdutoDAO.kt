/*package com.example.myapplication.dao

import com.example.myapplication.models.Produto

class ProdutoDAO {

    /*
    private val produtos = mutableListOf<Produto>()

    Antes de mudar para companionObject antes de mudar para onResume()

    fun adicionar(produto : Produto){
        produtos.add(produto)
    }

    fun buscarTudo() : List<Produto>{
        return produtos.toList()
    }

     */
    fun adicionar(produto : Produto){
        produtos.add(produto)
    }

    fun buscarTudo() : List<Produto>{
        return produtos.toList()
    }

    companion object{
        private val produtos = mutableListOf<Produto>()
    }

}*/


package com.example.myapplication.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.models.Produto
import java.math.BigDecimal

class ProdutoDAO(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "produtos.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_PRODUTOS = "Produtos"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOME = "nome"
        private const val COLUMN_DESCRICAO = "descricao"
        private const val COLUMN_VALOR = "valor"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE $TABLE_PRODUTOS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOME TEXT NOT NULL,
                $COLUMN_DESCRICAO TEXT NOT NULL,
                $COLUMN_VALOR REAL NOT NULL
            )
        """.trimIndent()

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUTOS")
        onCreate(db)
    }

    fun adicionar(produto: Produto) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME, produto.nome)
            put(COLUMN_DESCRICAO, produto.descricao)
            put(COLUMN_VALOR, produto.valor.toDouble())
        }

        db.insert(TABLE_PRODUTOS, null, values)
        db.close()
    }

    fun buscarTudo(): List<Produto> {
        val produtos = mutableListOf<Produto>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_PRODUTOS", null)

        if (cursor.moveToFirst()) {
            do {
                val nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME))
                val descricao = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRICAO))
                val valor = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_VALOR))

                produtos.add(Produto(nome, descricao, BigDecimal(valor)))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return produtos
    }
}
