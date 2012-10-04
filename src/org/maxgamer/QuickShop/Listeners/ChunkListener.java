package org.maxgamer.QuickShop.Listeners;

import java.util.HashMap;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.maxgamer.QuickShop.QuickShop;
import org.maxgamer.QuickShop.Shop.DisplayItem;
import org.maxgamer.QuickShop.Shop.Shop;


public class ChunkListener implements Listener{
	private QuickShop plugin;
	
	public ChunkListener(QuickShop plugin){
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onChunkLoad(ChunkLoadEvent e){
		//Testing
		Chunk c = e.getChunk();
		if(plugin.getShopManager().getShops() == null) return;
		
		HashMap<Location, Shop> inChunk = plugin.getShopManager().getShops(c);
		
		if(inChunk == null) return;
		
		for(Shop shop : inChunk.values()){
			//TODO: Check display items are enabled
			DisplayItem disItem = shop.getDisplayItem();
			disItem.removeDupe();
			disItem.remove();
			disItem.spawn();
		}
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onChunkUnload(ChunkUnloadEvent e){
		Chunk c = e.getChunk();
		
		HashMap<Location, Shop> inChunk = plugin.getShopManager().getShops(c);
		
		if(inChunk == null) return;
		//TODO: Check display items are enabled
		for(Shop shop : inChunk.values()){
			DisplayItem disItem = shop.getDisplayItem();
			disItem.removeDupe();
			disItem.remove();
		}
	}
}