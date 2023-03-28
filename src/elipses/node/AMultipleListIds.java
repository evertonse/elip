/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class AMultipleListIds extends PListIds
{
    private TIdentifier _identifier_;
    private TPipe _pipe_;
    private PListIds _listIds_;

    public AMultipleListIds()
    {
        // Constructor
    }

    public AMultipleListIds(
        @SuppressWarnings("hiding") TIdentifier _identifier_,
        @SuppressWarnings("hiding") TPipe _pipe_,
        @SuppressWarnings("hiding") PListIds _listIds_)
    {
        // Constructor
        setIdentifier(_identifier_);

        setPipe(_pipe_);

        setListIds(_listIds_);

    }

    @Override
    public Object clone()
    {
        return new AMultipleListIds(
            cloneNode(this._identifier_),
            cloneNode(this._pipe_),
            cloneNode(this._listIds_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMultipleListIds(this);
    }

    public TIdentifier getIdentifier()
    {
        return this._identifier_;
    }

    public void setIdentifier(TIdentifier node)
    {
        if(this._identifier_ != null)
        {
            this._identifier_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._identifier_ = node;
    }

    public TPipe getPipe()
    {
        return this._pipe_;
    }

    public void setPipe(TPipe node)
    {
        if(this._pipe_ != null)
        {
            this._pipe_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._pipe_ = node;
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._identifier_)
            + toString(this._pipe_)
            + toString(this._listIds_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._identifier_ == child)
        {
            this._identifier_ = null;
            return;
        }

        if(this._pipe_ == child)
        {
            this._pipe_ = null;
            return;
        }

        if(this._listIds_ == child)
        {
            this._listIds_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._identifier_ == oldChild)
        {
            setIdentifier((TIdentifier) newChild);
            return;
        }

        if(this._pipe_ == oldChild)
        {
            setPipe((TPipe) newChild);
            return;
        }

        if(this._listIds_ == oldChild)
        {
            setListIds((PListIds) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}