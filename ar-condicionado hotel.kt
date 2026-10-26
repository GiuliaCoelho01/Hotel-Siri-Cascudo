package Hotel

import kotlin.system.exitProcess

fun arcondicionado(){
    println("\n[Ar-Condicionado]")


    var melhorEmpresa = ""
    var menorValor = Double.MAX_VALUE


    var maiorValor = 0.0

    while (true) {
        print("Nome da empresa: ")
        val empresa = readln()

        print("Valor por aparelho: ")
        val valorAp = readln().toDoubleOrNull() ?: 0.0

        print("Quantidade de aparelhos: ")
        val qtd = readln().toIntOrNull() ?: 0

        print("Desconto (%): ")
        val descontoPct = readln().toDoubleOrNull() ?: 0.0

        print("Quantidade mínima para desconto: ")
        val minDesc = readln().toIntOrNull() ?: 0

        print("Valor fixo de deslocamento: ")
        val deslocamento = readln().toDoubleOrNull() ?: 0.0

        var bruto = valorAp * qtd
        var valorDesconto = 0.0

        if (qtd >= minDesc) {
            valorDesconto = bruto * (descontoPct / 100.0)
        }

        val total = (bruto - valorDesconto) + deslocamento

        println("O serviço de $empresa custará R$ " + "%.2f".format(total))

        if (total < menorValor) {
            menorValor = total
            melhorEmpresa = empresa
        }
        if (total > maiorValor) {
            maiorValor = total
        }

        print("Deseja informar novos dados, $nomeUsuario? (S/N): ")
        val resposta = readln().uppercase()

        if (resposta != "S") {
            break
        }
    }

    println("\nMelhor orçamento: $melhorEmpresa — R$ " + "%.2f".format(menorValor))
}