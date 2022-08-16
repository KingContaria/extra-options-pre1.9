package me.voidxwalker.options.extra;

import com.google.common.io.Files;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class ExtraOptions implements ClientModInitializer {

    private static final File extraOptionsFile = new File(FabricLoader.getInstance().getConfigDirectory(), "extraoptions.txt");
    public static ExtraOption fovEffectScale = new ExtraOption("FOV Effects Scale", 1.0f);
    public static ExtraOption distortionEffectScale = new ExtraOption("Distortion Effects Scale", 1.0f);

    public static void save() {
        if (!extraOptionsFile.getParentFile().exists()) extraOptionsFile.getParentFile().mkdir();
        try {
            Files.write(("fovEffectScale:" + fovEffectScale.getValue() + System.lineSeparator() + "screenEffectScale:" + distortionEffectScale.getValue()).getBytes(), extraOptionsFile);
        } catch (IOException e) {
            LogManager.getLogger().error("Failed to save extraoptions");
        }
    }

    @Override
    public void onInitializeClient() {
        if (extraOptionsFile.exists()) {
            try {
                for (String line : Files.readLines(extraOptionsFile, Charset.defaultCharset())) {
                    String[] strings = line.split(":", 2);
                    if (strings[0].equals("fovEffectScale")) {
                        fovEffectScale.setValue(Float.parseFloat(strings[1]));
                    } else if (strings[0].equals("screenEffectScale")) {
                        distortionEffectScale.setValue(Float.parseFloat(strings[1]));
                    }
                }
            } catch (IOException e) {
                LogManager.getLogger().error("Failed to read extraoptions");
            }
        }
    }
}
