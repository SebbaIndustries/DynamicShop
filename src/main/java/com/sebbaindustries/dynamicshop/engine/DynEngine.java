package com.sebbaindustries.dynamicshop.engine;

import com.moandjiezana.toml.Toml;
import com.sebbaindustries.dynamicshop.Core;
import com.sebbaindustries.dynamicshop.commands.CommandManager;
import com.sebbaindustries.dynamicshop.engine.components.DynShopContainer;
import com.sebbaindustries.dynamicshop.engine.components.shop.implementations.ShopCategoryImpl;
import com.sebbaindustries.dynamicshop.messages.Message;
import com.sebbaindustries.dynamicshop.utils.FileManager;

public class DynEngine {

    public DynShopContainer container;

    public static ShopCategoryImpl shopCategory = new ShopCategoryImpl();

    public void initialize() {
        Core.gCore().commandManager = new CommandManager(Core.gCore().core);
        Core.gCore().message = new Toml().read(Core.gCore().fileManager.getFile(FileManager.PluginFiles.MESSAGES)).to(Message.class);
        this.container = new DynShopContainer();
    }
}
