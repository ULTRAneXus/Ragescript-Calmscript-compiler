class RageToCalm {
    fun rageToCalm(input: List<String>): List<String> {
        val result = mutableListOf<String>()
        for (line in input) {
            var newLine = ""
            for (word in line.trim().split(Regex("[ \t]+"))) {
                if (word.startsWith("E")) {
                    newLine += "var${word.length} "
                } else if (word.startsWith("r")) {
                    newLine += "${parseVal(word)} "
                } else if (word == "AA") {
                    newLine = "//${line.trim().removePrefix("AA ")}"
                    break
                } else {
                    newLine += when (word) {
                        "A" -> "code:"
                        "AAA" -> "printn "
                        "AAAA" -> "printc "
                        "AAAAA" -> "add "
                        "AAAAAA" -> "sub "
                        "AAAAAAA" -> "mul "
                        "AAAAAAAA" -> "div "
                        "AAAAAAAAA" -> "mod "
                        "Y" -> "else"
                        "YY" -> "endif"
                        "YYY" -> "ifeq "
                        "YYYY" -> "ifneq "
                        "YYYYY" -> "ifgeq "
                        "YYYYYY" -> "ifleq "
                        "YYYYYYY" -> "ifeq0 "
                        "YYYYYYYY" -> "ifneq0 "
                        "YYYYYYYYY" -> "ifgeq0 "
                        "YYYYYYYYYY" -> "ifleq0 "
                        "OO" -> "endwhile "
                        "OOO" -> "whileeq "
                        "OOOO" -> "whileneq "
                        "OOOOO" -> "whilegeq "
                        "OOOOOO" -> "whileleq "
                        "OOOOOOO" -> "whileeq0 "
                        "OOOOOOOO" -> "whileneq0 "
                        "OOOOOOOOO" -> "whilegeq0 "
                        "OOOOOOOOOO" -> "whileleq0 "
                        else -> "$word "
                    }
                }

            }
            result += newLine
        }

        return result + ""
    }

    private fun parseVal(input: String): Int {
        var num = input.substring(1).reversed()
        var isNegative = false
        if (num.first() == 'r') {
            isNegative = true
            num = num.substring(1)
        }
        var result = 0
        var currentVal = 1
        for (value in num) {
            if (value == 'e') {
                result += currentVal
            }
            currentVal *= 2
        }
        return if (isNegative) -result else result
    }
}