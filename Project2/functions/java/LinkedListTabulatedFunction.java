public class LinkedListTabulatedFunction implements TabulatedFunction {

    private int count;
    private Node head;

    private class Node {
        public double x;
        public double y;
        public Node next;
        public Node prev;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
            this.next = null;
            this.prev = null;
        }
    }

    private void addNode(double x, double y) {
        Node node = new Node(x, y);
        if (head == null) {
            head = node;
            head.next = head;
            head.prev = head;
        } else {
            Node last = head.prev;
            node.next = head;
            node.prev = last;
            last.next = node;
            head.prev = node;
        }
        count++;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        if (count < 2) {
            throw new IllegalArgumentException("count < 2");
        }
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            double x = xFrom + i * step;
            addNode(x, source.apply(x));
        }
    }

    private Node getNode(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        if (index < count / 2) {
            Node node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node node = head.prev;
            for (int i = count - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (getX(i) == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (getY(i) == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    public double apply(double x) {
        if (x < leftBound() || x > rightBound()) {
            throw new IllegalArgumentException("Argument out of range: " + x);
        }
        int i = floorIndexOfX(x);
        if (i == -1) {
            return getY(0);
        } else if (i < getCount() - 1) {
            double x1 = getX(i);
            double y1 = getY(i);
            double x2 = getX(i + 1);
            double y2 = getY(i + 1);
            return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
        } else {
            return getY(i);
        }
    }

    public double interpolate(double x, int floorIndex) {
        if (floorIndex < 0 || floorIndex >= getCount() - 1) {
            throw new IllegalArgumentException("Index out of range: " + floorIndex);
        }
        double x1 = getX(floorIndex);
        double y1 = getY(floorIndex);
        double x2 = getX(floorIndex + 1);
        double y2 = getY(floorIndex + 1);
        return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
    }

    public double extrapolateLeft(double x) {
        if (getCount() < 2) {
            return getY(0);
        }
        double x1 = getX(0);
        double y1 = getY(0);
        double x2 = getX(1);
        double y2 = getY(1);
        return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
    }

    public double extrapolateRight(double x) {
        if (getCount() < 2) {
            return getY(0);
        }
        double x1 = getX(getCount() - 2);
        double y1 = getY(getCount() - 2);
        double x2 = getX(getCount() - 1);
        double y2 = getY(getCount() - 1);
        return y2 + (y2 - y1) * (x - x2) / (x2 - x1);
    }

    public int floorIndexOfX(double x) {
        if (x < leftBound()) {
            return -1;
        }
        if (x > rightBound()) {
            return getCount() - 2;
        }
        int i = 0;
        while (getX(i) < x) {
            i++;
            if (i == getCount()) {
                return getCount() - 1;
            }
        }
        return i - 1;
    }
}