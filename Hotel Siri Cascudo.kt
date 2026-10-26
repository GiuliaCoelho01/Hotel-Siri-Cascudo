package Hotel

var nomeUsuario = ""
val statusQuartos = Array(20) { "L" }
val hospedesQuartos = arrayOfNulls<String>(20)

fun main() {
    // Função principal que chama a função inicio().
    nome()

    val acessoPermitido = senha()

    if (!acessoPermitido) {
        return // Se errar 3 vezes, encerra o programa aqui mesmo.
    }
    inicio()


}

//Solicite nome do usuário e senha.
fun nome () {
    println("Bem-vindo ao Siri Cascudo!")
    println("Primeiro nos diga seu nome:")
    val nomeUsuario = readln()
    println("Prazer ter você por aqui. $nomeUsuario")
}
fun senha(): Boolean {

    var tentativas = 3

    while (tentativas > 0) {
        println("Digite sua senha:")
        val senha = readln()

        if (senha == "2678") {
            return true
        } else {
            tentativas--
            if (tentativas > 0) {
                println("Senha incorreta, você tem $tentativas restantes")

            }
        }
    }
    println("Número máximo de tentativas excedido. Sistema bloqueado.")
    return false
}




fun inicio() {

    //Reservas de Quartos
    //Cadastro de Hóspedes
    //Eventos
    //Ar-Condicionado
    //Abastecimento
    //Relatórios Operacionais

    print("Bem vindo ao Hotel Siri Cascudo! $nomeUsuario. É um imenso prazer ter você por aqui!\n")
    println("Escolha uma opção:")

    while (true) {
        println("\n--- Menu Principal ---")
        println("1 - Reservas de Quartos")
        println("2 - Cadastro de Hóspedes")
        println("3 - Eventos")
        println("4 - Ar-Condicionado")
        println("5 - Abastecimento")
        println("6 - Relatórios Operacionais")
        println("7 - Sair")
        print("Escolha uma opção: ")

        val escolha = readln().toIntOrNull()


        // A variavel escolha armazena a opção escolhida pelo usuário.
        // uma variavel local é utilizada apenas dentro da função inicio().

        when (escolha) {
            1 -> reservarQuarto()
            2 -> cadastrarHospedes()
            3 -> evento()
            4 -> arcondicionado()
            5 -> Abastecimento()
            6 -> Relatorios()
            7 -> sairDoHotel()
            else -> erro()
        }
    }
}
//Receba o valor de uma diária no hotel e a quantidade de dias de hospedagem.
// Valide as informações, ou seja, impeça que o usuário informe dados inválidos,
// de maneira que o valor da diária não seja negativo e que a quantidade de dias não seja nem negativa, nem maior que 30.



val listaQuartos = MutableList(20) {"Livre"}
data class ReservaQuarto(val hospede: String, val quarto: Int, val total: Double)
val historicoReservas = mutableListOf<ReservaQuarto>()

fun reservarQuarto() {

        println("Nos informe o valor da diária:")
        val valordiaria = readln().toDoubleOrNull()
        println("Nos informe a quantidade de dias da hospedagem:")
        val dias = readln().toIntOrNull()

        if (valordiaria == null || dias == null || valordiaria <= 0 || dias < 1 || dias > 30) {
            println("Valor Inválido, $nomeUsuario")
            return
        }

        println("Dados válidos! Vamos continuar...")
        println("Por favor, digite seu nome:")
        val nomeQ = readln()

        println("Informe o tipo de quarto (S - Standard, E - Executivo, L - Luxo):")
        val tipoquarto = readln().uppercase()

        val fator = when (tipoquarto) {
            "S" -> 1.00
            "E" -> 1.35
            "L" -> 1.65
            else -> {
                println("Tipo de quarto inválido")
                return
            }
        }
    println("\n--- Mapa de Quartos (4x5) ---")
    for (i in listaQuartos.indices) {
        val status = if (listaQuartos[i] == "Livre") "L" else "O"
        print("[$status] ")

        if ((i + 1) % 5 == 0) {
            println()
        }
    }

    var quartoEscolhido = 0
    while (true) {
        println("Escolha um quarto de 1 a 20:")
        val numeroQuarto = readln().toIntOrNull()

        if (numeroQuarto == null || numeroQuarto < 1 || numeroQuarto > 20) {
            println("Número de quarto inválido! Escolha entre essas opções disponíveis:")
            for (i in listaQuartos.indices) {
                if (listaQuartos[i] == "Livre") {
                    println("${i + 1}")
                }
            }
            continue
        }

        val indice = numeroQuarto - 1


        if (listaQuartos[indice] != "Livre") {
            println("Quarto já está ocupado. Escolha outro.")
        } else {
            quartoEscolhido = numeroQuarto
            break
        }
    }


    val subtotal = valordiaria * dias * fator
    val taxaServico = subtotal * 0.10
    val totalFinal = subtotal + taxaServico


    println("\n--- Resumo da Reserva ---")
    println("Hóspede: $nomeQ")
    println("Quarto: $quartoEscolhido")
    println("Subtotal: R$ $subtotal")
    println("Taxa de serviço (10%): R$ $taxaServico")
    println("Total: R$ $totalFinal")

    println("Confirma a reserva? (S/N):")
    val confirmacao = readln().uppercase()

    if (confirmacao == "S") {

        listaQuartos[quartoEscolhido - 1] = nomeQ
        println("Reserva efetuada com sucesso!")
    } else {
        println("Reserva não efetuada.")
    }


    }






fun erro(){
    println("Por favor, informe um número entre 1 a 7.")
    inicio()
}

fun Relatorios(){
    val totalReservas = historicoReservas.size
    val quartosOcupados = listaQuartos.count { it != "Livre" }
    val taxaOcupacao = (quartosOcupados.toDouble() / 20.0) * 100.0
    val totalHospedes = listaHospedes.size

    val receitaHospedagem = historicoReservas.sumOf { it.total }
    val receitaGeral = receitaHospedagem + receitaEventosTotal

    println(String.format("%-35s | %-15s", "INDICADOR OPERACIONAL", "VALOR"))
    println("---------------------------------------------------------")
    println(String.format("%-35s | %-15d", "Reservas de Quartos Confirmadas", totalReservas))
    println(String.format("%-35s | %-14.1f%%", "Taxa de Ocupação Atual", taxaOcupacao))
    println(String.format("%-35s | %-15d", "Hóspedes Cadastrados", totalHospedes))
    println(String.format("%-35s | %-15d", "Eventos Confirmados", quantidadeEventosConfirmados))
    println(String.format("%-35s | R$ %-12.2f", "Receita de Hospedagem", receitaHospedagem))
    println(String.format("%-35s | R$ %-12.2f", "Receita de Eventos", receitaEventosTotal))
    println("---------------------------------------------------------")
    println(String.format("%-35s | R$ %-12.2f", "RECEITA ACUMULADA TOTAL", receitaGeral))
    println("=========================================================")
}
fun sairDoHotel() {
    println("Você deseja sair?")
    val confirma = readln().toBoolean()
    if (confirma) {
        println("Muito Obrigado e até logo, $nomeUsuario")
    } else {
        inicio()
    }
    fun formatarMoeda(valor: Double): String {
        return "R$ " + "%.2f".format(valor).replace('.', ',')
    }
}