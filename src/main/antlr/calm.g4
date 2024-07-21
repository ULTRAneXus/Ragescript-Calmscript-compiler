grammar calm;

code : vardef* SEP NEWLINE expr* ;
vardef : VAR NUMBER NEWLINE ;
expr : (commentExpr|printExpr|arithmeticExpr|branchExpr|loopExpr) NEWLINE ;
commentExpr : COMMENT ;
printExpr : PRINT val+ ;
arithmeticExpr : ARITHMETIC val+ ;
branchExpr : BRANCH val+ NEWLINE expr* (BRANCHSPLIT NEWLINE expr*)? BRANCHEND ;
loopExpr : LOOP val+ NEWLINE expr* LOOPEND;
val : (VAR|NUMBER) ;

/*
 * Lexer Rules
 */

VAR : 'var'NUM ;
NUMBER : '-'?NUM ;
SEP : 'code:' ;
COMMENT : '//'TEXT+ ;
PRINT : 'print'('n'|'c') ;
ARITHMETIC : ('add'|'sub'|'mul'|'div'|'mod') ;
BRANCH : 'if'(('n'|'g'|'l')?)'eq'('0'?) ;
BRANCHSPLIT : 'else' ;
BRANCHEND : 'endif' ;
LOOP : 'while'(('n'|'g'|'l')?)'eq'('0'?) ;
LOOPEND : 'endwhile' ;
fragment NUM : [0-9]+ ;
fragment TEXT : [a-zA-Z0-9.,!=\- \t]+ ;
NEWLINE : [\r\n]+ ;
WHITESPACE : [ \t]+ -> skip;