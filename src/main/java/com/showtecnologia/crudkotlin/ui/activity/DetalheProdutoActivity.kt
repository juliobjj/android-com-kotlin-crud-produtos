package com.showtecnologia.crudkotlin.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.showtecnologia.crudkotlin.databinding.ActivityDetalheProdutoBinding
import com.showtecnologia.crudkotlin.extensions.formataParaMoedaBrasileira
import com.showtecnologia.crudkotlin.extensions.tentaCarregarImagem
import com.showtecnologia.crudkotlin.model.Produto

class DetalheProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalheProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let {produtoCarregado ->
            preenCheCampos(produtoCarregado)
        }
    }

    private fun preenCheCampos(produtoCarregado: Produto) {
        with(binding) {
            activityDetalheProdutoImagem.tentaCarregarImagem(produtoCarregado.imagem)
            activityDetalheProdutoNome.text = produtoCarregado.nome
            activityDetalheProdutoDescricao.text = produtoCarregado.descricao
            activityDetalheProdutoPreco.text =
                produtoCarregado.valor.formataParaMoedaBrasileira()
        }
    }
}