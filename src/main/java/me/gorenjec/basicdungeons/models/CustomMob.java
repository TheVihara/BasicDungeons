package me.gorenjec.basicdungeons.models;

import me.gorenjec.basicdungeons.utils.HexUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public abstract class CustomMob {
    private LivingEntity entity;

    private EntityType type;
    private double maxHealth;
    private String displayName;
    private List<ItemStack> mobDrops;
    private ItemStack[] equipment;

    public CustomMob(EntityType type, double maxHealth, String displayName, List<ItemStack> mobDrops, ItemStack... equipment) {
        this.type = type;
        this.maxHealth = maxHealth;
        this.displayName = displayName;
        this.equipment = equipment;
    }

    public void spawnMob(Location loc) {
        World world = loc.getWorld();
        assert world != null;
        this.entity = (LivingEntity) world.spawnEntity(loc, type);
        EntityEquipment entityEquipment = this.entity.getEquipment();

        Objects.requireNonNull(this.entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(maxHealth);
        this.entity.setCustomName(HexUtils.colorify(displayName));
        this.entity.setCustomNameVisible(true);
        assert entityEquipment != null;
        entityEquipment.setHelmet(equipment[0]);
        entityEquipment.setChestplate(equipment[1]);
        entityEquipment.setLeggings(equipment[2]);
        entityEquipment.setBoots(equipment[3]);
        entityEquipment.setItemInMainHand(equipment[4]);
        entityEquipment.setItemInMainHand(equipment[5]);
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public EntityType getType() {
        return type;
    }

    public List<ItemStack> getMobDrops() {
        return mobDrops;
    }

    public ItemStack[] getEquipment() {
        return equipment;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public void setHealth(double health) {
        if (entity != null) {
            entity.setHealth(health);
        }
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public void setMobDrops(List<ItemStack> mobDrops) {
        this.mobDrops = mobDrops;
    }

    public void setEquipment(ItemStack[] equipment) {
        this.equipment = equipment;
    }
}
