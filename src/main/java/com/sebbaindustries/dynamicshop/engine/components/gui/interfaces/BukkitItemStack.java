package com.sebbaindustries.dynamicshop.engine.components.gui.interfaces;

import org.bukkit.Material;

import java.util.List;

public interface BukkitItemStack {

    Material material();
    String display();
    List<String> lore();

}
