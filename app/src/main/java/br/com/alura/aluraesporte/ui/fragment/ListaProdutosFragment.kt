package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout.VERTICAL
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.recyclerview.adapter.ProdutosAdapter
import br.com.alura.aluraesporte.ui.viewmodel.ComponentesVisuais
import br.com.alura.aluraesporte.ui.viewmodel.EstadoAppViewModel
import br.com.alura.aluraesporte.ui.viewmodel.ProdutosViewModel
import kotlinx.android.synthetic.main.lista_produtos.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ListaProdutosFragment : BaseFragment() {

    private val viewModel: ProdutosViewModel by viewModel()
    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()
    private val adapter: ProdutosAdapter by inject()
    private val controlador by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buscaProdutos()
    }

    private fun buscaProdutos() {
        viewModel.buscaTodos().observe(this, Observer { produtosEncontrados ->
            produtosEncontrados?.let {
                adapter.atualiza(it)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.lista_produtos,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoAppViewModel.temComponentes = ComponentesVisuais(
            appBar = true,
            bottomNavigation = true)
        configuraRecyclerView()
    }

    private fun configuraRecyclerView() {
        val divisor = DividerItemDecoration(context, VERTICAL)
        lista_produtos_recyclerview.addItemDecoration(divisor)
        adapter.onItemClickListener = { produtoSelecionado ->
            vaiParaDetalhesDoProduto(produtoSelecionado.id)
        }
        lista_produtos_recyclerview.adapter = adapter
    }

    private fun vaiParaDetalhesDoProduto(produtoId: Long) {
        val direcao = ListaProdutosFragmentDirections
            .acaoListaProdutosParaDetalhesProduto(produtoId)
        controlador.navigate(direcao)
    }

}
