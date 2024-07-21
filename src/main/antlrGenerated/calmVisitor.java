// Generated from calm.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link calmParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface calmVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link calmParser#code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCode(calmParser.CodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link calmParser#vardef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardef(calmParser.VardefContext ctx);
	/**
	 * Visit a parse tree produced by {@link calmParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(calmParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link calmParser#commentExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommentExpr(calmParser.CommentExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link calmParser#printExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintExpr(calmParser.PrintExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link calmParser#arithmeticExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticExpr(calmParser.ArithmeticExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link calmParser#branchExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranchExpr(calmParser.BranchExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link calmParser#loopExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopExpr(calmParser.LoopExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link calmParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVal(calmParser.ValContext ctx);
}