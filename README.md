# Descubra as pessoas que estão no espaço sideral agora !

# Android Studio 3.6

Este aplicativo consome a API http://open-notify.org/Open-Notify-API/People-In-Space/ para saber a quantidade de pessoas que estão no espaço sideral agora.

O objetivo do projeto é migrar um aplicativo Android que não segue nenhuma arquitetura definida para a arquitetura MVVM (Model View View Model). Podemos notar que o pojeto possui diversos code smells (code smells são trechos de código que podem indicar problemas). A lógica da aplicação está toda definida na View (MainFragment.kt), em um aplicativo pequeno e com poucos recursos isto pode não parecer um grande problema, mas este tipo de abordagem dificulta a evolução do aplicativo, dificulta a atuação de múltiplas equipes no mesmo projeto além de ter uma baixa testabilidade. 

Neste projeto não será necessário escrever a implementação da classe ViewModel, eu já deixei essas classes escritas para vocês ;). A ideia é apenas entender o papel de cada um dos elementos no seu aplicativo.

Eu preparei alguns TODO's que vão guia-los nesta jornada, para acessar os TODO's vocês podem ir em View -> Tool Windows -> TODO.

Antes de iniciar o refactore, rode o aplicativo e verifique como ele funciona, após o refactore execute novamente para garantir que as funcionalidades continuam de acordo com o esperado. 

- Primeiro passo para iniciar o refactore do código, vamos mover as classes da camada view para o seu pacote correspondente. Execute os TODOS 1, 2 e 3. Para realizar estes passos a tecla de atalho F6 pode ajuda-lo. 

- Antes de partir para o TODO número 4, abra o arquivo PeopleViewModel.kt e leia os comentários deixados na viewModel, a viewModel vai se comunicar com o Fragment via LiveData, sempre que ocorrer um evento na ViewModel esse evento sera postado nos objetos de LiveData.

- Vamos para próxima etapa, esta etapa consiste em implementar os observables da ViewModel na camada view, Observe o TODO número 4, e execute o que esta sendo requisitado nos TODO número 5 e número 6.

- Nesta etapa vamos remover os observables e informar para viewModel que a view não precisa mais receber as atualizações. Para remover o observable utilize o metodo ```removeObservers``` do objeto LiveData. 

- Chegamos na etapa final da refatoração, nesta etapa vamos remover os metodos que estão acoplados na nossa View, realize todos os TODO's restantes. 










