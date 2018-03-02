package com.carejava.core.build.model;

import java.util.List;

public class Store {
    private String id;

    private Model model;

    private Function function;

    private Model secondModel;

    private Function secondFunction;

    // private List<Panel> panels;

    private List<ModelAttribute> inputs;

    private List<ModelAttribute> outputs;

    private List<FilterAttribute> filters;

    public Store() {
        super();
    }

    public Store(String id, Model model, Function function) {
        super();
        this.id = id;
        this.model = model;
        this.function = function;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public List<ModelAttribute> getInputs() {
        return inputs;
    }

    public void setInputs(List<ModelAttribute> inputs) {
        this.inputs = inputs;
    }

    public List<ModelAttribute> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<ModelAttribute> outputs) {
        this.outputs = outputs;
    }

    public List<FilterAttribute> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterAttribute> filters) {
        this.filters = filters;
    }

    public Model getSecondModel() {
        return secondModel;
    }

    public void setSecondModel(Model secondModel) {
        this.secondModel = secondModel;
    }

    public Function getSecondFunction() {
        return secondFunction;
    }

    public void setSecondFunction(Function secondFunction) {
        this.secondFunction = secondFunction;
    }
}
