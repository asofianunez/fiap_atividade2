package com.grupog.atividadefase2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.grupog.atividadefase2.adapter.ItemAdapter
import com.grupog.atividadefase2.dao.ImovelDAO
import com.grupog.atividadefase2.databinding.ActivityImoveisBinding
import com.grupog.atividadefase2.model.Cidadao
import com.grupog.atividadefase2.model.Imovel

class ImoveisActivity : AppCompatActivity() {
    lateinit var binding: ActivityImoveisBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imoveis)
        supportActionBar!!.hide()


        binding = ActivityImoveisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var cidadao: Cidadao = intent.getSerializableExtra("cidadao") as Cidadao


        var imovelDao: ImovelDAO = ImovelDAO(this)


        binding.textViewUserName.text = "Nome: ${cidadao.nome.toString()}"
        binding.textViewUserCPF.text = "CPF: ${cidadao.cpf.toString()}"

        binding.buttonConsultar.setOnClickListener(
            {
                var listaDeImovel: MutableList<Imovel> =
                    imovelDao.consultarImovel(cidadao.id_cidadao) // Arrumar
                val recyclerView: RecyclerView = binding.recyclerViewImoveis
                recyclerView.adapter = ItemAdapter(this, listaDeImovel)
            }
        )


        //Ações atreladas aos cliques nos botões da tela de Imóveis
        binding.buttonHeaderHome.setOnClickListener({
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        })

        binding.buttonHeaderLogout.setOnClickListener({
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

        //Cadastrar Imovel
        binding.buttonCadastrar.setOnClickListener({
            var intent = Intent(this, CadastrarImovelActivity::class.java)
            intent.putExtra("cidadao", cidadao)
            startActivity(intent)
        })

        binding.buttonApagar.setOnClickListener({
            var intent = Intent(this, DeletarImovelActivity::class.java)

            intent.putExtra("cidadao", cidadao)
            startActivity(intent)
        })


        //Ao clicar em Editar Imóvel, as informações atuais do Imóvel serão levadas para a próxima Activity
        binding.buttonAlterarDados.setOnClickListener({
            var intent = Intent(this, AtualizarImovelActivity::class.java)

            intent.putExtra("cidadao", cidadao)
            startActivity(intent)
        })


    }
}