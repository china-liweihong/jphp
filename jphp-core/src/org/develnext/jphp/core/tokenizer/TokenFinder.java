package org.develnext.jphp.core.tokenizer;

import org.develnext.jphp.core.tokenizer.token.*;
import org.develnext.jphp.core.tokenizer.token.expr.BackslashExprToken;
import org.develnext.jphp.core.tokenizer.token.expr.BraceExprToken;
import org.develnext.jphp.core.tokenizer.token.expr.CommaToken;
import org.develnext.jphp.core.tokenizer.token.expr.DollarExprToken;
import org.develnext.jphp.core.tokenizer.token.expr.operator.*;
import org.develnext.jphp.core.tokenizer.token.expr.value.*;
import org.develnext.jphp.core.tokenizer.token.expr.value.macro.*;
import org.develnext.jphp.core.tokenizer.token.stmt.*;

import java.util.HashMap;

public class TokenFinder {

    private final static HashMap<String, Class<? extends Token>> patterns = new HashMap<String, Class<? extends Token>>(){{
        put("<?", OpenTagToken.class);
        put("<?php", OpenTagToken.class);
        put("?>", BreakToken.class);
        put("<?=", OpenEchoTagToken.class);
        put("/*", CommentToken.class);
        put("/**", CommentToken.class);
        put("//", CommentToken.class);
        put("<<<", StringStartDocToken.class);

        put("+", PlusExprToken.class);
        put("-", MinusExprToken.class);
        put("*", MulExprToken.class);
        put("/", DivExprToken.class);
        put("%", ModExprToken.class);
        put("=", AssignExprToken.class);
        put("\\", BackslashExprToken.class);
        put("==", EqualExprToken.class);
        put("!=", BooleanNotEqualExprToken.class);
        put("<>", BooleanNotEqualExprToken.class);
        put("===", IdenticalExprToken.class);
        put("!==", NotIdenticalExprToken.class);
        put(">", GreaterExprToken.class);
        put(">=", GreaterOrEqualExprToken.class);
        put("<", SmallerExprToken.class);
        put("<=", SmallerOrEqualToken.class);

        put("&&", BooleanAndExprToken.class);
        put("and", BooleanAnd2ExprToken.class);
        put("||", BooleanOrExprToken.class);
        put("or", BooleanOr2ExprToken.class);
        put("xor", BooleanXorExprToken.class);
        put("!", BooleanNotExprToken.class);

        put("?", ValueIfElseToken.class);

        put("~", NotExprToken.class);
        put("^", XorExprToken.class);
        put("|", OrExprToken.class);
        put("&", AndExprToken.class);
        put(">>", ShiftRightExprToken.class);
        put("<<", ShiftLeftExprToken.class);

        put("@", SilentToken.class);

        put("->", DynamicAccessExprToken.class);
        put("::", StaticAccessExprToken.class);
        put("=>", KeyValueExprToken.class);

        put("+=", AssignPlusExprToken.class);
        put("-=", AssignMinusExprToken.class);
        put("*=", AssignMulExprToken.class);
        put("/=", AssignDivExprToken.class);
        put("%=", AssignModExprToken.class);
        put(".=", AssignConcatExprToken.class);
        put("^=", AssignXorExprToken.class);
        put("&=", AssignAndExprToken.class);
        put("|=", AssignOrExprToken.class);
        put("++", IncExprToken.class);
        put("--", DecExprToken.class);
        put(">>=", AssignShiftRightExprToken.class);
        put("<<=", AssignShiftLeftExprToken.class);

        put("new", NewExprToken.class);
        put("clone", CloneExprToken.class);
        put("instanceof", InstanceofExprToken.class);
        put("insteadof", InsteadofStmtToken.class);
        put(".", ConcatExprToken.class);
        put(":", ColonToken.class);
        put("true", BooleanExprToken.class);
        put("false", BooleanExprToken.class);
        put("null", NullExprToken.class);
        put(";", SemicolonToken.class);
        put("&", AmpersandRefToken.class);
        put(",", CommaToken.class);
        put("$", DollarExprToken.class);

        put("{", BraceExprToken.class);
        put("[", BraceExprToken.class);
        put("(", BraceExprToken.class);
        put("}", BraceExprToken.class);
        put("]", BraceExprToken.class);
        put(")", BraceExprToken.class);

        put("static", StaticExprToken.class);
        put("self", SelfExprToken.class);
        put("parent", ParentExprToken.class);
        //put("$this", ThisExprToken.class);

        //put("array", ArrayExprToken.class);
        put("as", AsStmtToken.class);
        put("if", IfStmtToken.class);
        put("else", ElseStmtToken.class);
        put("elseif", ElseIfStmtToken.class);
        put("while", WhileStmtToken.class);
        put("do", DoStmtToken.class);
        put("for", ForStmtToken.class);
        put("foreach", ForeachStmtToken.class);
        put("switch", SwitchStmtToken.class);
        put("case", CaseStmtToken.class);
        put("default", DefaultStmtToken.class);
        put("declare", DeclareStmtToken.class);
        put("return", ReturnStmtToken.class);
        put("endif", EndifStmtToken.class);
        put("endforeach", EndforeachStmtToken.class);
        put("endfor", EndforStmtToken.class);
        put("endwhile", EndwhileStmtToken.class);
        put("endswitch", EndswitchStmtToken.class);
        put("enddeclare", EnddeclareStmtToken.class);
        put("break", BreakStmtToken.class);
        put("continue", ContinueStmtToken.class);
        put("goto", GotoStmtToken.class);

        put("unset", UnsetExprToken.class);
        put("isset", IssetExprToken.class);
        put("empty", EmptyExprToken.class);
        put("die", DieExprToken.class);
        put("exit", DieExprToken.class);

        put("class", ClassStmtToken.class);
        put("interface", InterfaceStmtToken.class);
        put("trait", TraitStmtToken.class);
        put("function", FunctionStmtToken.class);
        put("const", ConstStmtToken.class);
        put("namespace", NamespaceStmtToken.class);
        put("use", NamespaceUseStmtToken.class);
        //put("uses", UsesStmtToken.class);
        put("abstract", AbstractStmtToken.class);
        put("final", FinalStmtToken.class);
        put("private", PrivateStmtToken.class);
        put("protected", ProtectedStmtToken.class);
        put("public", PublicStmtToken.class);
        put("var", VarStmtToken.class);
        put("try", TryStmtToken.class);
        put("catch", CatchStmtToken.class);
        put("finally", FinallyStmtToken.class);
        put("throw", ThrowStmtToken.class);
        put("extends", ExtendsStmtToken.class);
        put("implements", ImplementsStmtToken.class);
        put("global", GlobalStmtToken.class);
        put("list", ListExprToken.class);

        put("__line__", LineMacroToken.class);
        put("__file__", FileMacroToken.class);
        put("__dir__", DirMacroToken.class);
        put("__function__", FunctionMacroToken.class);
        put("__class__", ClassMacroToken.class);
        put("__method__", MethodMacroToken.class);
        put("__trait__", TraitMacroToken.class);
        put("__namespace__", NamespaceMacroToken.class);

        put("include", IncludeExprToken.class);
        put("include_once", IncludeOnceExprToken.class);
        put("require", RequireExprToken.class);
        put("require_once", RequireOnceExprToken.class);
        put("echo", EchoStmtToken.class);
        put("print", PrintNameToken.class);
    }};

    public TokenFinder() {
    }

    private boolean isVarialbe(String word) {
        char ch = word.charAt(0);
        if (ch != '$' || word.length() < 2) {
            return false;
        }
        for (int i = 1; i < word.length(); i++) {
            ch = word.charAt(i);
            if ((i == 1 && Character.isDigit(ch)) || (ch != '_' && !GrammarUtils.isNameChar(ch))) {
                return false;
            }
        }
        return true;
    }

    private boolean isOctal(String word) {
        if (word.length() < 2) { // at least 0[digit]
            return false;
        }
        char ch = word.charAt(0);
        if (ch != '0') {
            return false;
        }
        for (int i = 1; i < word.length(); i++) {
            ch = word.charAt(i);
            if (ch < '0' || ch > '7') {
                return false;
            }
        }
        return true;
    }

    private boolean isInteger(String word) {
        char ch;
        for (int i = 0; i < word.length(); i++) {
            ch = word.charAt(i);
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }

    private boolean isBinaryInteger(String word) {
        if (word.length() < 3 || word.charAt(0) != '0' || word.charAt(1) != 'b') {
            return false;
        }
        char ch;
        for (int i = 2; i < word.length(); i++) {
            ch = word.charAt(i);
            if (ch != '0' && ch != '1') {
                return false;
            }
        }
        return true;
    }

    private boolean isHexInteger(String word) {
        char ch;
        if (word.length() < 3 || word.charAt(0) != '0') {
            return false;
        }
        ch = word.charAt(1);
        if (ch != 'x' && ch != 'X') {
            return false;
        }
        for (int i = 2; i < word.length(); i++) {
            ch = word.charAt(i);
            if (!(ch >= 'A' && ch <= 'F' || ch >= 'a' && ch <= 'f' || Character.isDigit(ch))) {
                return false;
            }
        }
        return true;
    }

    private boolean isSimpleFloat(String word) {
        if (word.length() < 2) { // because must be at least 0. or .0
            return false;
        }
        boolean dotPresent = false;
        char ch;
        for (int i = 0; i < word.length(); i++) {
            ch = word.charAt(i);
            if (ch == '.') {
                if (dotPresent) {
                    return false;
                }
                dotPresent = true;
            } else if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return dotPresent;
    }

    private boolean isExpFloat(String word) {
        if (word.length() < 3) { // at least [digit]e[digit]
            return false;
        }
        int ePosition = word.indexOf('e');
        if (ePosition == -1) {
            ePosition = word.indexOf('E');
            if (ePosition == -1) {
                return false;
            }
        }
        String mantissa = word.substring(0, ePosition);
        if (!isInteger(mantissa) && !isSimpleFloat(mantissa)) {
            return false; //invalid mantissa
        }
        int powerPosition = ePosition + 1;
        char ch = word.charAt(powerPosition);
        if (ch == '+' || ch == '-') {
            powerPosition++;
        }
        if (powerPosition >= word.length()) { // e.g 3e+
            return false;
        }
        for (int i = powerPosition; i < word.length(); i++) {
            if (!Character.isDigit(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public Class<? extends Token> find(String word){
        word = word.toLowerCase();
        Class<? extends Token> token = patterns.get(word);
        if (token != null)
            return token;

        int length = word.length();
        if (length == 0) {
            return null;
        }

        if (isVarialbe(word)) {
            return VariableExprToken.class;
        }
        if (isOctal(word)) {
            return OctalExprValue.class;
        }
        if (isInteger(word)) {
            return IntegerExprToken.class;
        }
        if (isSimpleFloat(word) || isExpFloat(word)) {
            return DoubleExprToken.class;
        }
        if (isHexInteger(word)) {
            return HexExprValue.class;
        }
        if (isBinaryInteger(word)) {
            return BinaryExprValue.class;
        }

        boolean fulledName = false;
        for (int i = 0; i < length; i++) {
            char ch = word.charAt(i);
            if (ch == '\\') {
                fulledName = true;
            } else if (!GrammarUtils.isNameChar(ch)) {
                return null;
            }
        }
        if (fulledName) {
            return FulledNameToken.class;
        }
        return NameToken.class;
    }

    public Class<? extends Token> find(TokenMeta meta){
        return find(meta.getWord());
    }
}
