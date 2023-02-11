package com.github.georgiadisc.net.widgets;

import com.github.georgiadisc.net.utility.Widget;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public final class GraphView extends Widget {

    private final Layout<String, Integer> layout;
    private final BasicVisualizationServer<String, Integer> renderer;

    /**
     * A widget showing the suspects' network graph.
     */
    public GraphView() {
        this.layout = new CircleLayout<>(db.getRegistry().getGraph());
        this.renderer = new BasicVisualizationServer<>(layout);
    }

    @Override
    public void build() {
        setUpdateFrequency(Frequency.NEVER);
        renderer.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        renderer.getRenderer().getVertexLabelRenderer().setPosition(Position.S);
        add(renderer);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException();
    }

}
