package com.sebbaindustries.dynamicshop.engine.ui.components;

import com.sebbaindustries.dynamicshop.utils.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DisplayItem {
    private String display = "%item%";
    private List<String> lore;
    private int slot;

    public List<String> getColoredLore() {
        if (lore == null) return null;
        return lore.stream().map(Color::format).collect(Collectors.toList());
    }

}
