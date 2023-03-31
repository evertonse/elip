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
    void caseAExp(AExp node);
    void caseAExpTernary(AExpTernary node);
    void caseAIfExpTernary(AIfExpTernary node);
    void caseAExpOr(AExpOr node);
    void caseAOrExpOr(AOrExpOr node);
    void caseAExpAnd(AExpAnd node);
    void caseAAndExpAnd(AAndExpAnd node);
    void caseAExpEquality(AExpEquality node);
    void caseAEqExpEquality(AEqExpEquality node);
    void caseAExpRelational(AExpRelational node);
    void caseALtExpRelational(ALtExpRelational node);
    void caseAGtExpRelational(AGtExpRelational node);
    void caseAExpAdditive(AExpAdditive node);
    void caseAPlusExpAdditive(APlusExpAdditive node);
    void caseAMinusExpAdditive(AMinusExpAdditive node);
    void caseAExpMultiplicative(AExpMultiplicative node);
    void caseAMultExpMultiplicative(AMultExpMultiplicative node);
    void caseADivExpMultiplicative(ADivExpMultiplicative node);
    void caseAModExpMultiplicative(AModExpMultiplicative node);
    void caseANegativeExpMultiplicative(ANegativeExpMultiplicative node);
    void caseANotExpMultiplicative(ANotExpMultiplicative node);
    void caseAIdExpAtomic(AIdExpAtomic node);
    void caseABinExpAtomic(ABinExpAtomic node);
    void caseAFracExpAtomic(AFracExpAtomic node);
    void caseAIntExpAtomic(AIntExpAtomic node);
    void caseATrueExpAtomic(ATrueExpAtomic node);
    void caseAFalseExpAtomic(AFalseExpAtomic node);
    void caseABlockExpAtomic(ABlockExpAtomic node);
    void caseACallExpAtomic(ACallExpAtomic node);
    void caseALambdaExpAtomic(ALambdaExpAtomic node);
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
