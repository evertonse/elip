/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import java.util.*;
import elipses.analysis.*;

@SuppressWarnings("nls")
public final class ACallExp extends PExp
{
    private TIdentifier _id_;
    private final LinkedList<PExp> _args_ = new LinkedList<PExp>();

    public ACallExp()
    {
        // Constructor
    }

    public ACallExp(
        @SuppressWarnings("hiding") TIdentifier _id_,
        @SuppressWarnings("hiding") List<?> _args_)
    {
        // Constructor
        setId(_id_);

        setArgs(_args_);

    }

    @Override
    public Object clone()
    {
        return new ACallExp(
            cloneNode(this._id_),
            cloneList(this._args_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACallExp(this);
    }

    public TIdentifier getId()
    {
        return this._id_;
    }

    public void setId(TIdentifier node)
    {
        if(this._id_ != null)
        {
            this._id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._id_ = node;
    }

    public LinkedList<PExp> getArgs()
    {
        return this._args_;
    }

    public void setArgs(List<?> list)
    {
        for(PExp e : this._args_)
        {
            e.parent(null);
        }
        this._args_.clear();

        for(Object obj_e : list)
        {
            PExp e = (PExp) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._args_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._id_)
            + toString(this._args_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._args_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._id_ == oldChild)
        {
            setId((TIdentifier) newChild);
            return;
        }

        for(ListIterator<PExp> i = this._args_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PExp) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        throw new RuntimeException("Not a child.");
    }
}