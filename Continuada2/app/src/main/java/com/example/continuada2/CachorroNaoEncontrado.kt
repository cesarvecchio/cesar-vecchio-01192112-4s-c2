package com.example.continuada2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CachorroNaoEncontrado : AppCompatActivity() {
    lateinit var tvMensagemNaoEncontrado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cachorro_nao_encontrado)

        var cachorro1 = intent.getStringExtra("cachorroId1")
        var cachorro2 = intent.getStringExtra("cachorroId2")

        tvMensagemNaoEncontrado = findViewById(R.id.tv_mensagem_nao_encontrado)

        tvMensagemNaoEncontrado.text = getString(R.string.tv_nao_encontrado, cachorro1, cachorro2)
    }
}