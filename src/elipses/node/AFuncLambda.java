/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class AFuncLambda extends PFuncLambda
{
    private TLParen _lp1_;
    private TKwLambda _kwLambda_;
    private TLParen _lp2_;
    private PListIds _listIds_;
    private TRParen _rp2_;
    private TColon _colon_;
    private TLParen _lp3_;
    private PExp _exp_;
    private TRParen _rp3_;
    private TLBrack _lBrack_;
    private PListExp _listExp_;
    private TRBrack _rBrack_;
    private TRParen _rp1_;

    public AFuncLambda()
    {
        // Constructor
    }

    public AFuncLambda(
        @SuppressWarnings("hiding") TLParen _lp1_,
        @SuppressWarnings("hiding") TKwLambda _kwLambda_,
        @SuppressWarnings("hiding") TLParen _lp2_,
        @SuppressWarnings("hiding") PListIds _listIds_,
        @SuppressWarnings("hiding") TRParen _rp2_,
        @SuppressWarnings("hiding") TColon _colon_,
        @SuppressWarnings("hiding") TLParen _lp3_,
        @SuppressWarnings("hiding") PExp _exp_,
        @SuppressWarnings("hiding") TRParen _rp3_,
        @SuppressWarnings("hiding") TLBrack _lBrack_,
        @SuppressWarnings("hiding") PListExp _listExp_,
        @SuppressWarnings("hiding") TRBrack _rBrack_,
        @SuppressWarnings("hiding") TRParen _rp1_)
    {
        // Constructor
        setLp1(_lp1_);

        setKwLambda(_kwLambda_);

        setLp2(_lp2_);

        setListIds(_listIds_);

        setRp2(_rp2_);

        setColon(_colon_);

        setLp3(_lp3_);

        setExp(_exp_);

        setRp3(_rp3_);

        setLBrack(_lBrack_);

        setListExp(_listExp_);

        setRBrack(_rBrack_);

        setRp1(_rp1_);

    }

    @Override
    public Object clone()
    {
        return new AFuncLambda(
            cloneNode(this._lp1_),
            cloneNode(this._kwLambda_),
            cloneNode(this._lp2_),
            cloneNode(this._listIds_),
            cloneNode(this._rp2_),
            cloneNode(this._colon_),
            cloneNode(this._lp3_),
            cloneNode(this._exp_),
            cloneNode(this._rp3_),
            cloneNode(this._lBrack_),
            cloneNode(this._listExp_),
            cloneNode(this._rBrack_),
            cloneNode(this._rp1_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFuncLambda(this);
    }

    public TLParen getLp1()
    {
        return this._lp1_;
    }

    public void setLp1(TLParen node)
    {
        if(this._lp1_ != null)
        {
            this._lp1_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lp1_ = node;
    }

    public TKwLambda getKwLambda()
    {
        return this._kwLambda_;
    }

    public void setKwLambda(TKwLambda node)
    {
        if(this._kwLambda_ != null)
        {
            this._kwLambda_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._kwLambda_ = node;
    }

    public TLParen getLp2()
    {
        return this._lp2_;
    }

    public void setLp2(TLParen node)
    {
        if(this._lp2_ != null)
        {
            this._lp2_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lp2_ = node;
    }

    public PListIds getListIds()
    {
        return this._listIds_;
    }

    public void setListIds(PListIds node)
    {
        if(this._listIds_ != null)
        {
            this._listIds_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listIds_ = node;
    }

    public TRParen getRp2()
    {
        return this._rp2_;
    }

    public void setRp2(TRParen node)
    {
        if(this._rp2_ != null)
        {
            this._rp2_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rp2_ = node;
    }

    public TColon getColon()
    {
        return this._colon_;
    }

    public void setColon(TColon node)
    {
        if(this._colon_ != null)
        {
            this._colon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._colon_ = node;
    }

    public TLParen getLp3()
    {
        return this._lp3_;
    }

    public void setLp3(TLParen node)
    {
        if(this._lp3_ != null)
        {
            this._lp3_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lp3_ = node;
    }

    public PExp getExp()
    {
        return this._exp_;
    }

    public void setExp(PExp node)
    {
        if(this._exp_ != null)
        {
            this._exp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exp_ = node;
    }

    public TRParen getRp3()
    {
        return this._rp3_;
    }

    public void setRp3(TRParen node)
    {
        if(this._rp3_ != null)
        {
            this._rp3_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rp3_ = node;
    }

    public TLBrack getLBrack()
    {
        return this._lBrack_;
    }

    public void setLBrack(TLBrack node)
    {
        if(this._lBrack_ != null)
        {
            this._lBrack_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lBrack_ = node;
    }

    public PListExp getListExp()
    {
        return this._listExp_;
    }

    public void setListExp(PListExp node)
    {
        if(this._listExp_ != null)
        {
            this._listExp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listExp_ = node;
    }

    public TRBrack getRBrack()
    {
        return this._rBrack_;
    }

    public void setRBrack(TRBrack node)
    {
        if(this._rBrack_ != null)
        {
            this._rBrack_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rBrack_ = node;
    }

    public TRParen getRp1()
    {
        return this._rp1_;
    }

    public void setRp1(TRParen node)
    {
        if(this._rp1_ != null)
        {
            this._rp1_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rp1_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._lp1_)
            + toString(this._kwLambda_)
            + toString(this._lp2_)
            + toString(this._listIds_)
            + toString(this._rp2_)
            + toString(this._colon_)
            + toString(this._lp3_)
            + toString(this._exp_)
            + toString(this._rp3_)
            + toString(this._lBrack_)
            + toString(this._listExp_)
            + toString(this._rBrack_)
            + toString(this._rp1_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._lp1_ == child)
        {
            this._lp1_ = null;
            return;
        }

        if(this._kwLambda_ == child)
        {
            this._kwLambda_ = null;
            return;
        }

        if(this._lp2_ == child)
        {
            this._lp2_ = null;
            return;
        }

        if(this._listIds_ == child)
        {
            this._listIds_ = null;
            return;
        }

        if(this._rp2_ == child)
        {
            this._rp2_ = null;
            return;
        }

        if(this._colon_ == child)
        {
            this._colon_ = null;
            return;
        }

        if(this._lp3_ == child)
        {
            this._lp3_ = null;
            return;
        }

        if(this._exp_ == child)
        {
            this._exp_ = null;
            return;
        }

        if(this._rp3_ == child)
        {
            this._rp3_ = null;
            return;
        }

        if(this._lBrack_ == child)
        {
            this._lBrack_ = null;
            return;
        }

        if(this._listExp_ == child)
        {
            this._listExp_ = null;
            return;
        }

        if(this._rBrack_ == child)
        {
            this._rBrack_ = null;
            return;
        }

        if(this._rp1_ == child)
        {
            this._rp1_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._lp1_ == oldChild)
        {
            setLp1((TLParen) newChild);
            return;
        }

        if(this._kwLambda_ == oldChild)
        {
            setKwLambda((TKwLambda) newChild);
            return;
        }

        if(this._lp2_ == oldChild)
        {
            setLp2((TLParen) newChild);
            return;
        }

        if(this._listIds_ == oldChild)
        {
            setListIds((PListIds) newChild);
            return;
        }

        if(this._rp2_ == oldChild)
        {
            setRp2((TRParen) newChild);
            return;
        }

        if(this._colon_ == oldChild)
        {
            setColon((TColon) newChild);
            return;
        }

        if(this._lp3_ == oldChild)
        {
            setLp3((TLParen) newChild);
            return;
        }

        if(this._exp_ == oldChild)
        {
            setExp((PExp) newChild);
            return;
        }

        if(this._rp3_ == oldChild)
        {
            setRp3((TRParen) newChild);
            return;
        }

        if(this._lBrack_ == oldChild)
        {
            setLBrack((TLBrack) newChild);
            return;
        }

        if(this._listExp_ == oldChild)
        {
            setListExp((PListExp) newChild);
            return;
        }

        if(this._rBrack_ == oldChild)
        {
            setRBrack((TRBrack) newChild);
            return;
        }

        if(this._rp1_ == oldChild)
        {
            setRp1((TRParen) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}