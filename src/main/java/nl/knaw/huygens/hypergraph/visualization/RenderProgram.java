package nl.knaw.huygens.hypergraph.visualization;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class RenderProgram {

    public static void main(String[] args) {
        Graph g = graph("example1").directed().with(node("a").link(node("b")));
        BufferedImage bufferedImage = Graphviz.fromGraph(g).width(200).render(Format.PNG).toImage();
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(bufferedImage)));
        frame.pack();
        frame.setVisible(true);

    }
}
