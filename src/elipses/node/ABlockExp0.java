/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class ABlockExp0 extends PExp0
{
    private PBlockExp _blockExp_;

    public ABlockExp0()
    {
        // Constructor
    }

    public ABlockExp0(
        @SuppressWarnings("hiding") PBlockExp _blockExp_)
    {
        // Constructor
        setBlockExp(_blockExp_);

    }

    @Override
    public Object clone()
    {
        return new ABlockExp0(
            cloneNode(this._blockExp_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseABlockExp0(this);
    }

    public PBlockExp getBlockExp()
    {
        return this._blockExp_;
    }

    public void setBlockExp(PBlockExp node)
    {
        if(this._blockExp_ != null)
        {
            this._blockExp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._blockExp_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._blockExp_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._blockExp_ == child)
        {
            this._blockExp_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._blockExp_ == oldChild)
        {
            setBlockExp((PBlockExp) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
