package com.diyartaikenov.game.managers;

import static com.diyartaikenov.game.utils.UrlConstants.FONT_FOLDER;
import static com.diyartaikenov.game.utils.UrlConstants.TEXTURE_FOLDER;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;

public class SimpleAssetManager implements Disposable {
    private final AssetManager assetManager;

    public SimpleAssetManager() {
        InternalFileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager = new AssetManager(resolver, false);
        assetManager.setLoader(Texture.class, new TextureLoader(resolver));
        assetManager.setLoader(BitmapFont.class, new BitmapFontLoader(resolver));
    }

    public void addTextureToLoad(String fileName) {
        fileName = TEXTURE_FOLDER + fileName;
        assetManager.load(fileName, Texture.class);
    }

    public void addFontToLoad(String fileName) {
        fileName = FONT_FOLDER + fileName;
        assetManager.load(fileName, BitmapFont.class);
    }

    public void unloadAsset(String fileName) {
        assetManager.unload(fileName);
    }

    public boolean updateLoading() {
        return assetManager.update();
    }

    /**
     * Get loaded texture by its filename.
     */
    public Texture getTexture(String fileName) {
        fileName = TEXTURE_FOLDER + fileName;
        return assetManager.get(fileName, Texture.class);
    }

    /**
     * Get loaded bitmap font by its filename.
     */
    public BitmapFont getFont(String fileName) {
        fileName = FONT_FOLDER + fileName;
        return assetManager.get(fileName, BitmapFont.class);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
