package gouri.ibk.interactivetrader.model;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This is an immutable data transfer object to wrap the result of the call from controllers to business layer.
 * The class wraps a result type parameter which is the expected result type.
 * If the result is success, client calls getData to fetch result.
 * If operation results in error, Errors are sent as a Key, value pair.
 * Key => property that resulted in validation error or some runtime error;
 * value => Actual Error description.
 * Clients can ignore the keys and just focus on the result description.
 *
 * @param <T> Result Type expected by client.
 */
public final class WebOpsResult<T> {

    /**
     * Success of Failure
     */
    private final boolean success;

    /**
     * Result of the operation if successful
     */
    private final Optional<T> data;

    /**
     * errors if the ops resulted in error.
     * If operation results in error, Errors are sent as a Key, value pair.
     * Key => property that resulted in validation error or some runtime error;
     * value => Actual Error description.
     * Clients can ignore the keys and just focus on the result description.
     */
    private final Map<String, String> errors;

    /**
     * Hidden constructor.
     * The Result is built from static param context
     *
     * @see @{@link #successOf(Object)}
     * @see @{@link #failureOf(Map)}
     */
    private WebOpsResult(boolean _success, T _data, Map<String, String> _errors) {
        this.success = _success;
        this.data = _data == null ? Optional.empty() : Optional.of(_data);
        this.errors = _errors;
    }

    /**
     * Hidden constructor.
     * The Result is built from static param context
     *
     * @see @{@link #successOf(Object)}
     * @see @{@link #failureOf(Map)}
     */
    private WebOpsResult(boolean _success, Optional<T> _data, Map<String, String> _errors) {
        this.success = _success;
        this.data = _data;
        this.errors = _errors;
    }

    /**
     * check is ops is successful
     * @return result of operation
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Wrapped errors in operation
     * @return errorMap
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * actual result of the operation
     * @return result data parameterized by client
     */
    public Optional<T> getData() {
        return data;
    }

    /**
     * static method to build an success result
     * @param resultObj actual result output
     * @param <R> Type parameter of the result
     * @return @{@link WebOpsResult} with wrapped result
     */
    public static <R> WebOpsResult<R> successOf(R resultObj) {
        return new WebOpsResult<R>(true, resultObj, null);
    }

    /**
     * static method to build an success result
     * @param resultObj actual result output
     * @param <R> Type parameter of the result
     * @return @{@link WebOpsResult} with wrapped result
     */
    public static <R> WebOpsResult<R> successOf(Optional<R> resultObj) {
        return new WebOpsResult<R>(true, resultObj, null);
    }


    /**
     * static method that builds a failure result
     * @param errorMap errors in the ops.
     * @param <R> Type of the result expected by client
     * @return @{@link WebOpsResult} with wrapped error
     */
    public static <R> WebOpsResult<R> failureOf(@NotNull Map<String, String> errorMap) {
        R r = null;
        return new WebOpsResult<>(false, r, errorMap);
    }

    /**
     * static method that builds a failure result for single failure Message
     * @param errorKey errorKey
     * @param errorDesc meaningful Desc or error.
     * @param <R> Type of the result expected by client
     * @return @{@link WebOpsResult} with wrapped error
     */
    public static <R> WebOpsResult<R> failureOf(@NotNull String errorKey, String errorDesc) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(errorKey, errorDesc);
        R r = null;
        return new WebOpsResult<>(false, r, errorMap);
    }
}
