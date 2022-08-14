import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import java.lang.RuntimeException

fun main(args: Array<String>) {
    val fileName = args.singleOrNull() ?: throw RuntimeException("Give me a path idiot")
    csvReader().open(fileName) {
        val entries = readAllWithHeaderAsSequence()
            .map(::toEntry)

        csvWriter().open("out.csv") {
            val rows = entries.map { entry ->
                listOf(entry.count, entry.name, entry.set, entry.number)
            }
            writeRow(listOf("Count", "Name", "Edition", "Card Number"))
            writeRows(rows)
        }
    }

}

fun toEntry(row: Map<String, String>): CardEntry = CardEntry(
    name = row.getValue("Card Name"),
    count = row.getValue("Count").toInt(),
    set = setCode(row.getValue("Set Name")),
    number = row.getValue("Collector Number"),
    foil = row.getValue("Foil").toBoolean()
)

fun setCode(set: String): String = when (set) {
    "Kamigawa: Neon Dynasty", "Kamigawa: Neon Dynasty Promos" -> "neo"
    "Strixhaven: School of Mages" -> "stx"
    "Strixhaven Mystical Archive" -> "sta"
    "The List" -> "plist"
    "Neon Dynasty Commander" -> "nec"
    else -> set
}

data class CardEntry(val name: String, val count: Int, val set: String, val number: String, val foil: Boolean)
