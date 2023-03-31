/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import java.util.*;
import elipses.analysis.*;

@SuppressWarnings("nls")
public final class ABlockBlockExp extends PBlockExp
{
    private TLParen _lParen_;
    private final LinkedList<PDeclConst> _declConst_ = new LinkedList<PDeclConst>();
    private PExp _exp_;
    private TRParen _rParen_;

    public ABlockBlockExp()
    {
        // Constructor
    }

    public ABlockBlockExp(
        @SuppressWarnings("hiding") TLParen _lParen_,
        @SuppressWarnings("hiding") List<?> _declConst_,
        @SuppressWarnings("hiding") PExp _exp_,
        @SuppressWarnings("hiding") TRParen _rParen_)
    {
        // Constructor
        setLParen(_lParen_);

        setDeclConst(_declConst_);

        setExp(_exp_);

        setRParen(_rParen_);

    }

    @Override
    public Object clone()
    {
        return new ABlockBlockExp(
            cloneNode(this._lParen_),
            cloneList(this._declConst_),
            cloneNode(this._exp_),
            cloneNode(this._rParen_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseABlockBlockExp(this);
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

    public LinkedList<PDeclConst> getDeclConst()
    {
        return this._declConst_;
    }

    public void setDeclConst(List<?> list)
    {
        for(PDeclConst e : this._declConst_)
        {
            e.parent(null);
        }
        this._declConst_.clear();

        for(Object obj_e : list)
        {
            PDeclConst e = (PDeclConst) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._declConst_.add(e);
        }
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._lParen_)
            + toString(this._declConst_)
            + toString(this._exp_)
            + toString(this._rParen_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._lParen_ == child)
        {
            this._lParen_ = null;
            return;
        }

        if(this._declConst_.remove(child))
        {
            return;
        }

        if(this._exp_ == child)
        {
            this._exp_ = null;
            return;
        }

        if(this._rParen_ == child)
        {
            this._rParen_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._lParen_ == oldChild)
        {
            setLParen((TLParen) newChild);
            return;
        }

        for(ListIterator<PDeclConst> i = this._declConst_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PDeclConst) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        if(this._exp_ == oldChild)
        {
            setExp((PExp) newChild);
            return;
        }

        if(this._rParen_ == oldChild)
        {
            setRParen((TRParen) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
