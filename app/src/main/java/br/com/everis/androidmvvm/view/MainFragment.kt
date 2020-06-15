package br.com.everis.androidmvvm.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.everis.androidmvvm.R
import br.com.everis.androidmvvm.infrastructure.network.Webservice
import br.com.everis.androidmvvm.infrastructure.network.model.People
import br.com.everis.androidmvvm.viewmodel.PeopleViewModel
import br.com.everis.androidmvvm.viewmodel.PeopleViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

//DONE: 2 - Mover esta classe para o package view
class MainFragment : Fragment() {
    private lateinit var rootView : View
    private val adapter = PeopleAdapter()

    private val viewModelFactory = PeopleViewModelFactory(Webservice.getAstrosApi())
    private lateinit var viewModel : PeopleViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_main, container, false)

        setupRecyclerView()

        setupSearchButton()

        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //DONE : 4 - Observe a criação da ViewModel na linha abaixo
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(PeopleViewModel::class.java)

        viewModel.peoples.observe(this, Observer {peoples ->
            //DONE : 5 - Este metodo é acionado quando a operação de requisiÇão de pessoas for completada pela viewmodel atualize a lista com o metodo adapter.setData
            adapter.setData(peoples)
        })

        viewModel.errorMessage.observe(this, Observer { message ->
            //DONE: 6 - Este metodo é acionado quando a operação de requisição falha, exiba um texto com a mensagem para o usuário
            Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        //????: 7 - Remova os observables das variaveis viewModel.peoples e viewModel.errorMessage
        viewModel.peoples.removeObserver{ }
        viewModel.errorMessage.removeObserver{ }

        //DONE: 8 - Remova esta linha, não será mais necessária
    }


    private fun setupSearchButton(){
        rootView.button_list_peoples.setOnClickListener {

            //DONE: 8 - Substitua o metodo listarPessoas no espaço por viewModel.requirePeoples
            viewModel.requirePeoples()
        }
    }

    private fun setupRecyclerView(){
        rootView.recycler_peoples.setHasFixedSize(true)
        rootView.recycler_peoples.addItemDecoration(DividerItemDecoration(context,LinearLayout.VERTICAL))
        rootView.recycler_peoples.adapter = adapter
    }

}
