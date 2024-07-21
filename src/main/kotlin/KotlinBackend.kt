class KotlinBackend {
    fun generate(ast: RootComponent): List<String> {
        val res = mutableListOf<String>()
        for (variable in ast.vars) {
            res += "var ${variable.first} = ${variable.second}"
        }
        res += "fun main() {"
        for (component in ast.tree) {
            eval(component, 1).forEach { res += it }
        }
        res += "}"
        return res
    }

    private fun eval(comp: ASTComponent, indentationLevel: Int): List<String> {
        return when (comp) {
            is CommentComponent -> evalComment(comp, indentationLevel)
            is PrintComponent -> evalPrint(comp, indentationLevel)
            is ArithmeticComponent -> evalArithmetic(comp, indentationLevel)
            is BranchComponent -> evalBranch(comp, indentationLevel)
            is LoopComponent -> evalLoop(comp, indentationLevel)
            else -> mutableListOf()
        }
    }

    private fun evalComment(comp: CommentComponent, indentationLevel: Int): List<String> {
        var indent = ""
        (1..indentationLevel).forEach { indent += "\t" }
        return listOf("$indent//${comp.content}")
    }

    private fun evalPrint(comp: PrintComponent, indentationLevel: Int): List<String> {
        var indent = ""
        (1..indentationLevel).forEach { indent += "\t" }
        if (comp.asChar) {
            var res = "${indent}print(\""
            comp.content.forEach { res += "\${Char($it)}" }
            return listOf("$res\")")
        } else {
            var res = "${indent}print(\""
            comp.content.forEach { res += "\${$it}" }
            return listOf("$res\")")
        }
    }

    private fun evalArithmetic(comp: ArithmeticComponent, indentationLevel: Int): List<String> {
        var indent = ""
        (1..indentationLevel).forEach { indent += "\t" }
        return when (comp.type) {
            ArithmeticOperation.ADD -> listOf("$indent${comp.result} = ${comp.operands.reduce { acc, s -> "$acc + ($s)" }}")
            ArithmeticOperation.SUB -> listOf("$indent${comp.result} = ${comp.operands.reduce { acc, s -> "$acc - ($s)" }}")
            ArithmeticOperation.MUL -> listOf("$indent${comp.result} = ${comp.operands.reduce { acc, s -> "$acc * ($s)" }}")
            ArithmeticOperation.DIV -> listOf("$indent${comp.result} = ${comp.operands[0]} / ${comp.operands[1]}")
            ArithmeticOperation.MOD -> listOf("$indent${comp.result} = ${comp.operands[0]} % ${comp.operands[1]}")
        }
    }

    private fun evalBranch(comp: BranchComponent, indentationLevel: Int): List<String> {
        var indent = ""
        (1..indentationLevel).forEach { indent += "\t" }
        val res = mutableListOf("${indent}if (${comp.operand1} ")
        res[0] += when (comp.type) {
            ConditionalOperation.EQ -> "=="
            ConditionalOperation.NEQ -> "!="
            ConditionalOperation.GEQ -> ">="
            ConditionalOperation.LEQ -> "<="
        }
        res[0] += " ${comp.operand2}) {"
        for (expr in comp.body) {
            eval(expr, indentationLevel + 1).forEach { res.add(it) }
        }
        if (comp.hasElse){
            res.add("${indent}} else {")
            for (expr in comp.elseBody) {
            eval(expr, indentationLevel + 1).forEach { res.add(it) }
        }
        }
        res.add("$indent}")
        return res
    }

    private fun evalLoop(comp: LoopComponent, indentationLevel: Int): List<String> {
        var indent = ""
        (1..indentationLevel).forEach { indent += "\t" }
        val res = mutableListOf("${indent}while (${comp.operand1} ")
        res[0] += when (comp.type) {
            ConditionalOperation.EQ -> "=="
            ConditionalOperation.NEQ -> "!="
            ConditionalOperation.GEQ -> ">="
            ConditionalOperation.LEQ -> "<="
        }
        res[0] += " ${comp.operand2}) {"
        for (expr in comp.body) {
            eval(expr, indentationLevel + 1).forEach { res.add(it) }
        }
        res.add("$indent}")
        return res
    }
}
