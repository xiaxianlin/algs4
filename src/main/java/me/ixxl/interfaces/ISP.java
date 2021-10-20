package me.ixxl.interfaces;

import me.ixxl.sp.DirectedEdge;

public interface ISP {
    public double distTo(int v);

    public boolean hasPathTo(int v);

    public Iterable<DirectedEdge> pathTo(int v);
}
