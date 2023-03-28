/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class ALambdaExp extends PExp
{
    private PFuncLambda _funcLambda_;

    public ALambdaExp()
    {
        // Constructor
    }

    public ALambdaExp(
        @SuppressWarnings("hiding") PFuncLambda _funcLambda_)
    {
        // Constructor
        setFuncLambda(_funcLambda_);

    }

    @Override
    public Object clone()
    {
        return new ALambdaExp(
            cloneNode(this._funcLambda_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseALambdaExp(this);
    }

    public PFuncLambda getFuncLambda()
    {
        return this._funcLambda_;
    }

    public void setFuncLambda(PFuncLambda node)
    {
        if(this._funcLambda_ != null)
        {
            this._funcLambda_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._funcLambda_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._funcLambda_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._funcLambda_ == child)
        {
            this._funcLambda_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._funcLambda_ == oldChild)
        {
            setFuncLambda((PFuncLambda) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}