package tech.op65n.dynamicshop;

import tech.op65n.dynamicshop.global.GlobalCore;
import tech.op65n.dynamicshop.global.ServerPlugin;
import tech.op65n.dynamicshop.log.DevLogger;
import tech.op65n.dynamicshop.log.EngineLogger;
import tech.op65n.dynamicshop.log.ILog;
import tech.op65n.dynamicshop.log.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author <b>SebbaIndustries</b>
 * @version <b>1.0</b>
 */
public final class Core extends JavaPlugin {

    public static ILog engineLogger = new EngineLogger("[Engine]");
    public static ILog pluginLogger = new PluginLogger("[Plugin]");
    public static ILog devLogger = new DevLogger("[Dev]");

    public static GlobalCore globalCore = null;
    private final ServerPlugin plugin = ServerPlugin.INSTANCE();

    /**
     * Safe way to get GlobalCore class
     *
     * @return GlobalCore class instance
     */
    @NotNull
    public static GlobalCore gCore() {
        if (globalCore == null) {
            throw new IllegalAccessError("Plugin core dumped due to illegal access of GlobalCore class!");
        }
        return globalCore;
    }


    @Override
    public void onLoad() {
        super.onLoad();
        plugin.load(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        plugin.terminate();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        plugin.initialize();
    }

}
