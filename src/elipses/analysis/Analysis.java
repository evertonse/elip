/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.analysis;

import elipses.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseAProgram(AProgram node);
    void caseADeclFunc(ADeclFunc node);
    void caseAIntType(AIntType node);
    void caseABoolType(ABoolType node);
    void caseARealType(ARealType node);
    void caseAParams(AParams node);
    void caseASingleParams(ASingleParams node);
    void caseAMultipleParams(AMultipleParams node);
    void caseATypeParam(ATypeParam node);
    void caseASignatureParam(ASignatureParam node);
    void caseASignature(ASignature node);
    void caseASignatureParams(ASignatureParams node);
    void caseASingleSignatureParams(ASingleSignatureParams node);
    void caseAMultipleSignatureParams(AMultipleSignatureParams node);
    void caseATypeSignatureParam(ATypeSignatureParam node);
    void caseASignatureSignatureParam(ASignatureSignatureParam node);
    void caseAOrExp(AOrExp node);
    void caseAAndExp(AAndExp node);
    void caseAEqExp(AEqExp node);
    void caseALtExp(ALtExp node);
    void caseAGtExp(AGtExp node);
    void caseAPlusExp(APlusExp node);
    void caseAMinusExp(AMinusExp node);
    void caseAMultExp(AMultExp node);
    void caseADivExp(ADivExp node);
    void caseAModExp(AModExp node);
    void caseANegativeExp(ANegativeExp node);
    void caseANotExp(ANotExp node);
    void caseAIfExp(AIfExp node);
    void caseAIdExp(AIdExp node);
    void caseABinExp(ABinExp node);
    void caseARealExp(ARealExp node);
    void caseAIntExp(AIntExp node);
    void caseATrueExp(ATrueExp node);
    void caseAFalseExp(AFalseExp node);
    void caseABlockExp(ABlockExp node);
    void caseACallExp(ACallExp node);
    void caseALambdaExp(ALambdaExp node);
    void caseABlockBlockExp(ABlockBlockExp node);
    void caseADeclConst(ADeclConst node);
    void caseAFuncCall(AFuncCall node);
    void caseAFuncLambda(AFuncLambda node);
    void caseAListIds(AListIds node);
    void caseASingleListIds(ASingleListIds node);
    void caseAMultipleListIds(AMultipleListIds node);
    void caseAListExp(AListExp node);
    void caseASingleListExp(ASingleListExp node);
    void caseAMultipleListExp(AMultipleListExp node);

    void caseTBlank(TBlank node);
    void caseTComment(TComment node);
    void caseTKwConst(TKwConst node);
    void caseTKwInteger(TKwInteger node);
    void caseTKwReal(TKwReal node);
    void caseTKwBool(TKwBool node);
    void caseTKwTrue(TKwTrue node);
    void caseTKwFalse(TKwFalse node);
    void caseTKwEntry(TKwEntry node);
    void caseTKwIf(TKwIf node);
    void caseTKwThen(TKwThen node);
    void caseTKwElse(TKwElse node);
    void caseTKwAnd(TKwAnd node);
    void caseTKwNot(TKwNot node);
    void caseTKwOr(TKwOr node);
    void caseTKwLambda(TKwLambda node);
    void caseTPlus(TPlus node);
    void caseTMinus(TMinus node);
    void caseTMult(TMult node);
    void caseTDiv(TDiv node);
    void caseTMod(TMod node);
    void caseTLParen(TLParen node);
    void caseTRParen(TRParen node);
    void caseTLBrack(TLBrack node);
    void caseTRBrack(TRBrack node);
    void caseTPipe(TPipe node);
    void caseTColon(TColon node);
    void caseTEq(TEq node);
    void caseTLt(TLt node);
    void caseTGt(TGt node);
    void caseTIdentifier(TIdentifier node);
    void caseTNumberInt(TNumberInt node);
    void caseTNumberFrac(TNumberFrac node);
    void caseTNumberBin(TNumberBin node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}
