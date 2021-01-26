package io.github.sunning.util.npe;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author ning11.zhang
 * @Description:
 * @date 2021/1/26
 */
public final class OptionalBean<T> {

    private static final OptionalBean<?> EMPTY = new OptionalBean<>();

    private final T value;

    private OptionalBean() {

        this.value = null;

    }

    /**
     * 空值会抛出空指针
     *
     * @param value
     */

    private OptionalBean(T value) {

        this.value = Objects.requireNonNull(value);

    }

    /**
     * 包装一个不能为空的 bean
     *
     * @param value
     * @param &lt;T&gt;
     * @return
     */

    public static <T> OptionalBean<T> of(T value) {

        return new OptionalBean<>(value);

    }

    /**
     * 包装一个可能为空的 bean
     *
     * @param value
     * @param &lt;T&gt;
     * @return
     */

    public static <T> OptionalBean<T> ofNullable(T value) {

        return value == null ? empty() : of(value);

    }

    /**
     * 取出具体的值
     *
     * @param &lt;R&gt;
     * @return
     */

    public T get() {

        return Objects.isNull(value) ? null : value;

    }

    /**
     * 取出一个可能为空的对象
     *
     * @param fn
     * @param &lt;R&gt;
     * @return
     */

    public <R> OptionalBean<R> getBean(Function<? super T, ? extends R> fn) {

        return Objects.isNull(value) ? OptionalBean.empty() : OptionalBean.ofNullable(fn.apply(value));

    }

    /**
     * 如果目标值为空 获取一个默认值
     *
     * @param other
     * @return
     */

    public T orElse(T other) {

        return value != null ? value : other;

    }

    /**
     * 如果目标值为空 通过lambda表达式获取一个值
     *
     * @param other
     * @return
     */

    public T orElseGet(Supplier<? extends T> other) {

        return value != null ? value : other.get();

    }

    /**
     * 如果目标值为空 抛出一个异常
     *
     * @param exceptionSupplier
     * @param &lt;X&gt;
     * @return
     * @throws X
     */

    public <X extends Throwable>

    T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {

        if (value != null) {

            return value;

        } else {

            throw exceptionSupplier.get();

        }

    }

    public boolean isPresent() {

        return value != null;

    }

    public void ifPresent(Consumer<? super T> consumer) {

        if (value != null)

            consumer.accept(value);

    }

    @Override

    public int hashCode() {

        return Objects.hashCode(value);

    }

    /**
     * 空值常量
     *
     * @param &lt;T&gt;
     * @return
     */

    public static <T> OptionalBean<T>

    empty() {

        @SuppressWarnings("unchecked")

        OptionalBean none = (OptionalBean) EMPTY;

        return none;

    }


}
