package br.com.alura.aluraesporte.ui.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.viewmodel.EstadoAppViewModel
import kotlinx.android.synthetic.main.main_activity.*
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
            viewModel.componentes.observe(this, Observer {
                it?.let { temComponentes ->
                    if(temComponentes.appBar){
                        supportActionBar?.show()
                    } else {
                        supportActionBar?.hide()
                    }
                    if(temComponentes.bottomNavigation) {
                        main_activity_bottom_navigation.visibility = VISIBLE
                    } else {
                        main_activity_bottom_navigation.visibility = GONE
                    }
                }
            })
        }
        main_activity_bottom_navigation
            .setupWithNavController(controlador)
    }

}
