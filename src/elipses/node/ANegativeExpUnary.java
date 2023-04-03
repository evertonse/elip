/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class ANegativeExpUnary extends PExpUnary
{
    private TMinus _minus_;
    private PExpUnary _expUnary_;

    public ANegativeExpUnary()
    {
        // Constructor
    }

    public ANegativeExpUnary(
        @SuppressWarnings("hiding") TMinus _minus_,
        @SuppressWarnings("hiding") PExpUnary _expUnary_)
    {
        // Constructor
        setMinus(_minus_);

        setExpUnary(_expUnary_);

    }

    @Override
    public Object clone()
    {
        return new ANegativeExpUnary(
            cloneNode(this._minus_),
            cloneNode(this._expUnary_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANegativeExpUnary(this);
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

    public PExpUnary getExpUnary()
    {
        return this._expUnary_;
    }

    public void setExpUnary(PExpUnary node)
    {
        if(this._expUnary_ != null)
        {
            this._expUnary_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expUnary_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._minus_)
            + toString(this._expUnary_);
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

        if(this._expUnary_ == child)
        {
            this._expUnary_ = null;
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

        if(this._expUnary_ == oldChild)
        {
            setExpUnary((PExpUnary) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}