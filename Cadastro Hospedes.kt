package Hotel

import kotlin.system.exitProcess

val listaHospedes = mutableListOf(
    "Carlos Villagran", "Maria Antonieta de las Nieves", "Roberto Gómez Bolaños", "Florinda Meza", "Ramón Valdés", "Rubén Aguirre", "Angelines Fernández", "Edgar Vivar", "Horácio Gómez Bolaños", "Raúl Padilla"
)
fun cadastrarHospedes() {


    while (true) {
        println("""Cadastro de Hóspedes
            Selecione uma opção:
            1. Cadastrar
            2. Pesquisar por nome exato
            3. Pesquisar po prefixo
            4. Listar ordenado (A-Z)
            5. Atualizar cadastro
            6. Remover cadastro
            7. Sair""")

        val escolha = readln().toIntOrNull()

        when (escolha) {
            1 -> cadastrarHospede(listaHospedes)
            2 -> pesquisarHospede(listaHospedes)
            3 -> pesquisarPrefixoHospede(listaHospedes)
            4 -> listarHospedes(listaHospedes)
            5 -> atualizarHospede(listaHospedes)
            6 -> removerHospede(listaHospedes)
            7 -> sairCadastroDeHospedes()
            8 -> {
                return
            }
            else -> erroCadastroDeHospedes()
        }
    }
}

fun cadastrarHospede(listaHospedes: MutableList<String>) {
    if (listaHospedes.size >= 15) {
        println("Máximo de cadastros atingido")
        return
    }
    println("Cadastro de Hóspedes.\nPor favor, informe o nome da Hóspede:")
    val novoHospede = readln()

    if (listaHospedes.any {it.equals(novoHospede, ignoreCase = true)}) {
        println("Hóspede já cadastrado")
        return
    }
    listaHospedes.add(novoHospede)
    println("$novoHospede cadastrado com sucesso!")
    println("Lista de Hóspedes atuais " + listaHospedes)

    // Não é necessário chamar a função cadastrarHospedes(), pois o loop while já está chamando.
}

fun pesquisarHospede(listaHospedes: MutableList<String>) {
    println("Pesquisa de Hóspedes.\nPor favor, informe o nome do Hóspede:")
    val nomeHospede = readln()

    if (listaHospedes.any { it.contains(nomeHospede, ignoreCase = true) }) {
        println("\nEncontramos a(s) hóspede(s):")
        listaHospedes.filter { it.contains(nomeHospede, ignoreCase = true) }
            .forEach { println(it) }
    } else {
        println("Não encontramos nenhuma hóspede com esse nome.")
    }
}

fun pesquisarPrefixoHospede(listaHospedes: MutableList<String>) {
    println("Pesquisa por prefixo. \n Informe o prefixo do nome:")
    val prefixo = readln()
    val resultados = listaHospedes.filter { it.startsWith(prefixo, ignoreCase = true) }

    if (resultados.isEmpty()) {
        println("Hóspede não encontrado")
    } else {
        println("Resultados:")
        resultados.forEach { h ->
            val index = listaHospedes.indexOf(h) + 1
            println("[$index] $h")
        }
    }
}

fun listarHospedes(listaHospedes: MutableList<String>){
    println("\n--- Lista de Hóspedes (A-Z) ---")
    if (listaHospedes.isEmpty()) {
        println("Nenhum hóspede cadastrado.")
        return
    }
    val ordenados = listaHospedes.sorted()
    ordenados.forEachIndexed { index, hospede ->
        println("[${index + 1}] $hospede")
    }
}

fun atualizarHospede(listaHospedes: MutableList<String>) {
    listarHospedes(listaHospedes)
    println("Informe o número do índice do hóspede que deseja atualizar:")
    val index = (readln().toIntOrNull() ?: -1) - 1

    val ordenados = listaHospedes.sorted()
    if (index in ordenados.indices) {
        val antigo = ordenados[index]
        println("Informe o novo nome:")
        val novoNome = readln()

        val realIndex = listaHospedes.indexOf(antigo)
        listaHospedes[realIndex] = novoNome
        println("Operação realizada com sucesso")
    } else {
        println("Hóspede não encontrado")
    }
}

fun removerHospede(listaHospedes: MutableList<String>) {
    listarHospedes(listaHospedes)
    println("Informe o número do índice do hóspede que deseja remover:")
    val index = (readln().toIntOrNull() ?: -1) - 1

    val ordenados = listaHospedes.sorted()
    if (index in ordenados.indices) {
        val alvo = ordenados[index]
        listaHospedes.remove(alvo)
        println("Operação realizada com sucesso")
    } else {
        println("Hóspede não encontrado")
    }
}

fun sairCadastroDeHospedes() {
    println("Você deseja sair? S/N")
    val escolha = readln()

    when (escolha.uppercase()) {
        // uppercase fará o que for digitado ser convertido para maiúsculo por exemplpo x -> X
        "S" -> {
            println("Hasta la vista, Baby.")
            exitProcess(0)
        }
        "N" -> {
            println("Ok, voltando ao início.")
            cadastrarHospedes()
        }
        else -> {
            println("Desculpe, mas não compreendi.")
            sairCadastroDeHospedes()
        }
    }
}

fun erroCadastroDeHospedes() {
    println("Por favor, informe um número entre 1 e 3.")
}