class ASTOptimizer {
    private lateinit var ast: RootComponent
    fun optimize(
        ast: RootComponent,
        pruneComments: Boolean = false,
        precalculateArithmeticExpressions: Boolean = false,
        compactArithmeticExpressions: Boolean = false
    ): RootComponent {
        this.ast = ast
        while (true) {
            if (pruneComments) {
                if (pruneComments()) continue
            }
            if (precalculateArithmeticExpressions) {
                if (precalculateArithmeticExpressions()) continue
            }
            if (compactArithmeticExpressions) {
                if (compactArithmeticExpressions()) continue
            }
            break
        }
        return ast
    }

    private fun pruneComments(): Boolean {
        return _pruneCommentsRecursively(ast.tree)
    }

    private fun _pruneCommentsRecursively(ast: MutableList<ASTComponent>): Boolean {
        var changes = ast.count { it is CommentComponent } > 0
        ast.removeIf { it is CommentComponent }
        ast.forEach {
            when (it) {
                is BranchComponent -> changes =
                    changes or _pruneCommentsRecursively(it.body) or _pruneCommentsRecursively(it.elseBody)

                is LoopComponent -> changes = changes or _pruneCommentsRecursively(it.body)
            }
        }
        return changes
    }

    private fun precalculateArithmeticExpressions(): Boolean {
        var changes = false
        while (ast.tree.firstOrNull() is ArithmeticComponent) {
            changes = true
            val operation = ast.tree.first() as ArithmeticComponent
            val target = ast.vars.indexOfFirst { it.first == operation.result }
            when (operation.type) {
                ArithmeticOperation.ADD -> {
                    var result = getVal(operation.operands.first())
                    operation.operands.drop(1).forEach { result += getVal(it) }
                    Pair(ast.vars[target].first, result).also { ast.vars[target] = it }
                    // I know that the line below is simpler than the line above, but it caused a class cast exception
                    //ast.vars[target] = Pair(ast.vars[target].first, result)
                }

                ArithmeticOperation.SUB -> {
                    var result = getVal(operation.operands.first())
                    operation.operands.drop(1).forEach { result -= getVal(it) }
                    Pair(ast.vars[target].first, result).also { ast.vars[target] = it }
                }

                ArithmeticOperation.MUL -> {
                    var result = getVal(operation.operands.first())
                    operation.operands.drop(1).forEach { result *= getVal(it) }
                    Pair(ast.vars[target].first, result).also { ast.vars[target] = it }
                }

                ArithmeticOperation.DIV -> {
                    val result = getVal(operation.operands.first()) / getVal(operation.operands[1])
                    Pair(ast.vars[target].first, result).also { ast.vars[target] = it }
                }

                ArithmeticOperation.MOD -> {
                    val result = getVal(operation.operands.first()) % getVal(operation.operands[1])
                    Pair(ast.vars[target].first, result).also { ast.vars[target] = it }
                }
            }
            ast.tree.removeFirst()
        }
        return changes
    }

    private fun getVal(input: Any): Int {
        return when (input) {
            is Int -> input
            is String -> ast.vars.first { it.first == input }.second
            else -> throw RuntimeException()
        }
    }

    private fun compactArithmeticExpressions(): Boolean {
        return _compactArithmeticExpressionsRecursively(ast.tree)
    }

    private fun _compactArithmeticExpressionsRecursively(ast: MutableList<ASTComponent>): Boolean {
        var changes = false
        ast.forEach { comp ->
            when (comp) {
                is ArithmeticComponent -> {
                    if (comp.operands.count { it is Int } > 1 && comp.operands.size > 2) {
                        when (comp.type) {
                            ArithmeticOperation.ADD, ArithmeticOperation.SUB -> {
                                var compressedVal = 0
                                comp.operands.filterIsInstance<Int>().forEach { compressedVal += getVal(it) }
                                comp.operands.removeIf { it is Int }
                                if (compressedVal != 0) comp.operands += compressedVal
//                                if(comp.operands.size == 0) comp.operands += 0
                                if(comp.operands.size == 1) comp.operands += 0
                                changes = true
                            }

                            ArithmeticOperation.MUL -> {
                                var compressedVal = 1
                                comp.operands.filterIsInstance<Int>().forEach { compressedVal *= getVal(it) }
                                comp.operands.removeIf { it is Int }
                                if (compressedVal != 0) comp.operands += compressedVal
//                                if(comp.operands.size == 0) comp.operands += 1
                                if(comp.operands.size == 1) comp.operands += 1
                                changes = true
                            }

                            else -> {}
                        }
                    }
                }

                is BranchComponent -> changes =
                    changes or _compactArithmeticExpressionsRecursively(comp.body) or _compactArithmeticExpressionsRecursively(
                        comp.elseBody
                    )

                is LoopComponent -> changes = changes or _compactArithmeticExpressionsRecursively(comp.body)
            }
        }
        return changes
    }

}