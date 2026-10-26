package Hotel

import kotlin.system.exitProcess
var quantidadeEventosConfirmados = 0
var receitaEventosTotal = 0.0

fun evento() {
    println("\n--- [Eventos] ---")
    print("Informe o número de convidados do evento: ")
    val entradaConvidados = readln()
    val convidados = entradaConvidados.toIntOrNull()

    // 6.1 Parte A — Capacidade e seleção de auditório
    if (convidados == null || convidados < 0 || convidados > 350) {
        println("Número de convidados inválido")
        return
    }

    var auditorio = ""
    var cadeirasExtras = 0

    if (convidados <= 220) {
        auditorio = "Laranja"
        if (convidados > 150) {
            cadeirasExtras = convidados - 150
        }
    } else {
        auditorio = "Colorado"
    }

    println("Auditório selecionado: $auditorio" + if (cadeirasExtras > 0) " ($cadeirasExtras cadeiras adicionais)" else "")

    // 6.2 Parte B — Agenda e disponibilidade
    print("Dia da semana (ex: segunda, sabado): ")
    val dia = readln().lowercase().trim()

    print("Hora inicial (número inteiro): ")
    val horaInicial = readln().toIntOrNull()

    print("Duração do evento em horas (1 a 12): ")
    val duracao = readln().toIntOrNull()

    if (horaInicial == null || duracao == null || duracao < 1 || duracao > 12) {
        println("Horário ou duração inválidos.")
        return
    }

    val horaFim = horaInicial + duracao
    val disponivel = when (dia) {
        in listOf("segunda", "terça", "quarta", "quinta", "sexta") -> horaInicial >= 7 && horaFim <= 23
        in listOf("sabado","sábado", "domingo") -> horaInicial >= 7 && horaFim <= 15
        else -> false
    }

    if (!disponivel) {
        println("Auditório não está disponível nesse dia/horário.")
        return
    }

    print("Nome da empresa: ")
    val empresa = readln()
    println("Status: Auditório reservado.")

    // 6.3 Parte C — Equipe de garçons
    val baseGarcons = (convidados + 11) / 12
    val reforcoGarcons = duracao / 2
    val totalGarcons = baseGarcons + reforcoGarcons
    val custoGarcons = totalGarcons * duracao * 10.50

    // 6.4 Parte D — Buffet
    val cafeL = convidados * 0.2
    val aguaL = convidados * 0.5
    val salgadosQtd = convidados * 7

    val custoCafe = cafeL * 0.80
    val custoAgua = aguaL * 0.40
    val custoSalgados = (salgadosQtd / 100.0) * 34.00
    val custoBuffet = custoCafe + custoAgua + custoSalgados

    val totalGeral = custoGarcons + custoBuffet

    // 6.5 Parte E — Relatório e decisão
    println("\n[Relatório Técnico do Evento]")
    println("Auditório: $auditorio" + if (cadeirasExtras > 0) " ($cadeirasExtras cadeiras adicionais)" else "")
    println("Empresa: $empresa")
    println("Data: $dia | Início: ${horaInicial}hs | Fim: ${horaFim}hs (Duração: $duracao horas)")
    println("Convidados: $convidados")
    println("Garçons necessários: $totalGarcons")
    println("Custo com garçons: R$ " + "%.2f".format(custoGarcons))
    println("\nBuffet:")
    println("Café: %.1f L".format(cafeL))
    println("Água: %.1f L".format(aguaL))
    println("Salgados: $salgadosQtd un")
    println("Custo buffet: R$ " + "%.2f".format(custoBuffet))
    println("\nTotal do evento: R$ " + "%.2f".format(totalGeral))

    print("\nConfirmar reserva? (S/N): ")
    val confirmacao = readln().uppercase()

    if (confirmacao == "S") {
        println("Reserva efetuada com sucesso.")
    } else {
        println("Reserva não efetuada.")
    }
}
