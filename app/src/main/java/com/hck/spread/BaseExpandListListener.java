package com.hck.spread;

public abstract class BaseExpandListListener<T> implements IExpandListListener<T> {
    @Override
    public boolean isHiddenFunctionView() {
        return false;
    }
}
