/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class AIfExp extends PExp
{
    private PExp _cond_;
    private PExp _truthy_;
    private PExp _falsy_;

    public AIfExp()
    {
        // Constructor
    }

    public AIfExp(
        @SuppressWarnings("hiding") PExp _cond_,
        @SuppressWarnings("hiding") PExp _truthy_,
        @SuppressWarnings("hiding") PExp _falsy_)
    {
        // Constructor
        setCond(_cond_);

        setTruthy(_truthy_);

        setFalsy(_falsy_);

    }

    @Override
    public Object clone()
    {
        return new AIfExp(
            cloneNode(this._cond_),
            cloneNode(this._truthy_),
            cloneNode(this._falsy_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIfExp(this);
    }

    public PExp getCond()
    {
        return this._cond_;
    }

    public void setCond(PExp node)
    {
        if(this._cond_ != null)
        {
            this._cond_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._cond_ = node;
    }

    public PExp getTruthy()
    {
        return this._truthy_;
    }

    public void setTruthy(PExp node)
    {
        if(this._truthy_ != null)
        {
            this._truthy_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._truthy_ = node;
    }

    public PExp getFalsy()
    {
        return this._falsy_;
    }

    public void setFalsy(PExp node)
    {
        if(this._falsy_ != null)
        {
            this._falsy_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._falsy_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._cond_)
            + toString(this._truthy_)
            + toString(this._falsy_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._cond_ == child)
        {
            this._cond_ = null;
            return;
        }

        if(this._truthy_ == child)
        {
            this._truthy_ = null;
            return;
        }

        if(this._falsy_ == child)
        {
            this._falsy_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._cond_ == oldChild)
        {
            setCond((PExp) newChild);
            return;
        }

        if(this._truthy_ == oldChild)
        {
            setTruthy((PExp) newChild);
            return;
        }

        if(this._falsy_ == oldChild)
        {
            setFalsy((PExp) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}