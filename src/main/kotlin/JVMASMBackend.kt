class JVMASMBackend {
    private lateinit var ast: RootComponent
    private var labelCounter = 1

    fun generate(ast: RootComponent): List<String> {
        this.ast = ast
        val res = mutableListOf<String>()
        res += ".class public Out"
        res += ".super java/lang/Object"
        res += ".method public static main : ([Ljava/lang/String;)V"
        res += "\t.code stack 2 locals ${ast.vars.size + 1}\n"
        for (variable in this.ast.vars) {
            res += "\t\tbipush ${variable.second}\n\t\tistore ${getIndexOfVar(variable.first)}\n"
        }
        for (component in this.ast.tree) {
            eval(component, 2).forEach { res += it }
        }
        res += "\t\treturn\n"
        res += "\t.end code"
        res += ".end method"
        res += ".end class"
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
        (1..indentationLevel).forEach { _ -> indent += "\t" }
        return listOf("$indent;${comp.content}\n")
    }

    private fun evalPrint(comp: PrintComponent, indentationLevel: Int): List<String> {
        var indent = ""
        (1..indentationLevel).forEach { _ -> indent += "\t" }
        val res = mutableListOf<String>()
        for (value in comp.content) {
            res += mutableListOf("${indent}getstatic Field java/lang/System out Ljava/io/PrintStream;")
            res += when (value) {
                is String -> "${indent}iload ${getIndexOfVar(value)}"
                is Int -> "${indent}bipush $value"
                else -> throw RuntimeException()
            }
            if (comp.asChar) {
                res += "${indent}i2c"
                res += "${indent}invokevirtual Method java/io/PrintStream print (C)V\n"
            } else res += "${indent}invokevirtual Method java/io/PrintStream print (I)V\n"
        }
        return res
    }

    private fun evalArithmetic(comp: ArithmeticComponent, indentationLevel: Int): List<String> {
        var indent = ""
        (1..indentationLevel).forEach { _ -> indent += "\t" }
        val res = mutableListOf<String>()
        res += when (comp.operands.first()) {
            is String -> "${indent}iload ${getIndexOfVar(comp.operands.first() as String)}"
            is Int -> "${indent}bipush ${comp.operands.first() as Int}"
            else -> throw RuntimeException()
        }
        for (operand in comp.operands.drop(1)) {
            res += when (operand) {
                is String -> "${indent}iload ${getIndexOfVar(operand)}"
                is Int -> "${indent}bipush $operand"
                else -> throw RuntimeException()
            }
            res += when (comp.type) {
                ArithmeticOperation.ADD -> "${indent}iadd"
                ArithmeticOperation.SUB -> "${indent}isub"
                ArithmeticOperation.MUL -> "${indent}imul"
                ArithmeticOperation.DIV -> "${indent}idiv"
                ArithmeticOperation.MOD -> "${indent}irem"
            }
        }
        res += "${indent}istore ${getIndexOfVar(comp.result)}\n"
        return res
    }

    private fun evalBranch(comp: BranchComponent, indentationLevel: Int): List<String> {
        var indent = ""
        (1..indentationLevel).forEach { _ -> indent += "\t" }
        val res = mutableListOf<String>()
        val labelID = labelCounter
        labelCounter++
        res += when (comp.operand1) {
            is String -> "${indent}iload ${getIndexOfVar(comp.operand1 as String)}"
            is Int -> "${indent}bipush ${comp.operand1}"
            else -> throw RuntimeException()
        }
        res += when (comp.operand2) {
            is String -> "${indent}iload ${getIndexOfVar(comp.operand2 as String)}"
            is Int -> "${indent}bipush ${comp.operand2}"
            else -> throw RuntimeException()
        }
        if (comp.hasElse) {
            res += when (comp.type) {
                ConditionalOperation.EQ -> "${indent}if_icmpne LELSE$labelID\n"
                ConditionalOperation.NEQ -> "${indent}if_icmpeq LELSE$labelID\n"
                ConditionalOperation.GEQ -> "${indent}if_icmplt LELSE$labelID\n"
                ConditionalOperation.LEQ -> "${indent}if_icmpgt LELSE$labelID\n"
            }
        } else {
            res += when (comp.type) {
                ConditionalOperation.EQ -> "${indent}if_icmpne LIFEND$labelID\n"
                ConditionalOperation.NEQ -> "${indent}if_icmpeq LIFEND$labelID\n"
                ConditionalOperation.GEQ -> "${indent}if_icmplt LIFEND$labelID\n"
                ConditionalOperation.LEQ -> "${indent}if_icmpgt LIFEND$labelID\n"
            }
        }
        for (expr in comp.body) {
            eval(expr, indentationLevel + 1).forEach { res.add(it) }
        }
        if (comp.hasElse) {
            res += "${indent}\tgoto LIFEND$labelID\n"
            res += "${indent}LELSE$labelID:\n"
            for (expr in comp.elseBody) {
                eval(expr, indentationLevel + 1).forEach { res.add(it) }
            }
        }
        res += "${indent}LIFEND$labelID:\n"
        return res
    }

    private fun evalLoop(comp: LoopComponent, indentationLevel: Int): List<String> {
        var indent = ""
        (1..indentationLevel).forEach { _ -> indent += "\t" }
        val res = mutableListOf<String>()
        val labelID = labelCounter
        labelCounter++
        res += "${indent}LWHILESTART$labelID:"
        res += when (comp.operand1) {
            is String -> "${indent}iload ${getIndexOfVar(comp.operand1 as String)}"
            is Int -> "${indent}bipush ${comp.operand1}"
            else -> throw RuntimeException()
        }
        res += when (comp.operand2) {
            is String -> "${indent}iload ${getIndexOfVar(comp.operand2 as String)}"
            is Int -> "${indent}bipush ${comp.operand2}"
            else -> throw RuntimeException()
        }
        res += when (comp.type) {
            ConditionalOperation.EQ -> "${indent}if_icmpne LWHILEEND$labelID\n"
            ConditionalOperation.NEQ -> "${indent}if_icmpeq LWHILEEND$labelID\n"
            ConditionalOperation.GEQ -> "${indent}if_icmplt LWHILEEND$labelID\n"
            ConditionalOperation.LEQ -> "${indent}if_icmpgt LWHILEEND$labelID\n"
        }
        for (expr in comp.body) {
            eval(expr, indentationLevel + 1).forEach { res.add(it) }
        }
        res += "${indent}\tgoto LWHILESTART$labelID\n"
        res += "${indent}LWHILEEND$labelID:\n"
        return res
    }

    private fun getIndexOfVar(variable: String): Int {
        return ast.vars.indexOfFirst { it.first == variable } + 1
    }
}
