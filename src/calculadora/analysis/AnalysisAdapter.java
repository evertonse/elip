/* This file was generated by SableCC (http://www.sablecc.org/). */

package calculadora.analysis;

import java.util.*;
import calculadora.node.*;

public class AnalysisAdapter implements Analysis
{
    private Hashtable<Node,Object> in;
    private Hashtable<Node,Object> out;

    @Override
    public Object getIn(Node node)
    {
        if(this.in == null)
        {
            return null;
        }

        return this.in.get(node);
    }

    @Override
    public void setIn(Node node, Object o)
    {
        if(this.in == null)
        {
            this.in = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.in.put(node, o);
        }
        else
        {
            this.in.remove(node);
        }
    }

    @Override
    public Object getOut(Node node)
    {
        if(this.out == null)
        {
            return null;
        }

        return this.out.get(node);
    }

    @Override
    public void setOut(Node node, Object o)
    {
        if(this.out == null)
        {
            this.out = new Hashtable<Node,Object>(1);
        }

        if(o != null)
        {
            this.out.put(node, o);
        }
        else
        {
            this.out.remove(node);
        }
    }

    @Override
    public void caseStart(Start node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAExp(AExp node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAFatorExpr(AFatorExpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseASomaExpr(ASomaExpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMenosExpr(AMenosExpr node)
    {
        defaultCase(node);
    }

    @Override
    public void caseATermoFator(ATermoFator node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAMultFator(AMultFator node)
    {
        defaultCase(node);
    }

    @Override
    public void caseADivFator(ADivFator node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAModFator(AModFator node)
    {
        defaultCase(node);
    }

    @Override
    public void caseANumeroTermo(ANumeroTermo node)
    {
        defaultCase(node);
    }

    @Override
    public void caseAParTermo(AParTermo node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTNumero(TNumero node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTSoma(TSoma node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMenos(TMenos node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMult(TMult node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTDiv(TDiv node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTMod(TMod node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTParEsq(TParEsq node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTParDir(TParDir node)
    {
        defaultCase(node);
    }

    @Override
    public void caseTVazio(TVazio node)
    {
        defaultCase(node);
    }

    @Override
    public void caseEOF(EOF node)
    {
        defaultCase(node);
    }

    @Override
    public void caseInvalidToken(InvalidToken node)
    {
        defaultCase(node);
    }

    public void defaultCase(@SuppressWarnings("unused") Node node)
    {
        // do nothing
    }
}
