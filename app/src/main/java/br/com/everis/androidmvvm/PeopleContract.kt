package br.com.everis.androidmvvm

import br.com.everis.androidmvvm.infrastructure.network.model.People



interface PeopleContract {
    /**
     * A Model deve ser o portão para o dominio da sua aplicação ou sua camada de negócio
     * sua responsabilidade inclui usar API's, fazer cache de dados, acessar banco de dados entre outros.
     */
    interface Model{
        suspend fun getPeoples() : List<People>
    }

    /**
     * A presenter atua entre a View e a Model ela deve recuperar os dados da Model e repassar esses
     * dados para a View, a presenter também é responsável por orquestrar o que acontece com a View
     */
    interface Presenter{
        fun start(view: View, model: Model)
        fun destroy()
        fun retrievePeoples()
    }


    /**
     * View é a camada de apresentação para o usuáro, a view contém instancia da presenter e deve
     * acionar a presenter sempre que uma ação for necessária
     */
    interface View{
        fun showMessage(message : String)
        fun showPeoples(peoples: List<People>)
    }
}