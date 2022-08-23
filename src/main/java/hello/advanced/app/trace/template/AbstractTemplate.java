package hello.advanced.app.trace.template;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.logtrace.LogTrace;

import java.util.concurrent.ExecutionException;

public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public T execute(String message){
        TraceStatus status = null;
        try{
            status = trace.begin(message);

            //로직 호출
            T result = call();

            trace.end(status);
            return result;
        } catch(Exception e){
            trace.exception(status, e);
            throw e;//예외를 꼭 다시 던져주어야 한다.

        }
    }

    protected abstract T call();
}
