package com.sebbaindustries.dynamicshop.engine.ui.cache;

import com.sebbaindustries.dynamicshop.engine.ui.components.DisplayItem;
import com.sebbaindustries.dynamicshop.engine.ui.components.UIBackground;
import com.sebbaindustries.dynamicshop.engine.ui.components.UIButton;
import com.sebbaindustries.dynamicshop.engine.ui.interfaces.BaseUI;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public class SellPageUICache implements BaseUI {

    private String name = "$NULL";

    private int size = 6;

    @Getter
    @Setter
    private DisplayItem item;

    private UIBackground background;

    private List<UIButton> button = new ArrayList<>();

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public UIBackground background() {
        return this.background;
    }

    @Override
    public List<UIButton> buttons() {
        return this.button;
    }
}
