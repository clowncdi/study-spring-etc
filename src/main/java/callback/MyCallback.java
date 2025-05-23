package callback;

public interface MyCallback<T> {

    void onReceived(T t);
    void onError(Throwable error);
    void onComplete();
}
