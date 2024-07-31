class ASTOptimizer {
    private lateinit var ast : RootComponent
    fun optimize(
        ast: RootComponent, pruneComments: Boolean = false, precalculateArithmeticExpressions: Boolean = false
    ): RootComponent {
        this.ast = ast
        while (true) {
            if (pruneComments) {
                if (pruneComments(this.ast.tree)) continue
            }
            if(precalculateArithmeticExpressions) {
                if (precalculateArithmeticExpressions()) continue
            }
            break
        }
        this.ast.vars[0] = Pair("test", 0)
        return ast
    }

    private fun pruneComments(ast: MutableList<ASTComponent>): Boolean {
        var changes = ast.count { it is CommentComponent } > 0
        ast.removeIf { it is CommentComponent }
        ast.forEach {
            when (it) {
                is BranchComponent -> changes = changes || pruneComments(it.body) || pruneComments(it.elseBody)
                is LoopComponent -> changes = changes || pruneComments(it.body)
            }
        }
        return changes
    }

    private fun precalculateArithmeticExpressions(): Boolean {
        var changes = false
        while (ast.tree.first() is ArithmeticComponent){
            changes = true
            val operation = ast.tree.first() as ArithmeticComponent
            val target = ast.vars.indexOfFirst { it.first == operation.result }
            when (operation.type){
                ArithmeticOperation.ADD -> {
                    var result = getVal(operation.operands.first())
                    operation.operands.drop(1).forEach { result += getVal(it) }
                    ast.vars[target] = Pair(ast.vars[target].first, result)
                }
                ArithmeticOperation.SUB -> {
                    var result = getVal(operation.operands.first())
                    operation.operands.drop(1).forEach { result -= getVal(it) }
                    ast.vars[target] = Pair(ast.vars[target].first, result)
                }
                ArithmeticOperation.MUL -> {
                    var result = getVal(operation.operands.first())
                    operation.operands.drop(1).forEach { result *= getVal(it) }
                    ast.vars[target] = Pair(ast.vars[target].first, result)
                }
                ArithmeticOperation.DIV -> {
                    val result = getVal(operation.operands.first()) / getVal(operation.operands[1])
                    ast.vars[target] = Pair(ast.vars[target].first, result)
                }
                ArithmeticOperation.MOD -> {
                    val result = getVal(operation.operands.first()) % getVal(operation.operands[1])
                    ast.vars[target] = Pair(ast.vars[target].first, result)
                }
            }
            ast.tree.removeFirst()
        }
        return changes
    }

    private fun getVal (input : Any) : Int{
        return when (input){
            is Int -> input
            is String -> ast.vars.first { it.first == input }.second
            else -> throw RuntimeException()
        }
    }
}