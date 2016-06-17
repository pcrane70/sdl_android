package com.smartdevicelink.api.properties;

import com.smartdevicelink.proxy.rpc.SetGlobalProperties;
import com.smartdevicelink.proxy.rpc.TTSChunk;
import com.smartdevicelink.proxy.rpc.VrHelpItem;
import com.smartdevicelink.proxy.rpc.enums.SpeechCapabilities;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by mschwerz on 6/15/16.
 */
public class SdlVRProperties {
    private final ArrayList<TTSChunk> mHelpPrompt= new ArrayList<>();
    private final ArrayList<TTSChunk> mTimeoutPrompt= new ArrayList<>();
    private final String mVRHelpTitle;
    private final ArrayList<SdlVRHelpItem> mVRHelpItems= new ArrayList<>();

    public SdlVRProperties(String vrHelpTitle){
        mVRHelpTitle= vrHelpTitle;
    }

    public void setHelpPrompts(Collection<TTSChunk> helpPrompts){
        mHelpPrompt.clear();
        mHelpPrompt.addAll(helpPrompts);
    }

    public void setHelpPrompts(ArrayList<String> helpPrompts){
        convertStringListToTTSChunks(mHelpPrompt,helpPrompts);
    }

    public void addHelpPrompt(TTSChunk helpPrompt){
        mHelpPrompt.add(helpPrompt);
    }

    public void setTimeoutPrompts(Collection<TTSChunk> timeoutPrompts){
        mTimeoutPrompt.clear();
        mTimeoutPrompt.addAll(timeoutPrompts);
    }

    public void setTimeoutPrompts(ArrayList<String> timeoutPrompts){
        convertStringListToTTSChunks(mTimeoutPrompt,timeoutPrompts);
    }

    public void setVrHelpItems(Collection<SdlVRHelpItem> helpItems){
        mVRHelpItems.clear();
        mVRHelpItems.addAll(helpItems);
    }

    public void addVrHelpItem(SdlVRHelpItem helpItem){
        mVRHelpItems.add(helpItem);
    }

    public SetGlobalProperties decorateProperties(SetGlobalProperties toDecorateProperties){
        toDecorateProperties.setHelpPrompt(mHelpPrompt);
        toDecorateProperties.setTimeoutPrompt(mTimeoutPrompt);
        toDecorateProperties.setVrHelpTitle(mVRHelpTitle);
        ArrayList<VrHelpItem> helpItems= new ArrayList<>();
        for(int i=0; i<mVRHelpItems.size();i++){
            helpItems.add(mVRHelpItems.get(i).createHelpItem(i));
        }
        toDecorateProperties.setVrHelp(helpItems);
        return toDecorateProperties;
    }

    private void convertStringListToTTSChunks(ArrayList<TTSChunk> chunks, ArrayList<String> text){
        chunks.clear();
        for(String helpText:text){
            TTSChunk newChunk= new TTSChunk();
            newChunk.setType(SpeechCapabilities.TEXT);
            newChunk.setText(helpText);
            chunks.add(newChunk);
        }
    }

}
