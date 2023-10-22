// Класс IdentityFunction реализует интерфейс MathFunction
package packFunctions;
public class IdentityFunction implements MathFunction, Cloneable {
    public double apply(double x) {
        return x;
    } //возвращается тот же элемент


    @Override
    public String toString() {
        return ("IdentityFunction");
    }

    @Override
    public boolean equals(Object obj) {
        return (obj.getClass() == this.getClass());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public IdentityFunction clone() throws CloneNotSupportedException {
        return (IdentityFunction) super.clone();
    }
}
