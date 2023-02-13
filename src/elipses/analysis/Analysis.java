/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.analysis;

import elipses.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseTBlank(TBlank node);
    void caseTComment(TComment node);
    void caseTKwInterger(TKwInterger node);
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
    void caseTNumber(TNumber node);
    void caseTFracNumber(TFracNumber node);
    void caseTBinNumber(TBinNumber node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}
