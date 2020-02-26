package ru.arhiser.tree3;

import ru.arhiser.funcops.inter.Func2;

import java.util.ArrayList;

public class Main {

    public static void main(String[] params) {
        ArrayList<Data> items = new ArrayList<>();

        items.add(new Data(1, "Соки", 0));
        items.add(new Data(2, "Манго", 1));
        items.add(new Data(3, "Виноградный", 1));
        items.add(new Data(4, "Яблочный", 1));
        items.add(new Data(5, "Газировка", 0));
        items.add(new Data(6, "Кола", 5));
        items.add(new Data(7, "Кола 0.5л", 6));
        items.add(new Data(8, "Кола 1.5л", 6));
        items.add(new Data(9, "Минералка", 5));
        items.add(new Data(10, "Лимонад", 5));

        DataNode tree = DataNode.makeTree(items, new TreeNode.TypeAdapter<Data, DataNode>() {
            @Override
            public DataNode newInstance() {
                return new DataNode();
            }

            @Override
            public boolean isChildOf(Data parentNodeData, Data childNodeData) {
                return parentNodeData.id == childNodeData.parentId;
            }

            @Override
            public boolean isTopLevelItem(Data data) {
                return data.parentId == 0;
            }
        });

        String names = tree.reduce((node, names1) -> {
            if (node.children == null || node.children.isEmpty()) {
                if (names1.length() > 0) {
                    names1 = names1 + ", ";
                }
                names1 = names1 + node.data.name;
            }
            return names1;
        }, "");

        System.out.println("Имена товаров: " + names);
    }

    static class DataNode extends TreeNode<Data, DataNode> {
        @Override
        public String toString() {
            return data.name;
        }
    }


    public static class Data {
        int id;
        String name;
        int parentId;

        public Data(int id, String name, int parentId) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
