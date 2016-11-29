package com.smartdevicelink.api.menu;

import android.util.Log;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.proxy.rpc.AddSubMenu;
import com.smartdevicelink.proxy.rpc.DeleteSubMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class SdlMenu extends SdlMenuItem {

    private static final String TAG = SdlMenu.class.getSimpleName();

    //tracks menu items that are visible on the UI
    private final HashMap<String, SdlMenuItem> mEntryMap;
    //tracks menu items that are voice exclusive
    private final HashMap<String, SdlMenuItem> mVoiceMap;
    //contains set of all registered voiceCommands, only created at root level
    private final HashSet<String> mRegisteredVoiceCommands;
    // Used to keep track of indexes
    private final ArrayList<String> mEntryKeyList;
    // Used to queue up items to be removed.
    private final LinkedList<SdlMenuItem> mPendingRemovals;
    private final HashSet<SdlMenuItem> mPendingAdditions;

    // True if menu has been created on head unit
    private boolean isCreated = false;
    // True if menu on head unit needs updated

    SdlMenu(String name, int index, SdlMenuItem parent){
        super(name);
        mIndex = index;
        isCreated = parent==null;
        mEntryKeyList = new ArrayList<>();
        mEntryMap = new HashMap<>();
        mParent = parent;
        mVoiceMap = new HashMap<>();
        mRegisteredVoiceCommands = isCreated? new HashSet<String>():null;
        mPendingRemovals = new LinkedList<>();
        mPendingAdditions = new HashSet<>();
    }

    SdlMenu(String name, SdlMenuItem parent){
        this(name, -1, parent);
    }

    void addMenuItem(SdlMenuItem item){
        if(item.getName()!=null && !mEntryMap.containsKey(item.getName())){
            String itemName = item.getName();
            Log.d(TAG, mName + ": adding item name " + itemName);
            mEntryKeyList.add(itemName);
            mEntryMap.put(itemName, item);
            mPendingAdditions.add(item);
        } else if(item.getName()!=null){
            throw new IllegalArgumentException(String.format("Menu named '%s' already contains item " +
                    "named '%s", mName, item.mName));
        }
        if(item.getVoiceCommands()!=null){
            registerVoiceCommands(item);
            if(item.getName()==null){
                for(String voiceCommand:item.getVoiceCommands()){
                    mVoiceMap.put(voiceCommand,item);
                }
                mPendingAdditions.add(item);
            }
        }
    }

    void addMenuItem(int index, SdlMenuItem item){
        if(item.getName()!=null && !mEntryMap.containsKey(item.getName())){
            String itemName = item.getName();
            Log.d(TAG, mName + ": adding item name " + itemName);
            mEntryKeyList.add(index, itemName);
            mEntryMap.put(itemName, item);
            mPendingAdditions.add(item);
        } else if(item.getName()!=null){
            throw new IllegalArgumentException(String.format("Menu named '%s' already contains item " +
                    "named '%s", mName, item.mName));
        }
        if(item.getVoiceCommands()!=null){
            registerVoiceCommands(item);
            if(item.getName()==null){
                for(String voiceCommand:item.getVoiceCommands()){
                    mVoiceMap.put(voiceCommand,item);
                }
                mPendingAdditions.add(item);
            }
        }
    }

    void removeMenuItem(SdlMenuItem menuItem){
        String name = menuItem.getName();
        if(name!=null && mEntryMap.containsKey(name)){
            Log.d(TAG, mName + ": removing item name " + name);
            SdlMenuItem item = mEntryMap.remove(name);
            removeEntryKey(name);
            if(item != null){
                if(mPendingAdditions.contains(menuItem)){
                    mPendingAdditions.remove(menuItem);
                } else {
                    mPendingRemovals.add(item);
                }
            }
        }
        if(menuItem.getVoiceCommands()!=null){
            unregisterVoiceCommands(menuItem);
            if(menuItem.getName()==null){
                for(String voiceCommand:menuItem.getVoiceCommands()){
                    mVoiceMap.remove(voiceCommand);
                }
                if(mPendingAdditions.contains(menuItem)){
                    mPendingAdditions.remove(menuItem);
                } else {
                    mPendingRemovals.add(menuItem);
                }
            }
        }
    }

    public boolean contains(SdlMenuItem sdlMenuItem){
        return sdlMenuItem != null && containsMenuName(sdlMenuItem.getName());
    }

    public boolean containsMenuName(String itemName){
        return itemName != null && mEntryMap.containsKey(itemName);
    }

    public boolean containsVRCommand(String vrCommand){
        return vrCommand != null;
    }

    SdlMenuItem getMenuItemByName(String name){
        return mEntryMap.get(name);
    }

    SdlMenuItem getMenuItemByVRCommand(String vrCommand){
        SdlMenuItem item = mVoiceMap.get(vrCommand);
        return item==null? getUIMenuItem(vrCommand):item;
    }

    int indexOf(SdlMenuItem item){
        return mEntryKeyList.indexOf(item.getName());
    }

    @Override
    void update(SdlContext sdlContext, int subMenuId) {
        if(!isCreated && mParent!=null) {
            isCreated = true;
            sendAddSubMenu(sdlContext);
        }
        sendPendingRemovals(sdlContext);
        sendPendingAdditions(sdlContext);
    }

    private void sendPendingAdditions(SdlContext sdlContext){
        for(SdlMenuItem item: mPendingAdditions){
            if(item != null){
                item.update(sdlContext,
                        mParent==null ? -1: mId);
            }
        }
        mPendingAdditions.clear();
    }

    private void sendPendingRemovals(SdlContext sdlContext) {
        while(!mPendingRemovals.isEmpty()){
            SdlMenuItem item = mPendingRemovals.removeFirst();
            item.remove(sdlContext);
            item.unregisterSelectListener(sdlContext);
        }
    }

    @Override
    void remove(SdlContext sdlContext) {
        sendPendingRemovals(sdlContext);

        mPendingAdditions.clear();

        for(int i = 0; i < mEntryKeyList.size(); i++){
            SdlMenuItem item = mEntryMap.remove(mEntryKeyList.get(i));
            item.remove(sdlContext);
            item.unregisterSelectListener(sdlContext);
        }

        mEntryKeyList.clear();

        //only need to have each voice command remove itself once
        HashSet<SdlMenuItem> uniqueMenuItems = new HashSet<>(mVoiceMap.values());
        for(SdlMenuItem voiceOnlyCommands:uniqueMenuItems){
            unregisterVoiceCommands(voiceOnlyCommands);
            voiceOnlyCommands.remove(sdlContext);
            voiceOnlyCommands.unregisterSelectListener(sdlContext);
        }
        mVoiceMap.clear();

        if(mParent!=null){
            sendDeleteSubMenu(sdlContext);
        }

        isCreated = false;
    }

    @Override
    void registerSelectListener(SdlContext sdlContext) {
        for(int i = 0; i < mEntryKeyList.size(); i++){
            SdlMenuItem item = mEntryMap.get(mEntryKeyList.get(i));
            item.registerSelectListener(sdlContext);
        }
    }

    @Override
    void unregisterSelectListener(SdlContext sdlContext) {
        for(int i = 0; i < mEntryKeyList.size(); i++){
            SdlMenuItem item = mEntryMap.get(mEntryKeyList.get(i));
            item.unregisterSelectListener(sdlContext);
        }
    }

    @Override
    void registerVoiceCommands(SdlMenuItem item){
        if(mParent==null){
            for(String voiceCommand: item.getVoiceCommands()){
                if(mRegisteredVoiceCommands.contains(voiceCommand)){
                    mRegisteredVoiceCommands.add(voiceCommand);
                } else {
                    throw new IllegalArgumentException(String.format("Menu already contains voice command " +
                            "named '%s", voiceCommand));
                }
            }
        } else {
            mParent.registerVoiceCommands(item);
        }
    }

    @Override
    void unregisterVoiceCommands(SdlMenuItem item){
        if(mParent==null){
            mRegisteredVoiceCommands.removeAll(item.getVoiceCommands());
        } else {
            mParent.unregisterVoiceCommands(item);
        }
    }

    private void sendAddSubMenu(SdlContext sdlContext) {
        AddSubMenu asm = new AddSubMenu();
        if(mIndex > 0) asm.setPosition(mIndex);
        asm.setMenuID(mId);
        asm.setMenuName(mName);
        sdlContext.sendRpc(asm);
    }

    private void sendDeleteSubMenu(SdlContext sdlContext) {
        DeleteSubMenu dsm = new DeleteSubMenu();
        dsm.setMenuID(mId);
        sdlContext.sendRpc(dsm);
    }

    private void removeEntryKey(String key){
        for(int i = 0; i < mEntryKeyList.size(); i++){
            if(mEntryKeyList.get(i).equals(key)){
                mEntryKeyList.remove(key);
                break;
            }
        }
    }

    private SdlMenuItem getUIMenuItem (String vrCommand){
        for(SdlMenuItem item:mEntryMap.values()){
            if(item.getVoiceCommands()!=null && item.getVoiceCommands().contains(vrCommand)){
                return item;
            }
        }
        return null;
    }
}
