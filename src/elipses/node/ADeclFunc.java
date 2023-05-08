/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import java.util.*;
import elipses.analysis.*;

@SuppressWarnings("nls")
public final class ADeclFunc extends PDeclFunc
{
    private TKwEntry _kwEntry_;
    private PType _type_;
    private TIdentifier _identifier_;
    private final LinkedList<PParam> _param_ = new LinkedList<PParam>();
    private PExp _exp_;

    public ADeclFunc()
    {
        // Constructor
    }

    public ADeclFunc(
        @SuppressWarnings("hiding") TKwEntry _kwEntry_,
        @SuppressWarnings("hiding") PType _type_,
        @SuppressWarnings("hiding") TIdentifier _identifier_,
        @SuppressWarnings("hiding") List<?> _param_,
        @SuppressWarnings("hiding") PExp _exp_)
    {
        // Constructor
        setKwEntry(_kwEntry_);

        setType(_type_);

        setIdentifier(_identifier_);

        setParam(_param_);

        setExp(_exp_);

    }

    @Override
    public Object clone()
    {
        return new ADeclFunc(
            cloneNode(this._kwEntry_),
            cloneNode(this._type_),
            cloneNode(this._identifier_),
            cloneList(this._param_),
            cloneNode(this._exp_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADeclFunc(this);
    }

    public TKwEntry getKwEntry()
    {
        return this._kwEntry_;
    }

    public void setKwEntry(TKwEntry node)
    {
        if(this._kwEntry_ != null)
        {
            this._kwEntry_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._kwEntry_ = node;
    }

    public PType getType()
    {
        return this._type_;
    }

    public void setType(PType node)
    {
        if(this._type_ != null)
        {
            this._type_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._type_ = node;
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

    public LinkedList<PParam> getParam()
    {
        return this._param_;
    }

    public void setParam(List<?> list)
    {
        for(PParam e : this._param_)
        {
            e.parent(null);
        }
        this._param_.clear();

        for(Object obj_e : list)
        {
            PParam e = (PParam) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._param_.add(e);
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._kwEntry_)
            + toString(this._type_)
            + toString(this._identifier_)
            + toString(this._param_)
            + toString(this._exp_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._kwEntry_ == child)
        {
            this._kwEntry_ = null;
            return;
        }

        if(this._type_ == child)
        {
            this._type_ = null;
            return;
        }

        if(this._identifier_ == child)
        {
            this._identifier_ = null;
            return;
        }

        if(this._param_.remove(child))
        {
            return;
        }

        if(this._exp_ == child)
        {
            this._exp_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._kwEntry_ == oldChild)
        {
            setKwEntry((TKwEntry) newChild);
            return;
        }

        if(this._type_ == oldChild)
        {
            setType((PType) newChild);
            return;
        }

        if(this._identifier_ == oldChild)
        {
            setIdentifier((TIdentifier) newChild);
            return;
        }

        for(ListIterator<PParam> i = this._param_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PParam) newChild);
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

        throw new RuntimeException("Not a child.");
    }
}
