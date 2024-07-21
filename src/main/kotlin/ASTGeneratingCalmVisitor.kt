import calmParser.*
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.TerminalNodeImpl

class ASTGeneratingCalmVisitor {

    private val rootComponent = RootComponent(":)")

    fun visit(p0: ParseTree?): RootComponent {

        if (p0 is CodeContext) {
            return visitCode(p0)
        } else throw RuntimeException()
    }

    private fun visitCode(ctx: CodeContext?): RootComponent {
        if (ctx == null) throw RuntimeException()
        var inDataSegment = false
        for (child in ctx.children) {
            when (child) {
                is VardefContext -> if (!inDataSegment) rootComponent.vars.add(visitVardef(child))
                is TerminalNodeImpl -> if (child.text == "code:") {
                    if (!inDataSegment) inDataSegment = true else throw RuntimeException()
                }

                is ExprContext -> if (inDataSegment) {
                    rootComponent.tree.add(visitExpr(child))
                } else throw RuntimeException()

                else -> throw RuntimeException()
            }
        }
        return rootComponent
    }

    private fun visitVardef(ctx: VardefContext?): Pair<String, Int> {
        if (ctx == null || ctx.children.size != 3 || ctx.children.any { it !is TerminalNodeImpl }) throw RuntimeException()
        val name = ctx.children.first().text
        if (rootComponent.vars.map { it.first }.contains(name)) throw RuntimeException()
        val value = ctx.children[1].text.toIntOrNull() ?: throw RuntimeException()
        return Pair(name, value)
    }

    private fun visitExpr(ctx: ExprContext?): ASTComponent {
        if (ctx == null) throw RuntimeException()
        return when (val expr = ctx.children.first()) {
            is CommentExprContext -> visitCommentExpr(expr)
            is PrintExprContext -> visitPrintExpr(expr)
            is ArithmeticExprContext -> visitArithmeticExpr(expr)
            is BranchExprContext -> visitBranchExpr(expr)
            is LoopExprContext -> visitLoopExpr(expr)
            else -> throw RuntimeException()
        }
    }

    private fun visitCommentExpr(ctx: CommentExprContext?): ASTComponent {
        if (ctx == null) throw RuntimeException()
        return CommentComponent(ctx.text.removePrefix("//"))
    }

    private fun visitPrintExpr(ctx: PrintExprContext?): ASTComponent {
        if (ctx == null) throw RuntimeException()
        val printComponent = PrintComponent(ctx.children.first().text == "printc")
        for (child in ctx.children.drop(1)) {
            printComponent.content.add(getVal(child))
        }
        return printComponent
    }

    private fun visitArithmeticExpr(ctx: ArithmeticExprContext?): ASTComponent {
        if (ctx == null || ctx.children.size < 4) throw RuntimeException()
        val arithmeticComponent = ArithmeticComponent(
            when (ctx.children.first().text) {
                "add" -> ArithmeticOperation.ADD
                "sub" -> ArithmeticOperation.SUB
                "mul" -> ArithmeticOperation.MUL
                "div" -> ArithmeticOperation.DIV
                "mod" -> ArithmeticOperation.MOD
                else -> throw RuntimeException()
            }
        )
        if (arithmeticComponent.type == ArithmeticOperation.DIV && ctx.children.size != 4) throw RuntimeException()
        if (arithmeticComponent.type == ArithmeticOperation.MOD && ctx.children.size != 4) throw RuntimeException()
        if (ctx.children[1].text.toIntOrNull() != null) throw RuntimeException()
        arithmeticComponent.result = ctx.children[1].text
        for (child in ctx.children.drop(2)) {
            arithmeticComponent.operands.add(getVal(child))
        }
        return arithmeticComponent
    }

    private fun visitBranchExpr(ctx: BranchExprContext?): ASTComponent {
        if (ctx == null || ctx.children.size < 4) throw RuntimeException()
        val branchComponent = BranchComponent(
            when (ctx.children.first().text) {
                "ifeq", "ifeq0" -> ConditionalOperation.EQ
                "ifneq", "ifneq0" -> ConditionalOperation.NEQ
                "ifgeq", "ifgeq0" -> ConditionalOperation.GEQ
                "ifleq", "ifleq0" -> ConditionalOperation.LEQ
                else -> throw RuntimeException()
            }
        )
        val compareToZero = ctx.children.first().text.endsWith("0")
        if (compareToZero) {
            if (ctx.children[2] !is TerminalNodeImpl) throw RuntimeException()
            branchComponent.operand1 = getVal(ctx.children[1])
            branchComponent.operand2 = 0
        } else {
            if (ctx.children[3] !is TerminalNodeImpl || ctx.children.size < 5) throw RuntimeException()
            branchComponent.operand1 = getVal(ctx.children[1])
            branchComponent.operand2 = getVal(ctx.children[2])
        }
        if (ctx.children.last() !is TerminalNodeImpl) throw RuntimeException()
        for (child in ctx.children.drop(if (compareToZero) 3 else 4).dropLast(1)) {
            if (child is TerminalNodeImpl && child.text == "else") {
                if (branchComponent.hasElse) {
                    throw RuntimeException()
                } else {
                    branchComponent.hasElse = true
                }
            } else if (child is ExprContext) {
                if (branchComponent.hasElse) branchComponent.elseBody.add(visitExpr(child))
                else branchComponent.body.add(visitExpr(child))
            }
        }
        return branchComponent
    }

    private fun visitLoopExpr(ctx: LoopExprContext?): ASTComponent {
        if (ctx == null || ctx.children.size < 4) throw RuntimeException()
        val loopComponent = LoopComponent(
            when (ctx.children.first().text) {
                "whileeq", "whileeq0" -> ConditionalOperation.EQ
                "whileneq", "whileneq0" -> ConditionalOperation.NEQ
                "whilegeq", "whilegeq0" -> ConditionalOperation.GEQ
                "whileleq", "whileleq0" -> ConditionalOperation.LEQ
                else -> throw RuntimeException()
            }
        )
        val compareToZero = ctx.children.first().text.endsWith("0")
        if (compareToZero) {
            if (ctx.children[2] !is TerminalNodeImpl) throw RuntimeException()
            loopComponent.operand1 = getVal(ctx.children[1])
            loopComponent.operand2 = 0
        } else {
            if (ctx.children[3] !is TerminalNodeImpl || ctx.children.size < 5) throw RuntimeException()
            loopComponent.operand1 = getVal(ctx.children[1])
            loopComponent.operand2 = getVal(ctx.children[2])
        }
        if (ctx.children.last() !is TerminalNodeImpl) throw RuntimeException()
        for (child in ctx.children.drop(if (compareToZero) 3 else 4).dropLast(1)) {
            if (child !is ExprContext) throw RuntimeException()
            loopComponent.body.add(visitExpr(child))
        }
        return loopComponent
    }

    private fun getVal(ctx: ParseTree?): Any {
        if (ctx == null || ctx !is ValContext) throw RuntimeException()
        val value = ctx.children.first().text
        if (value.toIntOrNull() == null) {
            if (!rootComponent.vars.map { it.first }.contains(value)) throw RuntimeException()
            return value
        } else return value.toInt()
    }

}
