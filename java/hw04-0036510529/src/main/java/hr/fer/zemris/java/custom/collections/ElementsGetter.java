package hr.fer.zemris.java.custom.collections;

public interface ElementsGetter<E> {

	public boolean hasNextElement();

	public E getNextElement();

	default void processRemaining(Processor<E> p) {
		while (hasNextElement()) {
			p.process(getNextElement());
		}
	}
}
