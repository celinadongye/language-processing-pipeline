
// File:   MH_Parser.java

// Java template file for parser component of Informatics 2A Assignment 1.
// Students should add a method body for the LL(1) parse table for Micro-Haskell.


import java.io.* ;

import jdk.nashorn.internal.parser.TokenLookup;
 
class MH_Parser extends GenParser implements PARSER {

    String startSymbol() {return "#Prog" ;}

    // Right hand sides of all productions in grammar:

    static String[] epsilon              = new String[] { } ;
    static String[] Decl_Prog            = new String[] {"#Decl", "#Prog"} ;
    static String[] TypeDecl_TermDecl    = new String[] {"#TypeDecl", "#TermDecl"} ;
    static String[] VAR_has_Type         = new String[] {"VAR", "::", "#Type", ";"} ;
    static String[] Type0_TypeRest       = new String[] {"#Type0", "#TypeRest"} ;
    static String[] arrow_Type           = new String[] {"->", "#Type"} ;
    static String[] Integer              = new String[] {"Integer"} ;
    static String[] Bool                 = new String[] {"Bool"} ;
    static String[] lbr_Type_rbr         = new String[] {"(", "#Type", ")"} ;
    static String[] VAR_Args_eq_Exp      = new String[] {"VAR", "#Args", "=", "#Exp", ";"} ;
    static String[] VAR_Args             = new String[] {"VAR", "#Args"} ;
    static String[] Exp0                 = new String[] {"#Exp0"} ;
    static String[] if_then_else         = new String[] {"if", "#Exp", "then", "#Exp", "else", "#Exp"} ;
    static String[] Exp1_Rest0           = new String[] {"#Exp1", "#Rest0"} ;
    static String[] eqeq_Exp1            = new String[] {"==", "#Exp1"} ;
    static String[] lteq_Exp1            = new String[] {"<=", "#Exp1"} ;
    static String[] Exp2_Rest1           = new String[] {"#Exp2", "#Rest1"} ;
    static String[] plus_Exp2_Rest1      = new String[] {"+", "#Exp2", "#Rest1"} ;
    static String[] minus_Exp2_Rest1     = new String[] {"-", "#Exp2", "#Rest1"} ;
    static String[] Exp3_Rest2           = new String[] {"#Exp3", "#Rest2"} ;
    static String[] VAR                  = new String[] {"VAR"} ;
    static String[] NUM                  = new String[] {"NUM"} ;
    static String[] BOOLEAN              = new String[] {"BOOLEAN"} ;
    static String[] lbr_Exp_rbr          = new String[] {"(", "#Exp", ")"} ;

    // may add auxiliary methods here if desired

    String[] tableEntry (String nonterm, String tokClass) {
        if (nonterm.equals("#Prog")) { // Prog → ε | Decl Prog
            if (tokClass == null) {
                return epsilon;
            }
            else if (tokClass.equals("VAR")) {
                return Decl_Prog;
            }
            else {
                System.out.println("Parse error: grammar is incorrect"); // corresponds to blank cell in parse table
                return null;
            }
        }
        if (nonterm.equals("#Decl")) { // Decl → TypeDecl TermDecl
            if (tokClass.equals("VAR")) {
                return TypeDecl_TermDecl;
            }
            else {
                System.out.println("Parse error: grammar is incorrect");
                return null;
            }
        }
        if (nonterm.equals("#TypeDecl")) { // TypeDecl → VAR :: Type ;
            if (tokClass.equals("VAR")) {
                return VAR_has_Type;
            }
            else {
                System.out.println("Parse error: grammar is incorrect");
                return null;
            }
        }
        if (nonterm.equals("#Type")) { // Type → Type0 TypeRest
            if (tokClass.equals("Integer") || tokClass.equals("Bool") || tokClass.equals("(")) {
                return Type0_TypeRest;
            }
            else {
                System.out.println("Parse error: grammar is incorrect");
                return null;
            }
        }
        if (nonterm.equals("#TypeRest")) { // TypeRest → ε | -> Type
            if (tokClass.equals(")") || tokClass.equals(";")) {
                return epsilon;
            }
            else if (tokClass.equals("->")) {
                return arrow_Type;
            }
            else {
                System.out.println("Parse error: grammar is incorrect");
                return null;
            }
        }
        if (nonterm.equals("#Type0")) { // Type0 → Integer | Bool | ( Type )
            if (tokClass.equals("Integer")) {
                return Integer;
            }
            else if (tokClass.equals("Bool")) {
                return Bool;
            }
            else if (tokClass.equals("(")) {
                return lbr_Type_rbr;
            }
            else {
                System.out.println("Parse error: grammar is incorrect");
                return null;
            }
        }
        if (nonterm.equals("#TermDecl")) { // TermDecl → VAR Args = Exp ;
            if (tokClass.equals("VAR")) {
                return VAR_Args_eq_Exp;
            }
            else {
                System.out.println("Parse error: grammar is incorrect");
                return null;
            }
        }
        if (nonterm.equals("#Args")) { // Args → ε | VAR Args
            if (tokClass.equals("VAR")) {
                return VAR_Args;
            }
            else if (tokClass.equals("=")) {
                return epsilon;
            }
            else {
                System.out.println("Parse error: grammar is incorrect");
                return null;
            }
        }
        if (nonterm.equals("#Exp")) { // Exp → Exp0 | if Exp then Exp else Exp
            if (tokClass.equals("VAR") || tokClass.equals("NUM") || tokClass.equals("BOOLEAN") || tokClass.equals("(")) {
                return Exp0;
            }
            else if (tokClass.equals("if")) {
                return if_then_else;
            }
            else {
                System.out.println("Parse error: grammar is incorrect");
                return null;
            }
        }
        if (nonterm.equals("#Exp0")) { // Exp0 → Exp1 Rest0
            if (tokClass.equals("VAR") || tokClass.equals("NUM") || tokClass.equals("BOOLEAN") || tokClass.equals("(")) {
                return Exp1_Rest0;
            }
            else {
                System.out.println("Parse error: grammar is incorrect");
                return null;
            }
        }
        if (nonterm.equals("#Rest0")) { // Rest0 → ε|==Exp1 |<=Exp1
            if (tokClass.equals("then") || tokClass.equals("else") || tokClass.equals(")") || tokClass.equals(";")) {
                return epsilon;
            }
            else if (tokClass.equals("==")) {
                return eqeq_Exp1;
            }
            else if (tokClass.equals("<=")) {
                return lteq_Exp1;
            }
            else {
                System.out.println("Parse error: grammar is incorrect");
                return null;
            }
        }
        if (nonterm.equals("#Exp1")){ // Exp1 → Exp2 Rest1
            if (tokClass.equals("VAR") || tokClass.equals("NUM") || tokClass.equals("BOOLEAN") || tokClass.equals("(")) {
                return Exp2_Rest1;
            }
            else {
                System.out.println("Parse error: grammar is incorrect");
                return null;
            }
        }
        if (nonterm.equals("#Rest1")){ // Rest1 → ε|+Exp2Rest1 |-Exp2Rest1
            if (tokClass.equals("then") || tokClass.equals("else") || tokClass.equals(")") || tokClass.equals(";") || tokClass.equals("==") || tokClass.equals("<=")) {
                return epsilon;
            }
            else if (tokClass.equals("+")) {
                return plus_Exp2_Rest1;
            }
            else if (tokClass.equals("-")) {
                return minus_Exp2_Rest1;
            }
            else {
                System.out.println("Parse error: grammar is incorrect");
                return null;
            }
        }
        if (nonterm.equals("#Exp2")){ // Exp2 → Exp3 Rest2
            if (tokClass.equals("VAR") || tokClass.equals("NUM") || tokClass.equals("BOOLEAN") || tokClass.equals("(")) {
                return Exp3_Rest2;
            }
            else {
                System.out.println("Parse error: grammar is incorrect");
                return null;
            }
        }
        if (nonterm.equals("#Rest2")){ // Rest2 → ε | Exp3 Rest2
            if (tokClass.equals("VAR") || tokClass.equals("NUM") || tokClass.equals("BOOLEAN") || tokClass.equals("(")) {
                return Exp3_Rest2;
            }
            else if (tokClass.equals("then") || tokClass.equals("else") || tokClass.equals(")") || tokClass.equals(";") || tokClass.equals("==") || tokClass.equals("<=") || tokClass.equals("+") || tokClass.equals("-")) {
                return epsilon;
            }
            else {
                System.out.println("Parse error: grammar is incorrect");
                return null;
            }
        }
        if (nonterm.equals("#Exp3")){ // Exp3 → VAR|NUM|BOOLEAN|(Exp)
            if (tokClass.equals("VAR")) {
                return VAR;
            }
            else if (tokClass.equals("NUM")) {
                return NUM;
            }
            else if (tokClass.equals("BOOLEAN")) {
                return BOOLEAN;
            }
            else if (tokClass.equals("(")) {
                return lbr_Exp_rbr;
            }
            else {
                System.out.println("Parse error: grammar is incorrect");
                return null;
            }
        }
        else return null;
    }
}


// For testing

class MH_ParserDemo {

    static PARSER MH_Parser = new MH_Parser() ;

    public static void main (String[] args) throws Exception {
	Reader reader = new BufferedReader (new FileReader (args[0])) ;
	LEX_TOKEN_STREAM MH_Lexer = 
	    new CheckedSymbolLexer (new MH_Lexer (reader)) ;
	TREE theTree = MH_Parser.parseTokenStream (MH_Lexer) ;
    }
}

/*
Autotesting parse table:
   Exception raised at (#Decl, $), should have been blank.
   Exception raised at (#TypeDecl, $), should have been blank.
   Exception raised at (#Type, $), should have been blank.
   Exception raised at (#Type0, $), should have been blank.
   Exception raised at (#TypeRest, $), should have been blank.
   Exception raised at (#TermDecl, $), should have been blank.
   Exception raised at (#Args, $), should have been blank.
   Exception raised at (#Exp, $), should have been blank.
   Exception raised at (#Exp0, $), should have been blank.
   Exception raised at (#Rest0, $), should have been blank.
   Exception raised at (#Exp1, $), should have been blank.
   Exception raised at (#Rest1, $), should have been blank.
   Exception raised at (#Exp2, $), should have been blank.
   Exception raised at (#Rest2, $), should have been blank.
   Exception raised at (#Exp3, $), should have been blank.

60 correct entries out of 60: 24.0/24.
No blank cells wrongly filled. 6/6
15 unhandled exceptions were raised: 1 mark deducted.

TOTAL: 29 marks out of 30.

HUMAN MARKER COMMENTS:Good code that shows good understanding of the parsing table. The exceptions are raised by the method equals when tokClass is null (we should firstly check that is not null before using the method equals). In these cases, the method should return null as the entries of the parsing table are blank.

FINAL MARK FOR PART B: 29 out of 30
*/
