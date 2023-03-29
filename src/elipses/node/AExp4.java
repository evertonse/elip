/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class AExp4 extends PExp4
{
    private PExp3 _exp3_;

    public AExp4()
    {
        // Constructor
    }

    public AExp4(
        @SuppressWarnings("hiding") PExp3 _exp3_)
    {
        // Constructor
        setExp3(_exp3_);

    }

    @Override
    public Object clone()
    {
        return new AExp4(
            cloneNode(this._exp3_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAExp4(this);
    }

    public PExp3 getExp3()
    {
        return this._exp3_;
    }

    public void setExp3(PExp3 node)
    {
        if(this._exp3_ != null)
        {
            this._exp3_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exp3_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._exp3_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._exp3_ == child)
        {
            this._exp3_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._exp3_ == oldChild)
        {
            setExp3((PExp3) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
