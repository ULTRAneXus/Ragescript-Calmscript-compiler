import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File

fun main() {
//    val input = File("src/main/resources/test.rage").useLines { it.toList() }
//    val calmedInput = RageToCalm().rageToCalm(input)
    val calmedInput = File("src/main/resources/fibonacci.calm").useLines { it.toList() }
    val inputStream = CharStreams.fromString(calmedInput.reduce { acc, it -> "$acc\n$it" })
    val lexer = calmLexer(inputStream)
    val commonTokenStream = CommonTokenStream(lexer)
    val parser = calmParser(commonTokenStream)
    val context = parser.code()
    val visitor = ASTGeneratingCalmVisitor()
    val ast = visitor.visit(context)
    val code = JVMASMBackend().generate(ast)
    val out = File("src/main/compilerGenerated/Out.j")
    out.delete()
    out.writeText(code.reduce { acc, it -> "$acc\n$it" } + "\n")
}