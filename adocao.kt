

class Pet(
    val nome: String,
    val especie: String,            
    val raca: String,
    val idade: Int,                 
    val peso: Double,               
    val porte: String,              
    val vacinado: Boolean,
    val remedioContinuo: String? = null   
) {
    // Filtro de Porte (when)
    fun descricaoPorte(): String = when (porte.uppercase()) {
        "P" -> "Pequeno (até 10kg)"
        "M" -> "Médio (até 25kg)"
        "G" -> "Grande (acima de 25kg)"
        else -> "Porte desconhecido"
    }

    // Uso de Remédios (Null Safety)
    fun historicoMedico(): String =
        if (remedioContinuo == null) "Nenhum histórico médico grave"
        else "Medicamento de uso contínuo: $remedioContinuo"

    fun exibir() {
        println("- $nome | $especie | Raça: $raca | ${idade} ano(s) | ${peso}kg | " +
                "Porte: ${descricaoPorte()} | Vacinado: ${if (vacinado) "Sim" else "Não"}")
        println("  Saúde: ${historicoMedico()}")
    }
}

class Adotante(
    val nome: String,
    val possuiApartamento: Boolean   // true = apartamento sem quintal
)

class PedidoAdocao(
    val pet: Pet,
    val adotante: Adotante
) {
    // Restrição de Espaço (if/else)
    fun processar() {
        println("Pedido: ${adotante.nome} quer adotar ${pet.nome} (${pet.descricaoPorte()})")
        if (pet.porte.uppercase() == "G" && adotante.possuiApartamento) {
            println("  -> Requer avaliação de espaço físico prévia")
        } else {
            println("  -> Pedido aprovado para a próxima etapa")
        }
    }
}



fun buscarPorEspecie(mural: List<Pet>, especie: String) {
    println("=== Pets da espécie \"$especie\" no mural ===")
    var encontrados = 0
    for (pet in mural) {
        if (pet.especie.equals(especie, ignoreCase = true)) {
            pet.exibir()
            encontrados++
        }
    }
    if (encontrados == 0) println("Nenhum pet encontrado.")
}



fun main() {
    // Mural com 5 pets disponíveis
    val mural = listOf(
        Pet("Mingau", "Gato", "Siamês", 2, 4.0, "P", true),
        Pet("Rex", "Cachorro", "Pastor Alemão", 4, 32.5, "G", true, "Anti-inflamatório"),
        Pet("Luna", "Gato", "Vira-lata", 1, 3.5, "P", true, "Vitamina B12"),
        Pet("Thor", "Cachorro", "Labrador", 3, 28.0, "G", false),
        Pet("Nina", "Gato", "Persa", 5, 5.0, "P", true)
    )

    // Busca: apenas gatos (cães omitidos)
    buscarPorEspecie(mural, "Gato")

    println()

    // Pedidos de adoção
    val maria = Adotante("Maria", possuiApartamento = true)
    val joao = Adotante("João", possuiApartamento = false)

    val pedidos = listOf(
        PedidoAdocao(mural[1], maria),  // Pet grande + apartamento -> avaliação
        PedidoAdocao(mural[1], joao),   // Pet grande + casa -> ok
        PedidoAdocao(mural[0], maria)   // Pet pequeno + apartamento -> ok
    )

    println("=== Fila de pedidos de adoção ===")
    for (pedido in pedidos) {
        pedido.processar()
    }
}