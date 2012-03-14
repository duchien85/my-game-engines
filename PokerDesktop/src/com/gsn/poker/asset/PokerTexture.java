package com.gsn.poker.asset;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class PokerTexture {
	static public final String tag = "Chess Texture";
	static public AssetManager manager;	
	static public AtlasRegion board;
	static public AtlasRegion board1;
	static public void finishLoading() {		
		manager.finishLoading();
	}

	static public void loadAll() {
		loadTexture();
		finishLoading();
		assignContent();
	}
	
	private static void assignContent() {		
		TextureAtlas atlas = manager.get("poker/content/pack", TextureAtlas.class);
				
		Field[] fields = PokerTexture.class.getFields();
		for (int i = 0; i < fields.length; i++){
			Field f = fields[i];			
			if (Modifier.isPublic(f.getModifiers()) && Modifier.isStatic(f.getModifiers()) && f.getType() == AtlasRegion.class){
				try {
					AtlasRegion region = atlas.findRegion(f.getName()); 
					f.set(null, region);
					if (region == null){
						Gdx.app.log(tag, "region = null voi : " + f.getName());
					}
				} catch (Exception e) {
					e.printStackTrace();				
				}
			}
		}
	}

	static public void loadTexture() {
		manager.load("poker/content/pack", TextureAtlas.class);
		manager.load("poker/font/medium.fnt", BitmapFont.class);		
		manager.load("poker/font/large.fnt", BitmapFont.class);
	}
	
	static public void create() {
		Resolution[] resolutions = { new Resolution(240, 320, "240320")};
		ResolutionFileResolver resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
		manager = new AssetManager(resolver);
//		manager.setErrorListener(new AssetErrorListener() {
//			@Override
//			public void error(String fileName, Class type, Throwable throwable) {
//				// TODO Auto-generated method stub
//				Gdx.app.error(tag, "couldn't load asset '" + fileName + "'", (Exception) throwable);
//			}
//		});
		Texture.setAssetManager(manager);
	}

	static public boolean update() {
		return manager.update();
	}

}