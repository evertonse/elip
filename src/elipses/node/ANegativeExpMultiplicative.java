/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class ANegativeExpMultiplicative extends PExpMultiplicative
{
    private TMinus _minus_;
    private PExpAtomic _expAtomic_;

    public ANegativeExpMultiplicative()
    {
        // Constructor
    }

    public ANegativeExpMultiplicative(
        @SuppressWarnings("hiding") TMinus _minus_,
        @SuppressWarnings("hiding") PExpAtomic _expAtomic_)
    {
        // Constructor
        setMinus(_minus_);

        setExpAtomic(_expAtomic_);

    }

    @Override
    public Object clone()
    {
        return new ANegativeExpMultiplicative(
            cloneNode(this._minus_),
            cloneNode(this._expAtomic_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANegativeExpMultiplicative(this);
    }

    public TMinus getMinus()
    {
        return this._minus_;
    }

    public void setMinus(TMinus node)
    {
        if(this._minus_ != null)
        {
            this._minus_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._minus_ = node;
    }

    public PExpAtomic getExpAtomic()
    {
        return this._expAtomic_;
    }

    public void setExpAtomic(PExpAtomic node)
    {
        if(this._expAtomic_ != null)
        {
            this._expAtomic_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expAtomic_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._minus_)
            + toString(this._expAtomic_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._minus_ == child)
        {
            this._minus_ = null;
            return;
        }

        if(this._expAtomic_ == child)
        {
            this._expAtomic_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._minus_ == oldChild)
        {
            setMinus((TMinus) newChild);
            return;
        }

        if(this._expAtomic_ == oldChild)
        {
            setExpAtomic((PExpAtomic) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}