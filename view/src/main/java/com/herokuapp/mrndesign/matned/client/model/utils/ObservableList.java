package com.herokuapp.mrndesign.matned.client.model.utils;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.List;

public class ObservableList<T> {

    protected final List<T> list;
    protected final PublishSubject<T> onAdd;

    public ObservableList(List<T> list) {
        this.list = new ArrayList<T>();
        this.onAdd = PublishSubject.create();
    }

    public void add(T value) {
        list.add(value);
        onAdd.onNext(value);
    }

    public Observable<T> getObservable() {
        return onAdd;
    }

    public List<T> getList() {
        return list;
    }
}
