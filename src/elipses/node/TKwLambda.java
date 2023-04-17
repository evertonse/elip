/* This file was generated by SableCC (http://www.sablecc.org/). */

package elipses.node;

import elipses.analysis.*;

@SuppressWarnings("nls")
public final class TKwLambda extends Token
{
    public TKwLambda()
    {
        super.setText("lambda");
    }

    public TKwLambda(int line, int pos)
    {
        super.setText("lambda");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TKwLambda(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTKwLambda(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TKwLambda text.");
    }
}
