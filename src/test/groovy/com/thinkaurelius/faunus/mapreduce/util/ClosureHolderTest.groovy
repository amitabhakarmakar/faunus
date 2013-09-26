package com.thinkaurelius.faunus.mapreduce.util

import com.tinkerpop.blueprints.impls.tg.TinkerGraph
import junit.framework.TestCase

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
class ClosureHolderTest extends TestCase {

    public void testSerialization() {
        ClosureHolder c1 = new ClosureHolder({ it.getProperty("name").equals("marko") });
        ClosureHolder c2 = ClosureHolder.deserialize(c1.serialize());

        println c1.getClosure().getClass();
        println c2.getClosure().getClass();

        def g = new TinkerGraph()
        def v = g.addVertex(null);
        def u = g.addVertex(null);
        v.setProperty("name", "marko");
        u.setProperty("name", "stephen");


        assertTrue(c1.getClosure().call(v));
        assertTrue(c2.getClosure().call(v));

        assertFalse(c1.getClosure().call(u));
        assertFalse(c2.getClosure().call(u));
    }
}