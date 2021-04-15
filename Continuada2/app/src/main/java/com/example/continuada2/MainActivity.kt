package com.example.continuada2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.continuada2.model.Cachorro
import com.example.continuada2.utils.ConexaoApiCachorros
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var etCachorro1: EditText
    lateinit var etCachorro2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCachorro1 = findViewById(R.id.et_cachorro1)
        etCachorro2 = findViewById(R.id.et_cachorro2)
    }

    fun comprar(view: View) {


        val idCachorro1 = etCachorro1.text.toString()
        val idCachorro2 = etCachorro2.text.toString()

        var cachorro1: Cachorro? = encontrarCachorro(idCachorro1)
        var cachorro2: Cachorro? = encontrarCachorro(idCachorro2)


        if(cachorro1 == null && cachorro2 == null) {
            telaCachorroNaoEncontrado(idCachorro1, idCachorro2)

        } else if (cachorro1 != null && cachorro2 == null) {
            telaCachorroEncontrado(cachorro1, cachorro2)

        } else if (cachorro1 == null && cachorro2 != null) {
            telaCachorroEncontrado(cachorro1, cachorro2)

        } else {
            telaCachorroEncontrado(cachorro1, cachorro2)
        }

    }

    fun telaCachorroNaoEncontrado(cachorro1Id: String, cachorro2Id: String) {
        val cachorroNaoEncontrado = Intent(this, CachorroNaoEncontrado::class.java)

        cachorroNaoEncontrado.putExtra("cachorroId1", cachorro1Id)
        cachorroNaoEncontrado.putExtra("cachorroId2", cachorro2Id)

        startActivity(cachorroNaoEncontrado)
    }

    fun telaCachorroEncontrado(cachorro1: Cachorro?, cachorro2: Cachorro?) {
        val cachorroEncontrado = Intent(this, CachorroEncontrado::class.java)

        var valorTotal: Int = 0

        if(cachorro1 == null) {
            cachorroEncontrado.putExtra("racaCachorro1", getString(R.string.nao_encontrado))
        } else {
            cachorroEncontrado.putExtra("racaCachorro1", cachorro1.raca)
            valorTotal += cachorro1.precoMedio
        }

        if(cachorro2 == null) {
            cachorroEncontrado.putExtra("racaCachorro2", getString(R.string.nao_encontrado))
        } else {
            cachorroEncontrado.putExtra("racaCachorro2", cachorro2.raca)
            valorTotal += cachorro2.precoMedio
        }

        cachorroEncontrado.putExtra("valorTotal", valorTotal)

        startActivity(cachorroEncontrado)
    }

    fun encontrarCachorro(idCachorro: String): Cachorro? {
        val apiCachorros = ConexaoApiCachorros.criar()

        var cachorroEncontrado: Cachorro? = null

        apiCachorros.get(idCachorro).enqueue(object : Callback<Cachorro> {
            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                val cachorro = response.body()

                if (cachorro != null) {
                    cachorroEncontrado = cachorro
                    println(cachorro.raca)

                } else {
                    cachorroEncontrado = null
                }
            }

            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
//                cachorroEncontrado = null
            }
        })

        return cachorroEncontrado
    }
}