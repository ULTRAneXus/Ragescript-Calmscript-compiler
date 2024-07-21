interface ASTComponent

data class RootComponent(val smiley:String) : ASTComponent {
    val vars: MutableList<Pair<String, Int>> = mutableListOf()
    val tree: MutableList<ASTComponent> = mutableListOf()
}

data class CommentComponent(val content: String) : ASTComponent

data class PrintComponent(val asChar: Boolean) : ASTComponent {
    val content: MutableList<Any> = mutableListOf()
}

enum class ArithmeticOperation { ADD, SUB, MUL, DIV, MOD }
data class ArithmeticComponent(val type: ArithmeticOperation) : ASTComponent {
    var result: String = ""
    val operands: MutableList<Any> = mutableListOf()
}

enum class ConditionalOperation { EQ, NEQ, GEQ, LEQ }
data class BranchComponent(val type: ConditionalOperation) : ASTComponent {
    var hasElse = false
    var operand1: Any = 0
    var operand2: Any = 1
    val body: MutableList<ASTComponent> = mutableListOf()
    val elseBody: MutableList<ASTComponent> = mutableListOf()

}

data class LoopComponent(val type: ConditionalOperation) : ASTComponent {
    var operand1: Any = 0
    var operand2: Any = 1
    val body: MutableList<ASTComponent> = mutableListOf()

}