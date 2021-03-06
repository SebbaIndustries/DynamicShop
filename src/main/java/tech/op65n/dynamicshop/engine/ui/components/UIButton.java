package tech.op65n.dynamicshop.engine.ui.components;

import tech.op65n.dynamicshop.engine.ui.interfaces.BukkitItemStack;
import tech.op65n.dynamicshop.engine.ui.interfaces.Clickable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UIButton implements BukkitItemStack, Clickable {

    private Material material;
    private String display;
    private List<String> lore = new ArrayList<>();
    private int amount = 1;
    private String texture = null;
    private String base64 = null;

    private int slot;

    private ClickActions onClick = ClickActions.NA;
    private ClickActions onRightClick = ClickActions.NA;
    private ClickActions onLeftClick = ClickActions.NA;
    private ClickActions onMiddleClick = ClickActions.NA;

    @Override
    public int amount() {
        return this.amount;
    }

    @Override
    public Material material() {
        return this.material;
    }

    @Override
    public String display() {
        return this.display;
    }

    @Override
    public List<String> lore() {
        return this.lore;
    }

    @Override
    public String texture() {
        return this.texture;
    }

    @Override
    public byte[] base64() {
        if (base64 == null) return null;
        return Base64.getDecoder().decode(this.base64);
    }

    @Override
    public ClickActions rightClick() {
        if (this.onRightClick == ClickActions.NA) return this.onClick;
        return this.onRightClick;
    }

    @Override
    public ClickActions leftClick() {
        if (this.onLeftClick == ClickActions.NA) return this.onClick;
        return this.onLeftClick;
    }

    @Override
    public ClickActions middleClick() {
        if (this.onMiddleClick == ClickActions.NA) return this.onClick;
        return this.onMiddleClick;
    }
}
