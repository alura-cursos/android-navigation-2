package br.com.alura.aluraesporte.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.viewmodel.EstadoAppViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val controlador by lazy {
        findNavController(R.id.main_activity_nav_host)
    }
    private val viewModel: EstadoAppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        controlador.addOnDestinationChangedListener { controller,
                                                      destination,
                                                      arguments ->
            title = destination.label
            viewModel.appBar.observe(this, Observer {
                it?.let { temAppBar ->
                    if(temAppBar){
                        supportActionBar?.show()
                    } else {
                        supportActionBar?.hide()
                    }
                }
            })
        }
    }

}
