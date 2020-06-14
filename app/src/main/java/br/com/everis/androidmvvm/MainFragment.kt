package br.com.everis.androidmvvm


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
import br.com.everis.androidmvvm.infrastructure.network.Webservice
import br.com.everis.androidmvvm.infrastructure.network.model.People
import br.com.everis.androidmvvm.viewmodel.PeopleViewModel
import br.com.everis.androidmvvm.viewmodel.PeopleViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

//TODO: 2 - Mover esta classe para o package view
class MainFragment : Fragment() {
    private lateinit var rootView : View
    private val adapter = PeopleAdapter()

    private val job = Job() //TODO: 13 - Remova esta propriedade
    private val scopeIO = CoroutineScope(job + Dispatchers.IO) //TODO: 14 - Remova esta propriedade
    private val scopeMainThread = CoroutineScope(job + Dispatchers.Main) //TODO: 15 - Remova esta propriedade

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

        //TODO : 4 - Observe a criação da ViewModel na linha abaixo
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(PeopleViewModel::class.java)


        viewModel.peoples.observe(this, Observer {peoples ->
            //TODO : 5 - Este metodo é acionado quando a operação de requisiÇão de pessoas for completada pela viewmodel atualize a lista com o metodo adapter.setData
        })

        viewModel.errorMessage.observe(this, Observer { message ->
            //TODO: 6 - Este metodo é acionado quando a operação de requisição falha, exiba um texto com a mensagem para o usuário
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        //TODO: 7 - Remova os observables das variaveis viewModel.peoples e viewModel.errorMessage

        job.cancel() //TODO: 8 - Remova esta linha, não será mais necessária
    }


    private fun setupSearchButton(){
        rootView.button_list_peoples.setOnClickListener {

            //TODO: 8 - Substitua o metodo listarPessoas no espaço por viewModel.requirePeoples
            listarPessoasNoEspaco()
        }
    }

    //TODO: 10 - Remover o metodo listar pessoas no espaço
    private fun listarPessoasNoEspaco() {
        scopeIO.launch {
            try {
                val response = Webservice.getAstrosApi().fetchPeopleInSpace()

                scopeMainThread.launch {
                    onSuccess(response.people)
                }
            } catch (e: Exception) {
                scopeMainThread.launch {
                    onFailure(e)
                }
            }
        }
    }

    //TODO: 11 - Remover o metodo on failure
    private fun onFailure(e: Exception){
        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
    }


    //TODO: 12 - Remover o metodo on sucess
    private fun onSuccess(peoples: List<People>){
        adapter.setData(peoples)
    }

    private fun setupRecyclerView(){
        rootView.recycler_peoples.setHasFixedSize(true)
        rootView.recycler_peoples.addItemDecoration(DividerItemDecoration(context,LinearLayout.VERTICAL))
        rootView.recycler_peoples.adapter = adapter
    }

}
