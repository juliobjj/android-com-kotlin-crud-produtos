package com.showtecnologia.crudkotlin.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.showtecnologia.crudkotlin.dao.ProdutosDao
import com.showtecnologia.crudkotlin.databinding.ActivityFormularioProdutosBinding
import com.showtecnologia.crudkotlin.extensions.tentaCarregarImagem
import com.showtecnologia.crudkotlin.model.Produto
import com.showtecnologia.crudkotlin.ui.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioProdutosActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutosBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar produto"
        configuraBotaoSalvar()
        binding.activityFormularioProdutoImagem.setOnClickListener {
            FormularioImagemDialog(this)
                .mostra(url) { imagem ->
                    url = imagem
                    binding.activityFormularioProdutoImagem.tentaCarregarImagem(url)
                }
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar
        val dao = ProdutosDao()
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            dao.adiciona(produtoNovo)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }

}