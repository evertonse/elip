/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class AIntType extends PType
{
    private TKwInteger _kwInteger_;

    public AIntType()
    {
        // Constructor
    }

    public AIntType(
        @SuppressWarnings("hiding") TKwInteger _kwInteger_)
    {
        // Constructor
        setKwInteger(_kwInteger_);

    }

    @Override
    public Object clone()
    {
        return new AIntType(
            cloneNode(this._kwInteger_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIntType(this);
    }

    public TKwInteger getKwInteger()
    {
        return this._kwInteger_;
    }

    public void setKwInteger(TKwInteger node)
    {
        if(this._kwInteger_ != null)
        {
            this._kwInteger_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._kwInteger_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._kwInteger_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._kwInteger_ == child)
        {
            this._kwInteger_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._kwInteger_ == oldChild)
        {
            setKwInteger((TKwInteger) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
