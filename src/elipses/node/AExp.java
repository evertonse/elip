/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class AExp extends PExp
{
    private PExpOr _expOr_;

    public AExp()
    {
        // Constructor
    }

    public AExp(
        @SuppressWarnings("hiding") PExpOr _expOr_)
    {
        // Constructor
        setExpOr(_expOr_);

    }

    @Override
    public Object clone()
    {
        return new AExp(
            cloneNode(this._expOr_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAExp(this);
    }

    public PExpOr getExpOr()
    {
        return this._expOr_;
    }

    public void setExpOr(PExpOr node)
    {
        if(this._expOr_ != null)
        {
            this._expOr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expOr_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._expOr_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._expOr_ == child)
        {
            this._expOr_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._expOr_ == oldChild)
        {
            setExpOr((PExpOr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
