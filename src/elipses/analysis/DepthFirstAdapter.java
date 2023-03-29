/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.analysis;

import java.util.*;
import elipses.node.*;

public class DepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getPProgram().apply(this);
        node.getEOF().apply(this);
        outStart(node);
    }

    public void inAProgram(AProgram node)
    {
        defaultIn(node);
    }

    public void outAProgram(AProgram node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAProgram(AProgram node)
    {
        inAProgram(node);
        {
            List<PDeclFunc> copy = new ArrayList<PDeclFunc>(node.getDeclFunc());
            for(PDeclFunc e : copy)
            {
                e.apply(this);
            }
        }
        outAProgram(node);
    }

    public void inADeclFunc(ADeclFunc node)
    {
        defaultIn(node);
    }

    public void outADeclFunc(ADeclFunc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADeclFunc(ADeclFunc node)
    {
        inADeclFunc(node);
        if(node.getKwEntry() != null)
        {
            node.getKwEntry().apply(this);
        }
        if(node.getLp1() != null)
        {
            node.getLp1().apply(this);
        }
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        if(node.getLp2() != null)
        {
            node.getLp2().apply(this);
        }
        if(node.getParams() != null)
        {
            node.getParams().apply(this);
        }
        if(node.getRp2() != null)
        {
            node.getRp2().apply(this);
        }
        if(node.getColon() != null)
        {
            node.getColon().apply(this);
        }
        if(node.getLp3() != null)
        {
            node.getLp3().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getRp3() != null)
        {
            node.getRp3().apply(this);
        }
        if(node.getRp1() != null)
        {
            node.getRp1().apply(this);
        }
        outADeclFunc(node);
    }

    public void inAIntType(AIntType node)
    {
        defaultIn(node);
    }

    public void outAIntType(AIntType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIntType(AIntType node)
    {
        inAIntType(node);
        if(node.getKwInterger() != null)
        {
            node.getKwInterger().apply(this);
        }
        outAIntType(node);
    }

    public void inABoolType(ABoolType node)
    {
        defaultIn(node);
    }

    public void outABoolType(ABoolType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABoolType(ABoolType node)
    {
        inABoolType(node);
        if(node.getKwBool() != null)
        {
            node.getKwBool().apply(this);
        }
        outABoolType(node);
    }

    public void inARealType(ARealType node)
    {
        defaultIn(node);
    }

    public void outARealType(ARealType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARealType(ARealType node)
    {
        inARealType(node);
        if(node.getKwReal() != null)
        {
            node.getKwReal().apply(this);
        }
        outARealType(node);
    }

    public void inAParams(AParams node)
    {
        defaultIn(node);
    }

    public void outAParams(AParams node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParams(AParams node)
    {
        inAParams(node);
        outAParams(node);
    }

    public void inASingleParams(ASingleParams node)
    {
        defaultIn(node);
    }

    public void outASingleParams(ASingleParams node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleParams(ASingleParams node)
    {
        inASingleParams(node);
        if(node.getParam() != null)
        {
            node.getParam().apply(this);
        }
        outASingleParams(node);
    }

    public void inAMultipleParams(AMultipleParams node)
    {
        defaultIn(node);
    }

    public void outAMultipleParams(AMultipleParams node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultipleParams(AMultipleParams node)
    {
        inAMultipleParams(node);
        if(node.getParam() != null)
        {
            node.getParam().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getParams() != null)
        {
            node.getParams().apply(this);
        }
        outAMultipleParams(node);
    }

    public void inATypeParam(ATypeParam node)
    {
        defaultIn(node);
    }

    public void outATypeParam(ATypeParam node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATypeParam(ATypeParam node)
    {
        inATypeParam(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outATypeParam(node);
    }

    public void inASignatureParam(ASignatureParam node)
    {
        defaultIn(node);
    }

    public void outASignatureParam(ASignatureParam node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASignatureParam(ASignatureParam node)
    {
        inASignatureParam(node);
        if(node.getSignature() != null)
        {
            node.getSignature().apply(this);
        }
        outASignatureParam(node);
    }

    public void inASignature(ASignature node)
    {
        defaultIn(node);
    }

    public void outASignature(ASignature node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASignature(ASignature node)
    {
        inASignature(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        if(node.getLParen() != null)
        {
            node.getLParen().apply(this);
        }
        if(node.getSignatureParams() != null)
        {
            node.getSignatureParams().apply(this);
        }
        if(node.getRParen() != null)
        {
            node.getRParen().apply(this);
        }
        outASignature(node);
    }

    public void inASignatureParams(ASignatureParams node)
    {
        defaultIn(node);
    }

    public void outASignatureParams(ASignatureParams node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASignatureParams(ASignatureParams node)
    {
        inASignatureParams(node);
        outASignatureParams(node);
    }

    public void inASingleSignatureParams(ASingleSignatureParams node)
    {
        defaultIn(node);
    }

    public void outASingleSignatureParams(ASingleSignatureParams node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleSignatureParams(ASingleSignatureParams node)
    {
        inASingleSignatureParams(node);
        if(node.getSignatureParam() != null)
        {
            node.getSignatureParam().apply(this);
        }
        outASingleSignatureParams(node);
    }

    public void inAMultipleSignatureParams(AMultipleSignatureParams node)
    {
        defaultIn(node);
    }

    public void outAMultipleSignatureParams(AMultipleSignatureParams node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultipleSignatureParams(AMultipleSignatureParams node)
    {
        inAMultipleSignatureParams(node);
        if(node.getSignatureParam() != null)
        {
            node.getSignatureParam().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getSignatureParams() != null)
        {
            node.getSignatureParams().apply(this);
        }
        outAMultipleSignatureParams(node);
    }

    public void inATypeSignatureParam(ATypeSignatureParam node)
    {
        defaultIn(node);
    }

    public void outATypeSignatureParam(ATypeSignatureParam node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATypeSignatureParam(ATypeSignatureParam node)
    {
        inATypeSignatureParam(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        outATypeSignatureParam(node);
    }

    public void inASignatureSignatureParam(ASignatureSignatureParam node)
    {
        defaultIn(node);
    }

    public void outASignatureSignatureParam(ASignatureSignatureParam node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASignatureSignatureParam(ASignatureSignatureParam node)
    {
        inASignatureSignatureParam(node);
        if(node.getSignature() != null)
        {
            node.getSignature().apply(this);
        }
        outASignatureSignatureParam(node);
    }

    public void inAExp(AExp node)
    {
        defaultIn(node);
    }

    public void outAExp(AExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp(AExp node)
    {
        inAExp(node);
        if(node.getExp6() != null)
        {
            node.getExp6().apply(this);
        }
        outAExp(node);
    }

    public void inAIfExp(AIfExp node)
    {
        defaultIn(node);
    }

    public void outAIfExp(AIfExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfExp(AIfExp node)
    {
        inAIfExp(node);
        if(node.getKwIf() != null)
        {
            node.getKwIf().apply(this);
        }
        if(node.getLParen() != null)
        {
            node.getLParen().apply(this);
        }
        if(node.getTest() != null)
        {
            node.getTest().apply(this);
        }
        if(node.getRParen() != null)
        {
            node.getRParen().apply(this);
        }
        if(node.getKwThen() != null)
        {
            node.getKwThen().apply(this);
        }
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        if(node.getKwElse() != null)
        {
            node.getKwElse().apply(this);
        }
        if(node.getElse() != null)
        {
            node.getElse().apply(this);
        }
        outAIfExp(node);
    }

    public void inAExp6(AExp6 node)
    {
        defaultIn(node);
    }

    public void outAExp6(AExp6 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp6(AExp6 node)
    {
        inAExp6(node);
        if(node.getExp5() != null)
        {
            node.getExp5().apply(this);
        }
        outAExp6(node);
    }

    public void inAOrExp6(AOrExp6 node)
    {
        defaultIn(node);
    }

    public void outAOrExp6(AOrExp6 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOrExp6(AOrExp6 node)
    {
        inAOrExp6(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getKwOr() != null)
        {
            node.getKwOr().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAOrExp6(node);
    }

    public void inAExp5(AExp5 node)
    {
        defaultIn(node);
    }

    public void outAExp5(AExp5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp5(AExp5 node)
    {
        inAExp5(node);
        if(node.getExp4() != null)
        {
            node.getExp4().apply(this);
        }
        outAExp5(node);
    }

    public void inAAndExp5(AAndExp5 node)
    {
        defaultIn(node);
    }

    public void outAAndExp5(AAndExp5 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAndExp5(AAndExp5 node)
    {
        inAAndExp5(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getKwAnd() != null)
        {
            node.getKwAnd().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAAndExp5(node);
    }

    public void inAExp4(AExp4 node)
    {
        defaultIn(node);
    }

    public void outAExp4(AExp4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp4(AExp4 node)
    {
        inAExp4(node);
        if(node.getExp3() != null)
        {
            node.getExp3().apply(this);
        }
        outAExp4(node);
    }

    public void inAEqExp4(AEqExp4 node)
    {
        defaultIn(node);
    }

    public void outAEqExp4(AEqExp4 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEqExp4(AEqExp4 node)
    {
        inAEqExp4(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAEqExp4(node);
    }

    public void inAExp3(AExp3 node)
    {
        defaultIn(node);
    }

    public void outAExp3(AExp3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp3(AExp3 node)
    {
        inAExp3(node);
        if(node.getExp2() != null)
        {
            node.getExp2().apply(this);
        }
        outAExp3(node);
    }

    public void inALtExp3(ALtExp3 node)
    {
        defaultIn(node);
    }

    public void outALtExp3(ALtExp3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALtExp3(ALtExp3 node)
    {
        inALtExp3(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getLt() != null)
        {
            node.getLt().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outALtExp3(node);
    }

    public void inAGtExp3(AGtExp3 node)
    {
        defaultIn(node);
    }

    public void outAGtExp3(AGtExp3 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGtExp3(AGtExp3 node)
    {
        inAGtExp3(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getGt() != null)
        {
            node.getGt().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAGtExp3(node);
    }

    public void inAExp2(AExp2 node)
    {
        defaultIn(node);
    }

    public void outAExp2(AExp2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp2(AExp2 node)
    {
        inAExp2(node);
        if(node.getExp1() != null)
        {
            node.getExp1().apply(this);
        }
        outAExp2(node);
    }

    public void inAPlusExp2(APlusExp2 node)
    {
        defaultIn(node);
    }

    public void outAPlusExp2(APlusExp2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPlusExp2(APlusExp2 node)
    {
        inAPlusExp2(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAPlusExp2(node);
    }

    public void inAMinusExp2(AMinusExp2 node)
    {
        defaultIn(node);
    }

    public void outAMinusExp2(AMinusExp2 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMinusExp2(AMinusExp2 node)
    {
        inAMinusExp2(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAMinusExp2(node);
    }

    public void inAExp1(AExp1 node)
    {
        defaultIn(node);
    }

    public void outAExp1(AExp1 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAExp1(AExp1 node)
    {
        inAExp1(node);
        if(node.getExp0() != null)
        {
            node.getExp0().apply(this);
        }
        outAExp1(node);
    }

    public void inAMultExp1(AMultExp1 node)
    {
        defaultIn(node);
    }

    public void outAMultExp1(AMultExp1 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultExp1(AMultExp1 node)
    {
        inAMultExp1(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getMult() != null)
        {
            node.getMult().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAMultExp1(node);
    }

    public void inADivExp1(ADivExp1 node)
    {
        defaultIn(node);
    }

    public void outADivExp1(ADivExp1 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADivExp1(ADivExp1 node)
    {
        inADivExp1(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outADivExp1(node);
    }

    public void inAModExp1(AModExp1 node)
    {
        defaultIn(node);
    }

    public void outAModExp1(AModExp1 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAModExp1(AModExp1 node)
    {
        inAModExp1(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getMod() != null)
        {
            node.getMod().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAModExp1(node);
    }

    public void inANegativeExp1(ANegativeExp1 node)
    {
        defaultIn(node);
    }

    public void outANegativeExp1(ANegativeExp1 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANegativeExp1(ANegativeExp1 node)
    {
        inANegativeExp1(node);
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getExp0() != null)
        {
            node.getExp0().apply(this);
        }
        outANegativeExp1(node);
    }

    public void inANotExp1(ANotExp1 node)
    {
        defaultIn(node);
    }

    public void outANotExp1(ANotExp1 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANotExp1(ANotExp1 node)
    {
        inANotExp1(node);
        if(node.getKwNot() != null)
        {
            node.getKwNot().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outANotExp1(node);
    }

    public void inAIdExp0(AIdExp0 node)
    {
        defaultIn(node);
    }

    public void outAIdExp0(AIdExp0 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIdExp0(AIdExp0 node)
    {
        inAIdExp0(node);
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAIdExp0(node);
    }

    public void inABinExp0(ABinExp0 node)
    {
        defaultIn(node);
    }

    public void outABinExp0(ABinExp0 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABinExp0(ABinExp0 node)
    {
        inABinExp0(node);
        if(node.getNumberBin() != null)
        {
            node.getNumberBin().apply(this);
        }
        outABinExp0(node);
    }

    public void inAFracExp0(AFracExp0 node)
    {
        defaultIn(node);
    }

    public void outAFracExp0(AFracExp0 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFracExp0(AFracExp0 node)
    {
        inAFracExp0(node);
        if(node.getNumberFrac() != null)
        {
            node.getNumberFrac().apply(this);
        }
        outAFracExp0(node);
    }

    public void inAIntExp0(AIntExp0 node)
    {
        defaultIn(node);
    }

    public void outAIntExp0(AIntExp0 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIntExp0(AIntExp0 node)
    {
        inAIntExp0(node);
        if(node.getNumberInt() != null)
        {
            node.getNumberInt().apply(this);
        }
        outAIntExp0(node);
    }

    public void inATrueExp0(ATrueExp0 node)
    {
        defaultIn(node);
    }

    public void outATrueExp0(ATrueExp0 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATrueExp0(ATrueExp0 node)
    {
        inATrueExp0(node);
        if(node.getKwTrue() != null)
        {
            node.getKwTrue().apply(this);
        }
        outATrueExp0(node);
    }

    public void inAFalseExp0(AFalseExp0 node)
    {
        defaultIn(node);
    }

    public void outAFalseExp0(AFalseExp0 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFalseExp0(AFalseExp0 node)
    {
        inAFalseExp0(node);
        if(node.getKwFalse() != null)
        {
            node.getKwFalse().apply(this);
        }
        outAFalseExp0(node);
    }

    public void inABlockExp0(ABlockExp0 node)
    {
        defaultIn(node);
    }

    public void outABlockExp0(ABlockExp0 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABlockExp0(ABlockExp0 node)
    {
        inABlockExp0(node);
        if(node.getBlockExp() != null)
        {
            node.getBlockExp().apply(this);
        }
        outABlockExp0(node);
    }

    public void inACallExp0(ACallExp0 node)
    {
        defaultIn(node);
    }

    public void outACallExp0(ACallExp0 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACallExp0(ACallExp0 node)
    {
        inACallExp0(node);
        if(node.getFuncCall() != null)
        {
            node.getFuncCall().apply(this);
        }
        outACallExp0(node);
    }

    public void inALambdaExp0(ALambdaExp0 node)
    {
        defaultIn(node);
    }

    public void outALambdaExp0(ALambdaExp0 node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALambdaExp0(ALambdaExp0 node)
    {
        inALambdaExp0(node);
        if(node.getFuncLambda() != null)
        {
            node.getFuncLambda().apply(this);
        }
        outALambdaExp0(node);
    }

    public void inABlockBlockExp(ABlockBlockExp node)
    {
        defaultIn(node);
    }

    public void outABlockBlockExp(ABlockBlockExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABlockBlockExp(ABlockBlockExp node)
    {
        inABlockBlockExp(node);
        if(node.getLParen() != null)
        {
            node.getLParen().apply(this);
        }
        {
            List<PDeclConst> copy = new ArrayList<PDeclConst>(node.getDeclConst());
            for(PDeclConst e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getExp1() != null)
        {
            node.getExp1().apply(this);
        }
        if(node.getRParen() != null)
        {
            node.getRParen().apply(this);
        }
        outABlockBlockExp(node);
    }

    public void inADeclConst(ADeclConst node)
    {
        defaultIn(node);
    }

    public void outADeclConst(ADeclConst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADeclConst(ADeclConst node)
    {
        inADeclConst(node);
        if(node.getLp1() != null)
        {
            node.getLp1().apply(this);
        }
        if(node.getKwConst() != null)
        {
            node.getKwConst().apply(this);
        }
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        if(node.getLp2() != null)
        {
            node.getLp2().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getRp2() != null)
        {
            node.getRp2().apply(this);
        }
        if(node.getRp1() != null)
        {
            node.getRp1().apply(this);
        }
        outADeclConst(node);
    }

    public void inAFuncCall(AFuncCall node)
    {
        defaultIn(node);
    }

    public void outAFuncCall(AFuncCall node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFuncCall(AFuncCall node)
    {
        inAFuncCall(node);
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        if(node.getLParen() != null)
        {
            node.getLParen().apply(this);
        }
        if(node.getListExp() != null)
        {
            node.getListExp().apply(this);
        }
        if(node.getRParen() != null)
        {
            node.getRParen().apply(this);
        }
        outAFuncCall(node);
    }

    public void inAFuncLambda(AFuncLambda node)
    {
        defaultIn(node);
    }

    public void outAFuncLambda(AFuncLambda node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFuncLambda(AFuncLambda node)
    {
        inAFuncLambda(node);
        if(node.getLp1() != null)
        {
            node.getLp1().apply(this);
        }
        if(node.getKwLambda() != null)
        {
            node.getKwLambda().apply(this);
        }
        if(node.getLp2() != null)
        {
            node.getLp2().apply(this);
        }
        if(node.getListIds() != null)
        {
            node.getListIds().apply(this);
        }
        if(node.getRp2() != null)
        {
            node.getRp2().apply(this);
        }
        if(node.getColon() != null)
        {
            node.getColon().apply(this);
        }
        if(node.getLp3() != null)
        {
            node.getLp3().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getRp3() != null)
        {
            node.getRp3().apply(this);
        }
        if(node.getLBrack() != null)
        {
            node.getLBrack().apply(this);
        }
        if(node.getListExp() != null)
        {
            node.getListExp().apply(this);
        }
        if(node.getRBrack() != null)
        {
            node.getRBrack().apply(this);
        }
        if(node.getRp1() != null)
        {
            node.getRp1().apply(this);
        }
        outAFuncLambda(node);
    }

    public void inAListIds(AListIds node)
    {
        defaultIn(node);
    }

    public void outAListIds(AListIds node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListIds(AListIds node)
    {
        inAListIds(node);
        outAListIds(node);
    }

    public void inASingleListIds(ASingleListIds node)
    {
        defaultIn(node);
    }

    public void outASingleListIds(ASingleListIds node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleListIds(ASingleListIds node)
    {
        inASingleListIds(node);
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outASingleListIds(node);
    }

    public void inAMultipleListIds(AMultipleListIds node)
    {
        defaultIn(node);
    }

    public void outAMultipleListIds(AMultipleListIds node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultipleListIds(AMultipleListIds node)
    {
        inAMultipleListIds(node);
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getListIds() != null)
        {
            node.getListIds().apply(this);
        }
        outAMultipleListIds(node);
    }

    public void inAListExp(AListExp node)
    {
        defaultIn(node);
    }

    public void outAListExp(AListExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListExp(AListExp node)
    {
        inAListExp(node);
        outAListExp(node);
    }

    public void inASingleListExp(ASingleListExp node)
    {
        defaultIn(node);
    }

    public void outASingleListExp(ASingleListExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASingleListExp(ASingleListExp node)
    {
        inASingleListExp(node);
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        outASingleListExp(node);
    }

    public void inAMultipleListExp(AMultipleListExp node)
    {
        defaultIn(node);
    }

    public void outAMultipleListExp(AMultipleListExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultipleListExp(AMultipleListExp node)
    {
        inAMultipleListExp(node);
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getListExp() != null)
        {
            node.getListExp().apply(this);
        }
        outAMultipleListExp(node);
    }
}
