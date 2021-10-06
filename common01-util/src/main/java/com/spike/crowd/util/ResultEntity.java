package com.spike.crowd.util;

/**
 * 用于统一项目中所有 Ajax 请求的返回值类型
 * @param <T>
 */
public class ResultEntity<T> {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    public static final String NO_MSG = "NO_MSG";
    public static final String NO_DATA = "NI_DATA";

    private String opResult;
    private String opMsg;
    private T opData;

    public ResultEntity() {
    }

    public ResultEntity(String opResult, String opMsg, T opData) {
        this.opResult = opResult;
        this.opMsg = opMsg;
        this.opData = opData;
    }

    /**
     * 返回操作结果为成功，不携带数据
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> successWithoutData() {
        return new ResultEntity<>(SUCCESS, NO_MSG, null);
    }

    /**
     * 返回操作结果为成功，携带数据
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> successWithData(E data) {
        return new ResultEntity<>(SUCCESS, NO_MSG, data);
    }

    /**
     * 返回操作结果为失败，不携带数据
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> FailedWithoutData(String msg) {
        return new ResultEntity<>(FAILED, msg, null);
    }

    public String getOpResult() {
        return opResult;
    }

    public void setOpResult(String opResult) {
        this.opResult = opResult;
    }

    public String getOpMsg() {
        return opMsg;
    }

    public void setOpMsg(String opMsg) {
        this.opMsg = opMsg;
    }

    public T getOpData() {
        return opData;
    }

    public void setOpData(T opData) {
        this.opData = opData;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "opResult='" + opResult + '\'' +
                ", opMsg='" + opMsg + '\'' +
                ", opData=" + opData +
                '}';
    }
}
