package br.com.everis.androidmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.everis.androidmvvm.infrastructure.network.Api

/**
 * Utilizamos o ViewModel factory para criar instancias da nossa VewModel
 *
 */
class PeopleViewModelFactory(private val api: Api) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = PeopleViewModel(api) as T
}