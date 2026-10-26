package Hotel

import kotlin.system.exitProcess

fun Abastecimento(){
    println("\n[Abastecimento]")
    val tanque = 42.0

    print("Posto Wayne Oil -> Preço do Álcool: ")
    val wAlcool = readln().toDoubleOrNull() ?: 0.0
    print("Posto Wayne Oil -> Preço da Gasolina: ")
    val wGasolina = readln().toDoubleOrNull() ?: 0.0

    print("Posto Stark Petrol -> Preço do Álcool: ")
    val sAlcool = readln().toDoubleOrNull() ?: 0.0
    print("Posto Stark Petrol -> Preço da Gasolina: ")
    val sGasolina = readln().toDoubleOrNull() ?: 0.0

    val wMelhorComb = if (wAlcool <= 0.70 * wGasolina) "Álcool" else "Gasolina"
    val wPrecoEscolhido = if (wMelhorComb == "Álcool") wAlcool else wGasolina
    val wTotal = tanque * wPrecoEscolhido

    val sMelhorComb = if (sAlcool <= 0.70 * sGasolina) "Álcool" else "Gasolina"
    val sPrecoEscolhido = if (sMelhorComb == "Álcool") sAlcool else sGasolina
    val sTotal = tanque * sPrecoEscolhido

    println("\nWayne Oil -> Álcool: %.2f | Gasolina: %.2f".format(wAlcool, wGasolina))
    println("Stark Petrol -> Álcool: %.2f | Gasolina: %.2f".format(sAlcool, sGasolina))
    println()
    println("Wayne Oil: melhor opção = $wMelhorComb | Total (42L) = R$ " + "%.2f".format(wTotal))
    println("Stark Petrol: melhor opção = $sMelhorComb | Total (42L) = R$ " + "%.2f".format(sTotal))

    val maisBaratoPosto: String
    val combFinal: String

    if (wTotal <= sTotal) {
        maisBaratoPosto = "Wayne Oil"
        combFinal = wMelhorComb.lowercase()
    } else {
        maisBaratoPosto = "Stark Petrol"
        combFinal = sMelhorComb.lowercase()
    }

    val nomeExibicao = if (nomeUsuario.isBlank()) "Funcionário" else nomeUsuario

    // Mensagem final personalizada
    println(" $nomeUsuario é mais barato abastecer com $combFinal no posto $maisBaratoPosto.")
}