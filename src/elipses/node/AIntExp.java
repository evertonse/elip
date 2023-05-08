/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class AIntExp extends PExp
{
    private TNumberInt _numberInt_;

    public AIntExp()
    {
        // Constructor
    }

    public AIntExp(
        @SuppressWarnings("hiding") TNumberInt _numberInt_)
    {
        // Constructor
        setNumberInt(_numberInt_);

    }

    @Override
    public Object clone()
    {
        return new AIntExp(
            cloneNode(this._numberInt_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIntExp(this);
    }

    public TNumberInt getNumberInt()
    {
        return this._numberInt_;
    }

    public void setNumberInt(TNumberInt node)
    {
        if(this._numberInt_ != null)
        {
            this._numberInt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._numberInt_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._numberInt_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._numberInt_ == child)
        {
            this._numberInt_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._numberInt_ == oldChild)
        {
            setNumberInt((TNumberInt) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
