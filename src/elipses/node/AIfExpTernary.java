/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class AIfExpTernary extends PExpTernary
{
    private TKwIf _kwIf_;
    private TLParen _lParen_;
    private PExp _cond_;
    private TRParen _rParen_;
    private TKwThen _kwThen_;
    private PExp _truthy_;
    private TKwElse _kwElse_;
    private PExp _falsy_;

    public AIfExpTernary()
    {
        // Constructor
    }

    public AIfExpTernary(
        @SuppressWarnings("hiding") TKwIf _kwIf_,
        @SuppressWarnings("hiding") TLParen _lParen_,
        @SuppressWarnings("hiding") PExp _cond_,
        @SuppressWarnings("hiding") TRParen _rParen_,
        @SuppressWarnings("hiding") TKwThen _kwThen_,
        @SuppressWarnings("hiding") PExp _truthy_,
        @SuppressWarnings("hiding") TKwElse _kwElse_,
        @SuppressWarnings("hiding") PExp _falsy_)
    {
        // Constructor
        setKwIf(_kwIf_);

        setLParen(_lParen_);

        setCond(_cond_);

        setRParen(_rParen_);

        setKwThen(_kwThen_);

        setTruthy(_truthy_);

        setKwElse(_kwElse_);

        setFalsy(_falsy_);

    }

    @Override
    public Object clone()
    {
        return new AIfExpTernary(
            cloneNode(this._kwIf_),
            cloneNode(this._lParen_),
            cloneNode(this._cond_),
            cloneNode(this._rParen_),
            cloneNode(this._kwThen_),
            cloneNode(this._truthy_),
            cloneNode(this._kwElse_),
            cloneNode(this._falsy_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIfExpTernary(this);
    }

    public TKwIf getKwIf()
    {
        return this._kwIf_;
    }

    public void setKwIf(TKwIf node)
    {
        if(this._kwIf_ != null)
        {
            this._kwIf_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._kwIf_ = node;
    }

    public TLParen getLParen()
    {
        return this._lParen_;
    }

    public void setLParen(TLParen node)
    {
        if(this._lParen_ != null)
        {
            this._lParen_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lParen_ = node;
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

    public TRParen getRParen()
    {
        return this._rParen_;
    }

    public void setRParen(TRParen node)
    {
        if(this._rParen_ != null)
        {
            this._rParen_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rParen_ = node;
    }

    public TKwThen getKwThen()
    {
        return this._kwThen_;
    }

    public void setKwThen(TKwThen node)
    {
        if(this._kwThen_ != null)
        {
            this._kwThen_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._kwThen_ = node;
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

    public TKwElse getKwElse()
    {
        return this._kwElse_;
    }

    public void setKwElse(TKwElse node)
    {
        if(this._kwElse_ != null)
        {
            this._kwElse_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._kwElse_ = node;
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
            + toString(this._kwIf_)
            + toString(this._lParen_)
            + toString(this._cond_)
            + toString(this._rParen_)
            + toString(this._kwThen_)
            + toString(this._truthy_)
            + toString(this._kwElse_)
            + toString(this._falsy_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._kwIf_ == child)
        {
            this._kwIf_ = null;
            return;
        }

        if(this._lParen_ == child)
        {
            this._lParen_ = null;
            return;
        }

        if(this._cond_ == child)
        {
            this._cond_ = null;
            return;
        }

        if(this._rParen_ == child)
        {
            this._rParen_ = null;
            return;
        }

        if(this._kwThen_ == child)
        {
            this._kwThen_ = null;
            return;
        }

        if(this._truthy_ == child)
        {
            this._truthy_ = null;
            return;
        }

        if(this._kwElse_ == child)
        {
            this._kwElse_ = null;
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
        if(this._kwIf_ == oldChild)
        {
            setKwIf((TKwIf) newChild);
            return;
        }

        if(this._lParen_ == oldChild)
        {
            setLParen((TLParen) newChild);
            return;
        }

        if(this._cond_ == oldChild)
        {
            setCond((PExp) newChild);
            return;
        }

        if(this._rParen_ == oldChild)
        {
            setRParen((TRParen) newChild);
            return;
        }

        if(this._kwThen_ == oldChild)
        {
            setKwThen((TKwThen) newChild);
            return;
        }

        if(this._truthy_ == oldChild)
        {
            setTruthy((PExp) newChild);
            return;
        }

        if(this._kwElse_ == oldChild)
        {
            setKwElse((TKwElse) newChild);
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
