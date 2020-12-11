package com.sebbaindustries.dynamicshop.engine.components.gui.guis;

import com.sebbaindustries.dynamicshop.Core;
import com.sebbaindustries.dynamicshop.engine.components.gui.components.UIAction;
import com.sebbaindustries.dynamicshop.engine.components.gui.components.UIMetaData;
import com.sebbaindustries.dynamicshop.engine.components.gui.components.UserInterface;
import com.sebbaindustries.dynamicshop.engine.components.gui.components.UserInterfaceItem;
import com.sebbaindustries.dynamicshop.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class MainPageUI implements UserInterface {

    public MainPageUI() {
        metaData = new UIMetaData();
        String guiName = Core.gCore().dynEngine.shopUI.mainPageCache.guiName;
        metaData.setTitle(Color.format(guiName));

        UserInterfaceItem background = Core.gCore().dynEngine.shopUI.mainPageCache.background;
        if (background != null) this.background = background;

        inventorySlots = Core.gCore().dynEngine.shopUI.mainPageCache.item;
        sizeGUI();
        inventory = Bukkit.createInventory(null, metaData.getRows()*9, metaData.getTitle());
    }

    private Player player;
    private Inventory inventory;
    private UIMetaData metaData;
    private HashMap<Integer, UserInterfaceItem> inventorySlots;
    private UserInterfaceItem background = null;

    private void sizeGUI() {
        int size = Core.gCore().dynEngine.shopUI.mainPageCache.size;
        if (size == -1) {
            int guiRows = 1;
            for (int position : inventorySlots.keySet()) {
                double potentialGUISize = Math.ceil((double) position / 9.0);
                if (guiRows < potentialGUISize) guiRows = (int) potentialGUISize;
            }
            metaData.setRows(guiRows);
            return;
        }
        metaData.setRows(size);
    }

    @Override
    public void open(Player player) {
        player.openInventory(inventory);
        this.player = player;
        Core.gCore().dynEngine.shopUI.invHolder.userInterfaceHashMap.put(player, this);
    }

    @Override
    public void draw() {
        Core.gCore().dynEngine.shopUI.invHolder.userInterfaceHashMap.put(player, this);
    }

    @Override
    public void clear() {
        Core.gCore().dynEngine.shopUI.invHolder.userInterfaceHashMap.put(player, this);
    }

    @Override
    public void update() {
        inventory = Bukkit.createInventory(null, metaData.getRows()*9, metaData.getTitle());
        fillBackground();
        inventorySlots.forEach((position, item) -> inventory.setItem(position, item.getBukkitItemStack()));
        fillCategories();
        Core.gCore().dynEngine.shopUI.invHolder.userInterfaceHashMap.put(player, this);
    }

    private void fillCategories() {
        //Core.gCore().dynEngine.container.getPrioritizedCategoryList().forEach(shopCategory -> inventorySlots.forEach(((position, item) -> {
        //    if (!item.isPlaceholder()) return;
        //    inventory.setItem(position, shopCategory.icon().getBukkitItemStack());
        //})));

        Core.gCore().dynEngine.container.getPrioritizedCategoryList().forEach(category -> {
            inventorySlots.entrySet().stream().anyMatch(entry -> {
                if (entry.getValue().isPlaceholder()) {
                    inventory.setItem(entry.getKey(), category.icon().getBukkitItemStack());
                    var itm = entry.getValue();
                    itm.placeholder = false;
                    inventorySlots.put(entry.getKey(), itm);
                    return true;
                }
                return false;
            });
        });
    }

    private void fillBackground() {
        if (this.background == null) return;
        for (int i = 0; i < metaData.getRows()*9; i++) {
            inventory.setItem(i, new ItemStack(background.getBukkitItemStack()));
        }
    }

    @Override
    public void close() {
        player.closeInventory();
        Core.gCore().dynEngine.shopUI.invHolder.userInterfaceHashMap.remove(player);
    }

    @Override
    public void onRightClick(int slot) {
        if (inventorySlots.get(slot) == null) return;
        UIAction.Actions action = inventorySlots.get(slot).onRightClick.get();
        if (action == null) return;
        switch (action) {
            case CLOSE -> close();
            case OPEN -> {
                UserInterface ui = new StorePageUI();
                ui.update();
                ui.open(player);
            }
        }
    }

    @Override
    public void onLeftClick(int slot) {
        if (inventorySlots.get(slot) == null) return;
        UIAction.Actions action = inventorySlots.get(slot).onLeftClick.get();
        if (action == null) return;
        switch (action) {
            case CLOSE -> close();
            case OPEN -> {
                UserInterface ui = new StorePageUI();
                ui.update();
                ui.open(player);
            }
        }
    }

    @Override
    public void onMiddleClick(int slot) {

    }

    @Override
    public void setMetaData(UIMetaData metaData) {
        this.metaData = metaData;
    }

    @Override
    public UIMetaData getMetaData() {
        return this.metaData;
    }
}
