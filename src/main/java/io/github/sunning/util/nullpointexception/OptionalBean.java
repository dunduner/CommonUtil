package io.github.sunning.util.nullpointexception;

import lombok.Data;

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


    public static void main(String[] args) {
        User axin = new User();
        axin.setName("hello");
        User.School school = new User.School();
        school.setAdress("上海市");
        axin.setSchool(school);
        // 1. 基本调用
        String value1 = OptionalBean.ofNullable(axin)
                .getBean(User::getSchool)
                .getBean(User.School::getAdress).get();
        System.out.println("1. 基本调用:"+value1);

        // 2. 扩展的 isPresent方法 用法与 Optional 一样
        boolean present = OptionalBean.ofNullable(axin)
                .getBean(User::getSchool)
                .getBean(User.School::getAdress).isPresent();
        System.out.println("地址是否为空："+present);

        boolean present2 = OptionalBean.ofNullable(axin).isPresent();
        boolean present1 = OptionalBean.ofNullable(axin).getBean(User::getSchool).isPresent();
        boolean present3 = OptionalBean.ofNullable(axin).getBean(User::getSchool).getBean(User.School::getAdress).isPresent();
        boolean present4 = OptionalBean.ofNullable(axin).getBean(User::getSchool).getBean(User.School::getScName).isPresent();

        // 3. 扩展的 ifPresent 方法
        OptionalBean.ofNullable(axin)
                .getBean(User::getSchool)
                .getBean(User.School::getAdress)
                .ifPresent(val -> System.out.println(String.format("地址存在:%s", val)));
        System.out.println("===================");
        // 4. 扩展的 orElse
        //如果目标值为空 获取一个默认值
        String value2 = OptionalBean.ofNullable(axin)
                .getBean(User::getSchool)
                .getBean(User.School::getAdress)
                .orElse("家里蹲");
        System.out.println(value2);

        // 5. 扩展的 orElseThrow
        try {
            String value3 = OptionalBean.ofNullable(axin)
                    .getBean(User::getSchool)
                    .getBean(User.School::getAdress).orElseThrow(() -> new RuntimeException("空指针了-我是message的内容"));
        } catch (Exception e) {
            System.out.println("ExceptionMessage->"+e.getMessage());
        }
    }

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
     * @param <T>
     * @return
     */
    public static <T> OptionalBean<T> of(T value) {

        return new OptionalBean<>(value);

    }

    /**
     * 包装一个可能为空的 bean
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> OptionalBean<T> ofNullable(T value) {

        return value == null ? empty() : of(value);

    }

    /**
     * 取出具体的值
     * @return
     */
    public T get() {
        return Objects.isNull(value) ? null : value;

    }

    /**
     * 取出一个可能为空的对象
     *
     * @param fn
     * @param <R>
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
     * @param <X>
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
     * @param <T>
     * @return
     */

    public static <T> OptionalBean<T>

    empty() {

        @SuppressWarnings("unchecked")

        OptionalBean none = (OptionalBean) EMPTY;

        return none;

    }


}
@Data
class User {
    private String name;
    private String gender;
    private School school;
    @Data
    public static class School {
        private String scName;
        private String adress;
    }

}

