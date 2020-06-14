package br.com.everis.androidmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.everis.androidmvvm.infrastructure.network.Api
import br.com.everis.androidmvvm.infrastructure.network.model.People
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PeopleViewModel(private val api: Api) : ViewModel() {

    private val scopeIO = CoroutineScope(Dispatchers.IO)


    /**
     * Utilizamos esta váriavel mutavel em modo privado para que ela não possa ser
     * alterada fora do escopo da viewmodel
     */
    private val _peoples = MutableLiveData<List<People>>()

    private val _errorMessage = MutableLiveData<String>()


    /**
     * Estas váriaveis serão utilizada para receber os valores processados pela ViewModel
     * elas são do tipo LiveData, seu conteúdo pode ser observado mas não pode ser alterado.
     */
    val peoples : LiveData<List<People>> = _peoples

    val errorMessage : LiveData<String> = _errorMessage


    fun requirePeoples() {
        scopeIO.launch {
            try {
                val response = api.fetchPeopleInSpace()

                _peoples.postValue(response.people)
            } catch (e: Exception) {
                _errorMessage.postValue(e.message ?: "")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scopeIO.cancel()
    }
}