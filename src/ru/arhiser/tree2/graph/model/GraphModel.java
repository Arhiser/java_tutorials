package ru.arhiser.tree2.graph.model;

public class GraphModel {

    private static final int FIELD_WIDTH = 20;
    private static final int FIELD_HEIGHT = 10;

    GraphNode[][] nodes;

    GraphNode root;

    public GraphModel() {
        this.nodes = new GraphNode[FIELD_HEIGHT][FIELD_WIDTH];

        for (int i = 0; i < FIELD_HEIGHT; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                nodes[i][j] = new GraphNode();
            }
        }

        for (int i = 0; i < FIELD_HEIGHT; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if (i + k >= 0
                                && i + k < FIELD_HEIGHT
                                && j + l >= 0
                                && j + l < FIELD_WIDTH
                                && (k != 0 || l != 0)) {
                            nodes[i][j].neighbors.add(nodes[i + k][j + l]);
                        }
                    }
                }
            }
        }

        root = nodes[4][5];

        for (int i = 0; i < 3; i++) {
            int index = (int)Math.round(Math.random() * (FIELD_HEIGHT * FIELD_WIDTH));
            nodes[index / FIELD_WIDTH][index - (index / FIELD_WIDTH) * FIELD_WIDTH].hasObject = true;
        }

    }

    public GraphNode[][] getNodes() {
        return nodes;
    }

    public GraphNode getRoot() {
        return root;
    }

    public int getFieldWidth() {
        return FIELD_WIDTH;
    }

    public int getFieldHeight() {
        return FIELD_HEIGHT;
    }
}
