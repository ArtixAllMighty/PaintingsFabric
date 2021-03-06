package subaraki.paintings.mod;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import subaraki.paintings.network.ServerNetwork;
import subaraki.paintings.events.Events;
import subaraki.paintings.util.ModConfig;
import subaraki.paintings.util.PaintingUtility;
import subaraki.paintings.util.json.PaintingPackReader;

public class Paintings implements ModInitializer {

    public static final String MODID = "paintings";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static final PaintingUtility UTILITY = new PaintingUtility();
    public static ModConfig config;

    /* call init here, to read json files before any event is launched. */
    static {
        new PaintingPackReader().init();
    }

    @Override
    public void onInitialize() {
        PaintingPackReader.registerToMinecraft();
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        ServerNetwork.registerServerPackets();
        Events.events();
    }
}
