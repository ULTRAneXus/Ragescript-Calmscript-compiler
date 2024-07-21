// Generated from calm.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link calmParser}.
 */
public interface calmListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link calmParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(calmParser.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link calmParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(calmParser.CodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link calmParser#vardef}.
	 * @param ctx the parse tree
	 */
	void enterVardef(calmParser.VardefContext ctx);
	/**
	 * Exit a parse tree produced by {@link calmParser#vardef}.
	 * @param ctx the parse tree
	 */
	void exitVardef(calmParser.VardefContext ctx);
	/**
	 * Enter a parse tree produced by {@link calmParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(calmParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link calmParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(calmParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link calmParser#commentExpr}.
	 * @param ctx the parse tree
	 */
	void enterCommentExpr(calmParser.CommentExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link calmParser#commentExpr}.
	 * @param ctx the parse tree
	 */
	void exitCommentExpr(calmParser.CommentExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link calmParser#printExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr(calmParser.PrintExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link calmParser#printExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr(calmParser.PrintExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link calmParser#arithmeticExpr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpr(calmParser.ArithmeticExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link calmParser#arithmeticExpr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpr(calmParser.ArithmeticExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link calmParser#branchExpr}.
	 * @param ctx the parse tree
	 */
	void enterBranchExpr(calmParser.BranchExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link calmParser#branchExpr}.
	 * @param ctx the parse tree
	 */
	void exitBranchExpr(calmParser.BranchExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link calmParser#loopExpr}.
	 * @param ctx the parse tree
	 */
	void enterLoopExpr(calmParser.LoopExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link calmParser#loopExpr}.
	 * @param ctx the parse tree
	 */
	void exitLoopExpr(calmParser.LoopExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link calmParser#val}.
	 * @param ctx the parse tree
	 */
	void enterVal(calmParser.ValContext ctx);
	/**
	 * Exit a parse tree produced by {@link calmParser#val}.
	 * @param ctx the parse tree
	 */
	void exitVal(calmParser.ValContext ctx);
}