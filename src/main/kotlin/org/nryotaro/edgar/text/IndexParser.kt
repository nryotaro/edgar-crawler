package org.nryotaro.edgar.text

import org.nryotaro.edgar.Edgar
import org.nryotaro.edgar.exception.EdgarException
import org.nryotaro.edgar.plain.index.Index
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cglib.core.Local
import org.springframework.stereotype.Service
import java.net.URL
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class IndexParser {
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    private val pattern = DateTimeFormatter.ofPattern("MMM dd, uuuu", Locale.US)

    private val indexPtn = Regex("^(.+) {2,}(.+) {2,}(\\d+) {2,}(\\d+) {2,}(http\\S+)\\s*$")
    private val paddingPtn =  Regex(" {2,}")

    fun parse(index: String) :List<Index> {
        val lines =  index.split(Regex("\\r?\\n"))
        val date = extractDate(lines)
        val  indices = extractIndexLine(lines)
        return indices.map { extractIndex(it)}
    }

    private fun extractIndex(line: String): Index {
        val found: List<String> = line.split(paddingPtn)

        try {
            return Index(companyName = found[0], formType = found[1], cik=Integer.parseInt(found[2]),
                    dateFiled =  LocalDate.parse(found[3], DateTimeFormatter.BASIC_ISO_DATE), url = found[4])
        } catch(e: Throwable) {
            logger.error("failed to parse index{}", line)
            throw EdgarException(e)
        }

    }

    private fun extractDate(lines: List<String>): LocalDate {
        val dateLine: String = lines.first { it.startsWith("Last Data Received:") }
        val dateStr: String? = Regex("Last Data Received:\\s+(\\w.+)").find(dateLine)?.groupValues?.get(1)
        return if(dateStr != null) LocalDate.parse(dateStr, pattern)
            else throw EdgarException("failed to find date in \"$dateLine\"")
    }

    private fun extractIndexLine(lines: List<String>): List<String> {
        var start: Int = -1
        var found: Boolean = false
        for(line in lines) {
            start++
            if(line.startsWith("-----")) {
                found = true
                break
            }
        }
        return if(found) lines.subList(start+1, lines.size)
            else throw EdgarException("failed to find the first line of indices")
    }
}
