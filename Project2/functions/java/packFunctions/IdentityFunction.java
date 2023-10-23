// Класс IdentityFunction реализует интерфейс MathFunction
package packFunctions;
public class IdentityFunction implements MathFunction, Cloneable {
    public double apply(double x) {
        return x;
    } //возвращается тот же элемент


    public String toString() {
        return ("IdentityFunction");
    }

    public boolean equals(Object obj) {
        return (obj.getClass() == this.getClass());
    }

    public int hashCode() {
        return super.hashCode();
    }

    public IdentityFunction clone() throws CloneNotSupportedException {
        return (IdentityFunction) super.clone();
    }
}
